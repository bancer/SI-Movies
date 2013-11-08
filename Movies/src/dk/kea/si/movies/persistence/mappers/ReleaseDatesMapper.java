package dk.kea.si.movies.persistence.mappers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.domain.ReleaseDates;

public class ReleaseDatesMapper extends AbstractMapper {
	
	public static final String COLUMNS = "Release.id, Release.movie_id,"
			+ " Release.type, Release.release_date";

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
		return "INSERT INTO `Release` (movie_id, type, release_date)"
				+ " VALUES (?, ?, ?), (?, ?, ?);";
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
		ReleaseDates releases = (ReleaseDates) o;
		s.setLong(1, releases.getMovieId());
		s.setString(2, "THEATER");
		s.setDate(3, Date.valueOf(releases.getTheater()));
		s.setLong(4, releases.getMovieId());
		s.setString(5, "DVD");
		s.setDate(6, Date.valueOf(releases.getDvd()));
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
