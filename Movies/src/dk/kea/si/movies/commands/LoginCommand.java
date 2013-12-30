package dk.kea.si.movies.commands;

import java.io.IOException;

import javax.servlet.ServletException;

import dk.kea.si.movies.domain.User;
import dk.kea.si.movies.helpers.UserHelper;
import dk.kea.si.movies.util.AppUtils;
import dk.kea.si.movies.util.Constants;

public class LoginCommand extends FrontCommand {

	private static final int MAX_LOGIN_ATTEMPTS = 3;

	@Override
	public void process() throws ServletException, IOException {
		setCSRFProtectionKey();
		forward("/login.jsp");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		UserHelper helper = initHelper(new User());
		request.setAttribute("helper", helper);
		User user = authenticate(helper);
		if(user != null) {
			startSession(user);
			// redirect to home page
			response.sendRedirect(request.getContextPath());
			return;
		} else {
			forward("/login.jsp");
		}
	}
	
	private User authenticate(UserHelper helper) {
		if(hasValidCSRFToken()) {
			if(helper.getErrors().isEmpty()) {
				String message = "Wrong username or password.";
				User user = (User) getStorage().findByUserName(helper.getUser());
				if(user != null) {
					long loginTimeout = getStorage().getUserLoginTimeout(user.getId());
					if(loginTimeout > 0) {
						message = String.format(
								"You cannot login during the next %s min %s sec.",
								loginTimeout / 60, loginTimeout % 60);
					} else {
						String hexPass = makeSaltedPassword(user);
						if(user.getPassword().equals(hexPass)) {
							return user;
						} else {
							Integer leftLoginAttempts = (Integer) request.getSession().getAttribute("failed_login_countdown");
							if(leftLoginAttempts == null || leftLoginAttempts < 0) {
								leftLoginAttempts = MAX_LOGIN_ATTEMPTS - 1;
							}
							if (leftLoginAttempts == 0) {
								getStorage().blockUser(user);
							}
							message = String.format(
									message + "\nYou have %s attempts left.", 
									leftLoginAttempts);
							request.getSession().setAttribute("failed_login_countdown", --leftLoginAttempts);
						}
					}
				}
				helper.setError(Constants.ERROR_MESSAGE_KEY, message);
			}
		}
		return null;
	}

	private String makeSaltedPassword(User user) {
		String prefix = request.getSession().getServletContext()
				.getInitParameter("salt");
		String pass = prefix + request.getParameter("password") + user.getSalt();
		return AppUtils.sha256(pass);
	}

	private UserHelper initHelper(User user) {
		UserHelper helper = new UserHelper(user);
		helper.setUsername(request.getParameter("username"));
		helper.setPassword(AppUtils.sha256(request.getParameter("password")));
		return helper;
	}

}
