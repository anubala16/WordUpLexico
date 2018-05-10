package wordup.features;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wordup.business.Card;
import wordup.business.Lesson;
import wordup.business.User;
import wordup.dataUtil.CardDBUtil;
import wordup.dataUtil.LessonDBUtil;
import wordup.dataUtil.LessonDBUtil.LessonAuthor;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// get current action
				String action = request.getParameter("action");
				System.out.println("Action: " + action);

				if (action == null) {

					action = "refresh"; // default action
					getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
				}
				ArrayList<String> errors = new ArrayList<String>();
				// perform action and set URL to appropriate page
				String success = "";
				String url = "/lessons/myScores.jsp";
				if (action.equals("quiz")) {
					// submitted attempt 
					int lessonID = Integer.parseInt(request.getParameter("lessonID"));
					ArrayList<Card> cards = null;
					if (lessonID != 0) {
						cards = CardDBUtil.getLessonCards(lessonID);
						request.setAttribute("cards", cards);
						int cardCount = cards.size();
						for (int i = 0, i < cardCount; i++) {
							
						}
					} else {
						// should not be here 
						// for debugging purposes only 
						System.out.println("Null lesson when submitting attempt!");
					}
				}

				request.setAttribute("errors", errors);

				getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
