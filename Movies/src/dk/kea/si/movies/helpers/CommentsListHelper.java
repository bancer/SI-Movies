package dk.kea.si.movies.helpers;

import java.util.ArrayList;

import dk.kea.si.movies.domain.Comment;

public class CommentsListHelper extends AHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8250452394896066767L;

	private ArrayList<Comment> comments;
	
	private CommentHelper commentHelper;
	
	public CommentsListHelper() {
		this(new ArrayList<Comment>());
	}
	
	public CommentsListHelper(ArrayList<Comment> comments) {
		this.comments = comments;
		this.commentHelper = new CommentHelper();
	}
	
	public int size() {
		return comments.size();
	}
	
	public String getCommentText(int index) {
		commentHelper.setComment(comments.get(index));
		return commentHelper.getCommentText();
	}
	
	public String getAuthorDisplayName(int index) {
		commentHelper.setComment(comments.get(index));
		return commentHelper.getAuthorDisplayName();
	}
	
	public String getCommentId(int index) {
		commentHelper.setComment(comments.get(index));
		return commentHelper.getId();
	}
}
