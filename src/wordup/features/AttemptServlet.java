package wordup.features;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wordup.business.Attempt;
import wordup.business.Card;
import wordup.business.Lesson;
import wordup.business.Response;
import wordup.business.User;
import wordup.dataUtil.AttemptDBUtil;
import wordup.dataUtil.AttemptDBUtil.LessonAttempt;
import wordup.dataUtil.CardDBUtil;
import wordup.dataUtil.LessonDBUtil;
import wordup.dataUtil.ResponseDBUtil;
import wordup.dataUtil.LessonDBUtil.LessonAuthor;
import wordup.dataUtil.MailUtilGmail;

/**
 * Servlet implementation class AttemptServlet
 */
@WebServlet(description = "Handles user attempts of lesson quizzes", urlPatterns = { "/AttemptServlet" })
public class AttemptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttemptServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		ArrayList<LessonAttempt> la = AttemptDBUtil.getLessonAttempts(user.getUserID()); 
		request.setAttribute("lessonAttempts", la);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get current action
		String action = request.getParameter("action");
		System.out.println("Attempt Servlet!");
		System.out.println("Action: " + action);

		if (action == null) {

			action = "refresh"; // default action
			getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
		}
		ArrayList<String> errors = new ArrayList<String>();
		// perform action and set URL to appropriate page
		String success = "";
		String url = "/lessons/myScores.jsp";
		if (action.equals("quiz")) { // grade quiz attempt
			// submitted attempt
			int lessonID = Integer.parseInt(request.getParameter("lessonID"));
			ArrayList<Card> cards = null;
			if (lessonID != 0) {
				cards = CardDBUtil.getLessonCards(lessonID);
				request.setAttribute("cards", cards);
				int cardCount = cards.size();
				User user = (User) request.getSession().getAttribute("user");
				int userID = user.getUserID();
				ArrayList<Attempt> userAttempts = AttemptDBUtil.getAttempts(lessonID, userID);
				int count = userAttempts.size() + 1;
				Attempt newAttempt = new Attempt(lessonID, userID, count);
				int rowCount = AttemptDBUtil.insert(newAttempt);
				if (rowCount == 1) {
					System.out.println("Inserted attempt...");
				}
				Attempt a = AttemptDBUtil.getAttempt(lessonID, userID, count);
				int score = 0;
				for (int i = 1; i <= cardCount; i++) {
					String index = Integer.toString(i);
					String resp = request.getParameter(index);
					System.out.println("Resp " + i + ": " + resp + " ");
					Response r = new Response(cards.get(i - 1).getCardID(), a.getAttemptID(), resp);
					ResponseDBUtil.insert(r);
					System.out.println("Here: " + resp + " (Key: " + cards.get(i - 1).getWord() + ") ");
					if (resp.trim().equalsIgnoreCase(cards.get(i - 1).getWord().trim())) {
						score += 1;
					}
				}
				a.setScore(score); // only 'a' has the attemptID field set!
				AttemptDBUtil.update(a);
				System.out.println("updated attempt!");

				doGet(request, response);
				//request.setAttribute("responses", ResponseDBUtil.getAttemptResponses(a.getAttemptID()));
			}
		} else if (action.equals("scores")) {
			System.out.println("emailing scores for you :)");
			String strAttemptID = request.getParameter("email");
			if (strAttemptID == null) {
				errors.add("Unknown quiz attempt. Please login before proceeding.");
				url = "/login.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			int attemptID = Integer.parseInt(strAttemptID);
			Attempt a = AttemptDBUtil.getAttemptById(attemptID);
			Lesson l = LessonDBUtil.getLessonByID(a.getLessonID());
			ArrayList<Card> cards = CardDBUtil.getLessonCards(l.getLessonID());
			ArrayList<Response> responses = ResponseDBUtil.getAttemptResponses(attemptID);
			String to = "abalaji@uncc.edu";
			String from = "admin@wordup.com";
			from = "abalaji@uncc.edu";
			String subject = "WordUp Score Report: " + l.getTitle() + "(Attempt: " + a.getCount() + ")";
			StringBuilder body = new StringBuilder();
			for (int i = 0; i < cards.size(); i++) {
				int num = i + 1;
				body.append("Prompt " + num + ": " + cards.get(i).getDescription() + "\n\n");
				body.append("Your Answer: " + responses.get(i).getUserResp() + "\n");
				body.append("Correct Answer: " + cards.get(i).getWord() + "\n"
						+ "---------------------------------------------------\n\n");
			}
			double percent = (double) a.getScore() * 100 / cards.size();
			body.append("Final Score: " + a.getScore() + " out of " + cards.size() + " = " + percent + "% \n===================================================\n\n");
			boolean isBodyHTML = false;
			try {
				MailUtilGmail.sendMail(to, from, subject, body.toString(), isBodyHTML);
				System.out.println("sent email!");
			} catch (MessagingException e) {
				System.out.println("Error sending email");
                String errorMessage
                    = "ERROR: Unable to send email. "
                    + "Check Tomcat logs for details.<br>"
                    + "NOTE: You may need to configure your system "
                    + "as described in chapter 14.<br>"
                    + "ERROR MESSAGE: " + e.getMessage();
                errors.add(errorMessage);
                request.setAttribute("errors", errors);
                    this.log(
                    "Unable to send email. \n"
                    + "Here is the email you tried to send: \n"
                    + "=====================================\n"
                    + "TO: " + to + "\n"
                    + "FROM: " + from + "\n"
                    + "SUBJECT: " + subject + "\n\n"
                    + body + "\n\n");
            }

		} else {
			// should not be here
			// for debugging purposes only
			System.out.println("Null lesson when submitting attempt!");
		}

		request.setAttribute("errors", errors);

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
