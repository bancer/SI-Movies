package dk.kea.si.movies.persistence.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import dk.kea.si.movies.domain.Comment;
import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.domain.User;

public class CommentMapper extends AbstractMapper {
	
	private static final String ID = "Comment.id";

	private static final String MOVIE_ID = "Comment.movie_id";

	private static final String USER_ID = "Comment.user_id";

	private static final String PARENT_ID = "Comment.parent_id";

	private static final String LAST_EDITOR_ID = "Comment.last_editor_id";

	private static final String COMMENT = "Comment.comment";

	private static final String TIME_POSTED = "Comment.time_posted";

	private static final String LAST_TIME_EDITED = "Comment.last_time_edited";
	
	public static final String COLUMNS = ID + ", " + MOVIE_ID + ", " + USER_ID
			+ ", " + PARENT_ID + ", " + LAST_EDITOR_ID + ", " + COMMENT + ", "
			+ TIME_POSTED + ", " + LAST_TIME_EDITED;

	@Override
	protected String findStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String findAllStatement() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String findLatestListByMovieIdStatement() {
		return "SELECT " + COLUMNS + ", " + UserMapper.COLUMNS + 
				" FROM Comment AS Comment" +
				" LEFT JOIN User AS User" +
				" ON Comment.user_id=User.id" +
				" WHERE Comment.movie_id=?" +
				" ORDER BY COALESCE(Comment.last_time_edited, Comment.time_posted) DESC" +
				" LIMIT 20";
	}

	@Override
	protected String updateStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String insertStatement() {
		return "INSERT INTO Comment (movie_id, user_id, parent_id, comment)" +
				" VALUES (?, ?, ?, ?);";
	}

	@Override
	protected String deleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
		Comment comment = new Comment();
		comment.setId(rs.getLong(ID));
		comment.setMovieId(rs.getLong(MOVIE_ID));
		comment.setUserId(rs.getLong(USER_ID));
		comment.setParentId(rs.getLong(PARENT_ID));
		comment.setLastEditorId(rs.getLong(LAST_EDITOR_ID));
		comment.setComment(rs.getString(COMMENT));
		comment.setTimePosted(timestampToCalendar(rs.getTimestamp(TIME_POSTED)));
		comment.setLastTimeEdited(timestampToCalendar(rs.getTimestamp(LAST_TIME_EDITED)));

		if(resultSetContainsColumn(rs, UserMapper.ID)) {
			User user = (User) getMapper(User.class).doLoad(rs.getLong(UserMapper.ID), rs);
			comment.setUser(user);
		}
		//System.out.println(comment.getUser());
		return comment;
	}

	@Override
	protected void doUpdate(DomainObject o, PreparedStatement s)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doInsert(DomainObject o, PreparedStatement s)
			throws SQLException {
		Comment comment = (Comment) o;
		s.setLong(1, comment.getMovieId());
		s.setLong(2, comment.getUserId());
		if(comment.getParentId() > 0) {
			s.setLong(3, comment.getParentId());
		} else {
			s.setNull(3, Types.INTEGER);
		}
		s.setString(4, comment.getComment());
	}

	@Override
	protected void doDelete(DomainObject o, PreparedStatement s)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public DomainObject find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
