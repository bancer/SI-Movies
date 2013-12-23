package dk.kea.si.movies.commands;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;

import dk.kea.si.movies.domain.User;
import dk.kea.si.movies.helpers.UserHelper;
import dk.kea.si.movies.util.AppUtils;
import dk.kea.si.movies.util.Constants;

public class LoginCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		setCSRFProtectionKey();
		forward("/login.jsp");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		User temp = new User();
		UserHelper helper = initHelper(temp);
		if(hasValidCSRFToken()) {
			if(helper.getErrors().isEmpty()) {
				User user = (User) getStorage().findByUserName(temp);
				if(user != null) {
					long loginTimeout = getStorage().getUserLoginTimeout(user);
					if(loginTimeout > 0) {
						String message = "You cannot login during the next %s min %s sec.";
						message = String.format(message,
								loginTimeout / 60, 
								loginTimeout % 60);
						System.out.println(message);
						request.setAttribute(Constants.ERROR_MESSAGE_KEY, message);
					} else {
						String hexPass = makeSaltedPassword(user);
						if(user.getPassword().equals(hexPass)) {
							startSession(user);
							// redirect to home page
							response.sendRedirect(request.getContextPath());
							return;
						} else {
							//TODO: If the same username is wrong for 3 consecutive
							// times the user is not allowed to login for 5 minutes
							Enumeration<String> attributes = request.getSession().getAttributeNames();
							Integer count = (Integer) request.getSession().getAttribute("failed_login_count");
							System.out.println(count);
							if(count == null) {
								count = 1;
							} else if(count < 3) {
								count++;
							} else {
								getStorage().blockUser(user);
								count = 0;
							}
							request.getSession().setAttribute("failed_login_count", count);
						}
						request.setAttribute(Constants.ERROR_MESSAGE_KEY, "Wrong username or password.");
					}
				} else {
					request.setAttribute(Constants.ERROR_MESSAGE_KEY, "Wrong username or password.");
				}
			}
		}
		request.setAttribute("helper", helper);
		forward("/login.jsp");
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
