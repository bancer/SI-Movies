package dk.kea.si.movies.commands;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import dk.kea.si.movies.components.Auth;
import dk.kea.si.movies.domain.Comment;

public class DeleteCommentCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processPost() throws ServletException, IOException {
		rejectUnauthenticatedUser();
		
		response.setContentType("text/HTML");
		PrintWriter out = response.getWriter();		
		
		if(hasValidCSRFToken() && isAuthorized(Auth.Action.COMMENT_DELETE)) {
			try {
				Comment comment = new Comment();
				comment.setId(Long.parseLong(request.getParameter("comment_id")));
				int res = getStorage().delete(comment);
				out.append("" + res);
			} catch (IllegalArgumentException e) {
				out.append(e.getMessage());
			}
		}
		
		out.flush();
	}
}
