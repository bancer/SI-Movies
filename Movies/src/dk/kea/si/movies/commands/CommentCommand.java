package dk.kea.si.movies.commands;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import dk.kea.si.movies.domain.Comment;
import dk.kea.si.movies.helpers.CommentHelper;

public class CommentCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("get save command");
		//processPost();
	}

	@Override
	public void processPost() throws ServletException, IOException {
		Comment comment = new Comment();
		CommentHelper helper = new CommentHelper();
		if(hasValidCSRFToken()) {
			if(helper.getErrors().isEmpty()) {
				
			}
		}
		//System.out.println(request.getAttributeNames().toString());
		//TODO: get the comment data from the request and save it to the database
		// then sanitize the user supplied data and return it with PrintWriter
		response.setContentType("text/HTML");
		PrintWriter out = response.getWriter();
		out.println("success");
		out.flush();
	}

}
