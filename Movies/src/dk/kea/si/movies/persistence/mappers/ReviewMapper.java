package dk.kea.si.movies.persistence.mappers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.domain.Review;

public class ReviewMapper extends AbstractMapper {

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
		return "INSERT INTO `Review` (movie_id, critic, date, freshness,"
				+ " publication, quote, link, country)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
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
		Review review = (Review) o;
		s.setLong(1, review.getMovieId());
		s.setString(2, review.getCritic());
		s.setDate(3, Date.valueOf(review.getDate()));
		s.setString(4, review.getFreshness());
		s.setString(5, review.getPublication());
		s.setString(6, review.getQuote());
		s.setString(7, review.getLinks().getReview());
		s.setString(8, review.getCountry());
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
