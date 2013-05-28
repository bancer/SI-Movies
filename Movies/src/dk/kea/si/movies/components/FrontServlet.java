package dk.kea.si.movies.components;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.kea.si.movies.commands.FrontCommand;
import dk.kea.si.movies.commands.UnknownCommand;
import dk.kea.si.movies.util.ApplicationException;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet("/default.jsp")
public class FrontServlet extends HttpServlet {
	
	//private static final String GATEWAYS_PACKAGE = "com.marv.persistence.gateways";
	
	private static final String COMMANDS_PACKAGE = "dk.kea.si.movies.commands";
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FrontCommand command = getCommand(request);
		command.init(getServletContext(), request, response);
		command.process();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FrontCommand command = getCommand(request);
		command.init(getServletContext(), request, response);
		command.processPost();
	}

	private FrontCommand getCommand(HttpServletRequest request) {
		try {
			return (FrontCommand) getCommandClass(request).newInstance();
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	private Class<?> getCommandClass(HttpServletRequest request) {
		Class<?> result;
		String command = request.getParameter("command");
		if(command == null) {
			command = "Home";
		}
		final String commandClassName = COMMANDS_PACKAGE + "." + command + "Command";
		
		try {
			result = Class.forName(commandClassName);
		} catch (ClassNotFoundException e) {
			result = UnknownCommand.class;
		}
		return result;
	}

}
