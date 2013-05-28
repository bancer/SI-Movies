package dk.kea.si.movies.commands;

import java.io.IOException;

import javax.servlet.ServletException;

public class HomeCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		forward("/home.jsp");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
