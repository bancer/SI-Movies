package dk.kea.si.movies.commands;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import dk.kea.si.movies.domain.Comment;
import dk.kea.si.movies.domain.User;
import dk.kea.si.movies.helpers.CommentHelper;
import dk.kea.si.movies.util.Constants;

public class CommentCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		response.setContentType("text/HTML");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		if (!id.matches("\\d+")) { // if id is not a digit
			return;
		}
		Comment comment = (Comment) getStorage().find(Long.parseLong(id),
				Comment.class);
		String format = "{\"author\":\"%s\", \"text\":\"%s\"}";
		String json = String.format(format, comment.getUser()
				.getDisplayName(), comment.getComment());
		out.println(json);
		out.flush();
	}

	@Override
	public void processPost() throws ServletException, IOException {
		//System.out.println("save comment");
		rejectUnauthenticatedUser();
		
		response.setContentType("text/HTML");
		PrintWriter out = response.getWriter();
		
		CommentHelper helper = initHelper();
		
		if(hasValidCSRFToken()) {
			//System.out.println("csrf valid");
			if(helper.getErrors().isEmpty()) {
				//boolean id = getStorage().save(helper.getComment());
				long id = getStorage().insert(helper.getComment());
				out.print(id);
				//out.println(new Gson().toJson(helper.getComment()));
			} else {
				out.println(new JSONObject(helper.getErrors()));
			}
		}
		out.flush();
	}

	private CommentHelper initHelper() {
		CommentHelper helper = new CommentHelper();
		helper.setMovieId(request.getParameter("movie_id"));
		helper.setParentId(request.getParameter("parent_id"));
		helper.setCommentText(request.getParameter("comment_text"));		
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		helper.setUserId(user.getId());
		helper.setUser(user);
		return helper;
	}

}
