package dk.kea.si.movies.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class SignOutCommand extends FrontCommand{

	@Override
	public void process() throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getHeader("referer"));
	}

	@Override
	public void processPost() throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
