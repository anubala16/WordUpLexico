package wordup.account;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wordup.business.Lesson;
import wordup.business.User;
import wordup.dataUtil.UserDBUtil;
import wordup.dataUtil.AttemptDBUtil;
import wordup.dataUtil.LessonDBUtil;
import wordup.dataUtil.AttemptDBUtil.LessonAttempt;
import wordup.dataUtil.LessonDBUtil.LessonAuthor;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
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
		String action = request.getParameter("action");
		String createLesson = request.getParameter("create");
		String viewAll = request.getParameter("viewAllLessons");
		String viewMine = request.getParameter("viewMyLessons");
		String viewScores = request.getParameter("viewMyScores");
		String adminUsers = request.getParameter("adminUsers");
		String adminLessons = request.getParameter("adminLessons");
		User user = (User) request.getSession().getAttribute("user");
		if (action == null) {

			action = "refresh"; // default action
			getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
		}

		ArrayList<String> errors = new ArrayList<String>();
		// perform action and set URL to appropriate page
		String success = "";
		String url = "";
		System.out.println("Action yo: " + action);
		System.out.println(createLesson + " | " + viewAll + " | " + viewMine + " | " + viewScores + " | " + adminLessons + " | " + adminUsers);
		if (viewMine != null) {
			// view my lessons button pressed 
			url = "/lessons/myLessons.jsp";
			ArrayList<Lesson> lessons = LessonDBUtil.getLessonsByUser(user.getUserID());
			System.out.println("Trimming file paths before displaying your lessons");
			for (int i = 0; i < lessons.size(); i++) {
				String[] tokens = lessons.get(i).getFilePath().split("\\\\");
				lessons.get(i).setFilePath(tokens[tokens.length-1]);
				//System.out.println(tokens[tokens.length-1]);
			}
			
			request.setAttribute("lessons", lessons);
		} else if (viewAll != null) {
			// view all lessons button pressed 
			url = "/lessons/browse.jsp";
			ArrayList<LessonAuthor> lessonAuthors = LessonDBUtil.getCatalog(user.getUserID());
			request.setAttribute("lessonAuthors", lessonAuthors);
		} else if (createLesson != null) { 
			// create a lesson button pressed 
			url = "/lessons/create.jsp";
		} else if (viewScores != null) {
			// view my score reports button pressed 
			url = "/lessons/myScores.jsp";
			ArrayList<LessonAttempt> la = AttemptDBUtil.getLessonAttempts(user.getUserID()); 
			request.setAttribute("lessonAttempts", la);
		} else if (adminUsers != null) {
			url = "/users/viewAll.jsp";
			ArrayList<User> users = UserDBUtil.getUsers();
			request.setAttribute("users", users);
		} else if (adminLessons != null) {
			if (user.getType() == 0) {
			url = "/lessons/viewAll.jsp";
			ArrayList<LessonAuthor> lessonAuthors = LessonDBUtil.getLessonAuthors();
			request.setAttribute("lessonAuthors", lessonAuthors);
			} else {
				url = "/login.jsp";
				errors.add("You are unauthorized to view this page. Please sign-in as an admin beofre proceeding.");
			}
		} else {
			System.out.println("Unknown value. Not sure what to do");
			request.removeAttribute("lesson");
			url = "/welcome.jsp";
		}
		
		request.setAttribute("errors", errors);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
