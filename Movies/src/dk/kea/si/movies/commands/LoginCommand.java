package dk.kea.si.movies.commands;

import java.io.IOException;

import javax.servlet.ServletException;

public class LoginCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		
		
		forward("/login.jsp");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
