package dk.kea.si.movies.commands;

import java.io.IOException;

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
					String hexPass = makeSaltedPassword(user);
					if(user.getPassword().equals(hexPass)) {
						startSession(user);
						// redirect to home page
						response.sendRedirect(request.getContextPath());
						return;
					}
				}		
			}
			request.setAttribute(Constants.ERROR_MESSAGE_KEY, "Wrong username or password.");
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
		helper.setPassword(request.getParameter("password"));
		return helper;
	}

}
