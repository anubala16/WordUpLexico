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
import wordup.util.PasswordUtil;

/**
 * Servlet implementation class LoginServlet Handles user requests while they
 * login
 * 
 * @author Anusha Balaji
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
			//system.out.println("User exists!");
			String url = "/welcome.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		} else {
			// user is null
			//system.out.println("User is null?!");
			ArrayList<String> errors = new ArrayList<String>();
			errors.add("Please login before proceeding.");
			String url = "/login.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
	}

	/**
	 * Handles login and logout for users, creates a session if user credentials
	 * are valid and directs them to their dashboard
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get current action
		String action = request.getParameter("action");
		if (action == null) {
			action = "login";
		}

		// perform action and set URL to appropriate page
		String url = "/login.jsp";

		if (action.equals("login")) {
			// get request parameters
			String email = request.getParameter("email");
			String pwd = request.getParameter("password");
			//system.out.println(email + '\n' + pwd + '\n');
			HttpSession session = request.getSession();
			ArrayList<String> errors = new ArrayList<String>();
			session.setMaxInactiveInterval(-1);

			if (email.trim().length() == 0 || pwd.trim().length() == 0) {
				errors.add("Please provide username and password");
			}

			// validating email
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
					+ "A-Z]{2,7}$";
			Pattern pat = Pattern.compile(emailRegex);
			boolean isValid = pat.matcher(email).matches();
			if (!isValid) {
				errors.add("Invalid email provided");
			}

			// see if user exists...if so, retrieve user
			User user = UserDBUtil.getUserByEmail(email);
			if (user == null) {
				// unknown user 
				errors.add("Unknown user. Please sign-up for an account before proceeding.");
			} else {
				// user exists, authenticate them 
				String pwd_hash = "";
				try {
					pwd_hash = PasswordUtil.hashPassword(pwd.trim() + user.getSalt().trim());
				} catch (Exception e) {
					errors.add("Error during authentication. Please retry");
				}
				if (!pwd_hash.equals(user.getPassword().trim())) {
					errors.add("Invalid login credentials");
				} else {
					// valid login
					session.setAttribute("user", user);
					url = "/welcome.jsp";
				}
			}

			request.setAttribute("errors", errors);
			//system.out.println("done with login servlet");
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
