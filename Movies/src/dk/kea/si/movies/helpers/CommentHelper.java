package dk.kea.si.movies.helpers;

import dk.kea.si.movies.domain.Comment;
import dk.kea.si.movies.domain.User;

public class CommentHelper extends AHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7701623489543110594L;

	private Comment comment;
	
	public CommentHelper() {
		this(new Comment());
	}

	public CommentHelper(Comment comment) {
		this.comment = comment;
	}

	public void setMovieId(String movieId) {
		try {
			comment.setMovieId(Long.parseLong(movieId));
		} catch (IllegalArgumentException e) {
			getErrors().put("movie_id", e.getMessage());
		}
	}

	public void setParentId(String parentId) {
		if(!parentId.isEmpty()) {
			try {
				comment.setParentId(Long.parseLong(parentId));
			} catch (IllegalArgumentException e) {
				getErrors().put("parent_id", e.getMessage());
			}
		}
	}

	public void setCommentText(String commentText) {
		try {
			commentText = breakLinesToHtml(commentText);
			comment.setComment(commentText);
		} catch (IllegalArgumentException e) {
			getErrors().put("comment_text", e.getMessage());
		}
	}

	public void setUserId(long userId) {
		try {
			comment.setUserId(userId);
		} catch (IllegalArgumentException e) {
			getErrors().put("user_id", e.getMessage());
		}
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getCommentText() {
		if(comment.getComment() != null) {
			return sanitize(comment.getComment());
		} else {
			return "";
		}
	}
	
	public String getAuthorDisplayName() {
		//System.out.println("x:"+comment.getUser().getDisplayName());
		if(comment.getUser() != null) {
			return sanitize(comment.getUser().getDisplayName());
		} else {
			return "";
		}
	}

	public void setUser(User user) {
		comment.setUser(user);
	}

	public String getId() {
		if(comment.getId() > 0) {
			return "" + comment.getId();
		} else {
			return "";
		}
	}
}
