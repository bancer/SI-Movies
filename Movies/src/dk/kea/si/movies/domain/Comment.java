package dk.kea.si.movies.domain;

import java.util.Calendar;

public class Comment extends DomainObject {

	private long movieId;
	
	private long userId;
	
	private User user;
	
	private long parentId;
	
	private long lastEditorId;
	
	private String comment;
	
	private Calendar timePosted;
	
	private Calendar lastTimeEdited;

	@Override
	public String toString() {
		return String
				.format("Comment [movieId=%s, userId=%s, user=%s, parentId=%s, lastEditorId=%s, comment=%s, timePosted=%s, lastTimeEdited=%s, getId()=%s]",
						movieId, userId, user, parentId, lastEditorId, comment,
						timePosted, lastTimeEdited, getId());
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
		if(movieId < 1L) {
			throw new IllegalArgumentException("ID cannot be less than 1.");
		}
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
		if(movieId < 1L) {
			throw new IllegalArgumentException("ID cannot be less than 1.");
		}
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
		if(movieId < 1L) {
			throw new IllegalArgumentException("ID cannot be less than 1.");
		}
	}

	public long getLastEditorId() {
		return lastEditorId;
	}

	public void setLastEditorId(long lastEditorId) {
		this.lastEditorId = lastEditorId;
		if(movieId < 1L) {
			throw new IllegalArgumentException("ID cannot be less than 1.");
		}
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Calendar getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(Calendar timePosted) {
		this.timePosted = timePosted;
	}

	public Calendar getLastTimeEdited() {
		return lastTimeEdited;
	}

	public void setLastTimeEdited(Calendar lastTimeEdited) {
		this.lastTimeEdited = lastTimeEdited;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
