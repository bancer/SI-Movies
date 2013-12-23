package dk.kea.si.movies.commands;

import java.io.IOException;

import javax.servlet.ServletException;

import dk.kea.si.movies.domain.User;
import dk.kea.si.movies.helpers.UserHelper;
import dk.kea.si.movies.util.AppUtils;

public class RegisterCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		setCSRFProtectionKey();
		forward("/register.jsp");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		User user = new User();
		UserHelper helper = initHelper(user);
		if(hasValidCSRFToken()) {
			if(helper.getErrors().isEmpty()) {
				user.setDisplayName(user.getUserName());
				int countUsername = getStorage().countByUserName(user.getUserName(), User.class);
				int countEmail = getStorage().countByEmail(user.getEmail(), User.class);
				if(countUsername > 0) {
					String message = String.format("Username '%s' has already been registered.", user.getUserName());
					helper.getErrors().put("username", message);
				} else if(countEmail > 0) {
					String message = String.format("Email '%s' has already been registered.", user.getEmail());
					helper.getErrors().put("email", message);
				} else if(getStorage().save(user)) {
					startSession(user);
					response.sendRedirect("?command=User");
					return;
				} else {
					request.setAttribute("error.message", "Cannot register. Try again later.");
				}
			}
		}
		request.setAttribute("helper", helper);
		forward("/register.jsp");
	}

	private UserHelper initHelper(User user) {
		String salt = AppUtils.generateSalt();
		user.setSalt(salt);
		String prefix = request.getSession().getServletContext()
				.getInitParameter("salt");

		UserHelper helper = new UserHelper(user);
		helper.setUsername(request.getParameter("username"));
		helper.setEmail(request.getParameter("email"));
		helper.validatePassword(request.getParameter("password"));
		helper.setPassword(request.getParameter("password"));
		helper.comparePassword(request.getParameter("repeatpassword"));
		String password = prefix + request.getParameter("password") + salt;
		helper.setPassword(AppUtils.sha256(password));
		return helper;
	}

}
