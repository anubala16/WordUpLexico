package wordup.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wordup.business.User;
import wordup.dataUtil.UserDBUtil;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = { "/register" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Should not be here!!!");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hmm, posting...");

		// get current action
		String action = request.getParameter("action");
		System.out.println("Action: " + action);
		if (action == null) {
			System.out.println("action is null!");
			action = ""; // default action
			// getServletContext().getRequestDispatcher("/register").forward(request,
			// response);
		}

		// perform action and set URL to appropriate page
		String url = "/register.jsp";
		if (action.equals("register")) {
			String fName = request.getParameter("firstName");
			String lName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String pwd = request.getParameter("password");
			String pwd2 = request.getParameter("password2");
			String prof = request.getParameter("profession");
			/** Playing with multiple submits 
			String register = request.getParameter("register");
			String dummy = request.getParameter("dummy");
			System.out.println("Plz work!!!");
			System.out.println("Hello----" + "\n" + register + " | " + dummy + " hmm");
			if (register != null) {
				System.out.println("Pressed register button...");
				if (dummy == null) {
					System.out.println("should be here as well...");
				}
			} else if (dummy != null) {
				System.out.println("Pressed dummy button...");
				if (register == "") {
					System.out.println("Should be here...");
				}
			}
			*/
			System.out.println(fName + '\n' + lName + '\n' + email + '\n' + pwd + '\n' + pwd2 + '\n' + prof);

			HttpSession session = request.getSession();
			ArrayList<String> errors = new ArrayList<String>();
			session.setMaxInactiveInterval(-1);

			if (fName.length() == 0 || lName.length() == 0 || email.length() == 0 || pwd.length() == 0
					|| pwd2.length() == 0 || prof.length() == 0) {
				errors.add("Please fill out all the required fields");
			}

			if (!pwd2.equals(pwd)) {
				errors.add("Passwords do not match. Please re-enter password");
			}

			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
					+ "A-Z]{2,7}$";
			Pattern pat = Pattern.compile(emailRegex);
			boolean isValid = pat.matcher(email).matches();
			if (!isValid) {
				errors.add("Please provide a valid email id.");
			}

			// valid input provided - check if email id is new or already taken
			User user = new User(fName, lName, email, pwd, prof, 1);
			if (UserDBUtil.getUserByEmail(email) != null) {
				// email id already in use
				errors.add("Given email is already in use. Please provide a new one.");
			}

			if (errors.size() == 0) {
				url = "/login.jsp";
				UserDBUtil.insert(user);
				request.setAttribute("success", "Success! Your account is created :)");
				// request.setAttribute("user", user);
			}

			request.setAttribute("errors", errors);

			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
	}

}
