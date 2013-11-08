package dk.kea.si.movies.persistence.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.domain.Ratings;

public class RatingsMapper extends AbstractMapper {

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
		return "INSERT INTO `CriticRating` (movie_id, type, score, rating)"
				+ " VALUES (?, ?, ?, ?), (?, ?, ?, ?);";
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
		Ratings ratings = (Ratings) o;
		s.setLong(1, ratings.getMovieId());
		s.setString(2, "CRITICS");
		s.setInt(3, ratings.getCritics_score());
		s.setString(4, ratings.getCritics_rating());
		s.setLong(5, ratings.getMovieId());
		s.setString(6, "AUDIENCE");
		s.setInt(7, ratings.getAudience_score());
		s.setString(8, ratings.getAudience_rating());
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
