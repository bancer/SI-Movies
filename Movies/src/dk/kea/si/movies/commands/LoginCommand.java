package dk.kea.si.movies.commands;

import java.io.IOException;

import javax.servlet.ServletException;

import dk.kea.si.movies.domain.User;
import dk.kea.si.movies.helpers.UserHelper;

public class LoginCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		forward("/login.jsp");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		User user = null;
		User temp = new User();
		UserHelper helper = initHelper(temp);
		if(helper.getErrors().isEmpty()) {
			user = (User) getStorage().findByUserName(temp);
			if(user != null) {
				String prefix = request.getSession().getServletContext()
						.getInitParameter("salt");
				String pass = prefix + request.getParameter("password") + user.getSalt();
				String hexPass = User.sha256(pass);
				if(user.getPassword().equals(hexPass)) {
					startSession(user);
					// redirect to home page
					response.sendRedirect(request.getContextPath());
					return;
				}
			}		
		}
		request.setAttribute("error-message", "Wrong username or password.");
		request.setAttribute("helper", helper);
		forward("/login.jsp");
	}

	private UserHelper initHelper(User user) {
		UserHelper helper = new UserHelper(user);
		helper.setUsername(request.getParameter("username"));
		helper.setPassword(request.getParameter("password"));
		return helper;
	}

}
