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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import wordup.business.Card;
import wordup.business.Lesson;
import wordup.business.User;
import wordup.dataUtil.CardDBUtil;
import wordup.dataUtil.LessonDBUtil;
import wordup.dataUtil.LessonDBUtil.LessonAuthor;

/**
 * Servlet implementation class LessonServlet
 */
// @WebServlet({ "/LessonServlet", "/lessons/create", "/lessons/study",
// "lessons/myLessons",
// "/lessons/quiz" })
public class LessonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LessonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		String url = "/lessons/create.jsp";
		if (action.equals("create")) {
			String title = request.getParameter("title");
			String path = request.getParameter("file");
			String subject = request.getParameter("subject");
			String subject2 = request.getParameter("subject2");
			String subject3 = request.getParameter("subject3");
			String accessLevel = request.getParameter("access");
			User user = (User) request.getSession().getAttribute("user");
			System.out.println("creating lesson.." + path);

			if (user == null) {
			}

			Lesson lesson = null, newLesson = null;
			File f = null;
			try {
				f = new File(path);
				if (!f.exists()) {
					throw new Exception(
							"Unable to open file: " + path + ". Please make sure the file exists and try again.");
				}
				lesson = new Lesson(title, path, subject, subject2, subject3, accessLevel, user.getUserID());
				newLesson = LessonDBUtil.getLessonByFileAndTitle(path, title); // are
																				// we
																				// creating
																				// or
																				// updating
																				// a
																				// lesson?
				if (newLesson == null) { // new lesson
					int rowCount = LessonDBUtil.insert(lesson);
					if (rowCount == 0) {
						throw new Exception("Error inserting the new Lesson in database.");
					}
					newLesson = LessonDBUtil.getLessonByFileAndTitle(path, title);
				} else { // lesson already exists...whose the author?
					if (newLesson.getAuthorID() == user.getUserID()) { // original
																		// author
																		// is
																		// editing
																		// lesson
						lesson.setLessonID(newLesson.getLessonID());
						int rowCount = LessonDBUtil.update(lesson);
						if (rowCount == 1) {
							System.out.println("Lesson updated!");
							success = "Success! Updated your older lesson :)";
							request.setAttribute("lesson", newLesson);
							rowCount = LessonDBUtil.deleteCards(lesson.getLessonID());
						} else {
							System.out.println("Error updating lesson...");
						}
					} else { // different author trying to create/modify a
								// lesson with same name and file
						errors.add(
								"A lesson is already created by another user based on the given file. Please try with another file and/or lesson name.");
					}
				}
				if (errors.size() == 0) {
					Scanner scanner = new Scanner(f);
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine().trim();
						String[] tokens = line.split(": ");
						if (tokens.length != 2) {
							System.out.println("invalid line, ignored card creation for line.");
							continue;
						}
						Card card = new Card(tokens[0].trim(), tokens[1].trim(), newLesson.getLessonID());
						int rowCount = CardDBUtil.insert(card);
					}
					// JFileChooser fileChooser = new JFileChooser();
					// fileChooser.addChoosableFileFilter(new
					// FileNameExtensionFilter("TEXT Documents", "txt"));
					// int result = fileChooser.showOpenDialog(new JFrame());
					// if (result == JFileChooser.APPROVE_OPTION) {
					// File selectedFile = fileChooser.getSelectedFile();
					// System.out.println("Selected file: " +
					// selectedFile.getAbsolutePath());
					// }
					scanner.close();
				}
			} catch (Exception e) {
				errors.add(e.getMessage());
				url = "/lessons/create.jsp";
			}

			if (errors.size() == 0) {
				url = "/lessons/create.jsp";
				if (success == "") {
					success = "Success! Your lesson is created :)";
				}
				request.setAttribute("success", success);
				request.setAttribute("lesson", newLesson);
			}
			
			/** done at end of method */
			// request.setAttribute("errors", errors);
 			// getServletContext().getRequestDispatcher(url).forward(request,
			// response);
		} else if (action.equals("review")) {
			// study the lesson or take a quiz
			String study = request.getParameter("study");
			//String study2 = request.getParameter("study2");
			String quizMe = request.getParameter("quizMe");
			int lessonID = 0;
			if (study != null) {
				lessonID = Integer.parseInt(study);
			} else 
				lessonID = Integer.parseInt(quizMe);
			System.out.println("Lesson Servlet lesson id " + lessonID);
			ArrayList<Card> cards = null;
			if (lessonID != 0) {
				cards = CardDBUtil.getLessonCards(lessonID);
				request.setAttribute("cards", cards);
				LessonAuthor la = LessonDBUtil.getLessonAuthor(lessonID);
				if (la == null) {
					errors.add("Lesson does not exist. Please go back to catalog and choose a lesson that exists.");
				} else {
					request.setAttribute("lessonAuthor", la);
					int cardCount = cards.size();
					/**
					int[] numbers = new int[cardCount];
					for (int i = 0; i < cardCount; i++) {
						numbers[i] = i+1;
					}
					*/
					request.setAttribute("cardCount", cardCount);
					System.out.println("found " + cardCount + " cards for lesson " + lessonID + "! ");
				}
			}
			if (study != null) {
				// study button pressed
				System.out.println("User wants to study lesson " + lessonID);
				url = "/lessons/study.jsp";
			} else if (quizMe != null) {
				// quiz me button functionality
				System.out.println("User wants to take lesson " + lessonID + " quiz.");
				url = "/lessons/takequiz.jsp";
			} else {
				// refresh
				System.out.println("Refreshing browse page. ");
				url = "/lessons/browse.jsp";
			}
		}

		request.setAttribute("errors", errors);

		getServletContext().getRequestDispatcher(url).forward(request, response);

	}

}
