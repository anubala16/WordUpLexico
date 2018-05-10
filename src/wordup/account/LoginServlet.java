package wordup.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wordup.business.User;
import wordup.dataUtil.UserDBUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getAttribute("user") != null) {
			System.out.println("User exists!");
			String url = "/welcome.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		} else {
			// user is null
			System.out.println("User is null?!");
			ArrayList<String> errors = new ArrayList<String>();
			errors.add("Please login before proceeding.");
			String url = "/login.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
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
			action = "login";
		}

		// perform action and set URL to appropriate page
		String url = "/login.jsp";

		if (action.equals("login")) {
			String email = request.getParameter("email");
			String pwd = request.getParameter("password");
			System.out.println(email + '\n' + pwd + '\n');
			HttpSession session = request.getSession();
			ArrayList<String> errors = new ArrayList<String>();
			session.setMaxInactiveInterval(-1);

			if (email.trim().length() == 0 || pwd.trim().length() == 0) {
				errors.add("Please provide username and password");
			}

			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
					+ "A-Z]{2,7}$";
			Pattern pat = Pattern.compile(emailRegex);
			boolean isValid = pat.matcher(email).matches();
			if (!isValid) {
				errors.add("Invalid email provided");
			}

			User user = UserDBUtil.getUserByEmail(email);
			if (user == null) {
				errors.add("Unknown user. Please sign-up for an account before proceeding.");
				//response.sendRedirect("register.jsp");
			} else if (!pwd.equals(user.getPassword())) {
				errors.add("Invalid login credentials");
				//response.sendRedirect("register.jsp");
			} else {
				// valid login
				session.setAttribute("user", user);
				url = "/welcome.jsp";
				//response.sendRedirect("welcome.jsp");
			}

			request.setAttribute("errors", errors);
			System.out.println("done with login servlet");
			getServletContext().getRequestDispatcher(url).forward(request, response);

		} else if (action.equals("logout")) {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			request.removeAttribute("errors");
			request.setAttribute("success", "You're signed out!");

			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
	}

}
