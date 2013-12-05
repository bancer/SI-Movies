package dk.kea.si.movies.commands;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import javax.servlet.ServletException;
import javax.xml.bind.DatatypeConverter;

import dk.kea.si.movies.domain.User;

public class RegisterCommand extends FrontCommand {
	
	private static final Random random = new SecureRandom();

	@Override
	public void process() throws ServletException, IOException {
		forward("/register.jsp");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		boolean saved = false;
		User user = new User();
		if(formValidates(user)) {
			user.setDisplayName(user.getUserName());
			saved = getStorage().save(user);
		}
		if(!saved) {
			request.setAttribute("error.message", "Cannot register. Try again later.");
			forward("/register.jsp");
		} else {
			startSession(user);
			response.sendRedirect("?command=User");
		}
	}

	private boolean formValidates(User user) {
		//TODO: implement user helper
		String password = request.getParameter("password");
		String repeatedPassword = request.getParameter("repeat_password");
		boolean validates = true;
		if(password.equals(repeatedPassword)) {
			try {
				user.setUserName(request.getParameter("username"));
			} catch (IllegalArgumentException e) {
				request.setAttribute("error.username", e.getMessage());
				validates = false;
			}
			try {
				String salt = generateSalt();
				user.setSalt(salt);
				String prefix = request.getSession().getServletContext()
						.getInitParameter("salt");
				user.setPassword(prefix + password + salt);
			} catch (IllegalArgumentException e) {
				request.setAttribute("error.password", e.getMessage());
				validates = false;
			}
			try {
				user.setEmail(request.getParameter("email"));
			} catch (IllegalArgumentException e) {
				request.setAttribute("error.email", e.getMessage());
				validates = false;
			}
			
		} else {
			request.setAttribute("error.repeat_password", "Passwords do not match.");
			validates = false;
		}
		return validates;
	}

	private String generateSalt() {
		byte bytes[] = new byte[32];
		random.nextBytes(bytes);
		return DatatypeConverter.printHexBinary(bytes);
	}

}
