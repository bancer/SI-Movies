package dk.kea.si.movies.persistence.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.domain.Posters;

public class PostersMapper extends AbstractMapper {

	public static final String COLUMNS = "Poster.id, Poster.movie_id,"
			+ " Poster.type, Poster.url";

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
		return "INSERT INTO `Poster` (movie_id, type, url)"
				+ " VALUES (?, ?, ?), (?, ?, ?), (?, ?, ?), (?, ?, ?);";
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
		Posters posters = (Posters) o;
		s.setLong(1, posters.getMovieId());
		s.setString(2, "THUMBNAIL");
		s.setString(3, posters.getThumbnail());
		s.setLong(4, posters.getMovieId());
		s.setString(5, "PROFILE");
		s.setString(6, posters.getProfile());
		s.setLong(7, posters.getMovieId());
		s.setString(8, "DETAILED");
		s.setString(9, posters.getDetailed());
		s.setLong(10, posters.getMovieId());
		s.setString(11, "ORIGINAL");
		s.setString(12, posters.getOriginal());
		
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
