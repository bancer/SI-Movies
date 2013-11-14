package dk.kea.si.movies.persistence.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dk.kea.si.movies.domain.AlternateIds;
import dk.kea.si.movies.domain.DomainObject;

public class AlternateIdsMapper extends AbstractMapper {

	public static final String ALIAS = "AlternateId";

	private static final String IMDB = "IMDB";

	public static final String ID = ALIAS + ".id";

	private static final String NAME = ALIAS + ".name";

	private static final String VALUE = ALIAS + ".value";

	public static final String MOVIE_ID = ALIAS + ".movie_id";

	public static final String COLUMNS = ID + ", " + MOVIE_ID + ", " + NAME
			+ ", " + VALUE;

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
	protected String updateStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String insertStatement() {
		return "INSERT INTO `AlternateID` (movie_id, name, value)"
				+ " VALUES (?, ?, ?);";
	}

	@Override
	protected String deleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
		AlternateIds alternateIds = new AlternateIds();
		alternateIds.setId(rs.getInt(ID));
		alternateIds.setMovieId(rs.getInt(MOVIE_ID));
		if (rs.getString(NAME).equals(IMDB)) {
			alternateIds.setImdb(rs.getString(VALUE));
		}
		return alternateIds;
	}

	@Override
	protected void doUpdate(DomainObject o, PreparedStatement s)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doInsert(DomainObject o, PreparedStatement s)
			throws SQLException {
		AlternateIds ids = (AlternateIds) o;
		s.setLong(1, ids.getMovieId());
		s.setString(2, IMDB);
		s.setString(3, ids.getImdb());
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
