package dk.kea.si.movies.commands;

import java.io.IOException;

import javax.servlet.ServletException;


public class UnknownCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		forward("/unknown.jsp");
	}

	@Override
	public void processPost() throws ServletException, IOException {
		// Nothing to process
	}

}
