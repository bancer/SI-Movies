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
		// TODO Auto-generated method stub
		//System.out.println("get save command");
		//processPost();
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
				out.println(id);
				//out.println(new Gson().toJson(helper.getComment()));
			} else {
				out.println(new JSONObject(helper.getErrors()));
			}
		}
		//System.out.println(request.getAttributeNames().toString());
		//TODO: get the comment data from the request and save it to the database
		// then sanitize the user supplied data and return it with PrintWriter
		//out.println("success");
		out.flush();
	}

	private CommentHelper initHelper() {
		CommentHelper helper = new CommentHelper();
		helper.setMovieId(request.getParameter("movie_id"));
		helper.setParentId(request.getParameter("parent_id"));
		helper.setCommentText(request.getParameter("comment_text"));		
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		helper.setUserId(user.getId());
		return helper;
	}

}
