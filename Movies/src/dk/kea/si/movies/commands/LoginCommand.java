package dk.kea.si.movies.commands;

import java.io.IOException;

import javax.servlet.ServletException;

import dk.kea.si.movies.domain.User;

public class LoginCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		forward("/login.jsp");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		User user = null;
		String errorMessage = "Wrong username or password.";
		try {
			User temp = new User();
			temp.setUserName(request.getParameter("username"));
			temp.setPassword(request.getParameter("password"));
			user = (User) getStorage().findByUserNameAndPassword(temp);
		} catch (IllegalArgumentException e) {
			errorMessage = e.getMessage();
		}
		if(user == null) {
			request.setAttribute("error-message", errorMessage);
			forward("/login.jsp");
		} else {
			// redirect to home page
			response.sendRedirect("#");
		}
	}

}
