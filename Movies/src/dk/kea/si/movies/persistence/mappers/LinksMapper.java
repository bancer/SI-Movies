package dk.kea.si.movies.persistence.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.domain.Links;

public class LinksMapper extends AbstractMapper {

	public static final String ALIAS = "Link";

	public static final String ID = ALIAS + ".id";

	public static final String MOVIE_ID = ALIAS + ".movie_id";

	private static final String TYPE = ALIAS + ".type";

	private static final String URL = ALIAS + ".url";

	private static final String SELF = "SELF";

	private static final String ALTERNATE = "ALTERNATE";

	private static final String CAST = "CAST";

	private static final String CLIPS = "CLIPS";

	private static final String REVIEWS = "REVIEWS";
	
	private static final String SIMILAR = "SIMILAR";
	
	public static final String COLUMNS = ID + ", " + MOVIE_ID + ", " + TYPE
			+ ", " + URL;

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
	
	protected String findByMovieIdStatement() {
		return "SELECT " + COLUMNS + " FROM Link AS Link" + " WHERE "
				+ MOVIE_ID + "=?;";
	}

	@Override
	protected String updateStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String insertStatement() {
		return "INSERT INTO `Link` (movie_id, type, url)"
				+ " VALUES (?, ?, ?), (?, ?, ?), (?, ?, ?),"
				+ " (?, ?, ?), (?, ?, ?), (?, ?, ?);";
	}

	@Override
	protected String deleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doUpdate(DomainObject o, PreparedStatement s)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doInsert(DomainObject o, PreparedStatement s)
			throws SQLException {
		Links links = (Links) o;
		s.setLong(1, links.getMovieId());
		s.setString(2, SELF);
		s.setString(3, links.getSelf());
		s.setLong(4, links.getMovieId());
		s.setString(5, ALTERNATE);
		s.setString(6, links.getAlternate());
		s.setLong(7, links.getMovieId());
		s.setString(8, CAST);
		s.setString(9, links.getCast());
		s.setLong(10, links.getMovieId());
		s.setString(11, CLIPS);
		s.setString(12, links.getClips());
		s.setLong(13, links.getMovieId());
		s.setString(14, REVIEWS);
		s.setString(15, links.getReviews());
		s.setLong(16, links.getMovieId());
		s.setString(17, SIMILAR);
		s.setString(18, links.getSimilar());
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
