package dk.kea.si.movies.persistence.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.domain.Movie;
import dk.kea.si.movies.domain.Movie.Timeline;
import dk.kea.si.movies.domain.Posters;
import dk.kea.si.movies.domain.Ratings;
import dk.kea.si.movies.domain.ReleaseDates;
import dk.kea.si.movies.util.ApplicationException;

public class MovieMapper extends AbstractMapper {

	public static final String COLUMNS = "Movie.id, Movie.title, Movie.year,"
			+ " Movie.timeline, Movie.runtime, Movie.mpaa_rating,"
			+ " Movie.users_rating_score, Movie.studio, Movie.critics_consensus,"
			+ " Movie.synopsis";

	@Override
	protected String findStatement() {
		return "SELECT " + COLUMNS + " FROM Movie AS Movie"
				+ " WHERE Movie.id=?" + " LIMIT 1";
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
		return "INSERT INTO Movie (id, title, year, timeline, runtime,"
				+ " mpaa_rating, users_rating_score, studio, critics_consensus,"
				+ " synopsis) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	protected String deleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
		Movie movie = new Movie();
		movie.setId(rs.getLong("Movie.id"));
		movie.setTitle(rs.getString("Movie.title"));
		movie.setYear(rs.getString("Movie.year"));
		movie.setTimeline(Timeline.valueOf(rs.getString("Movie.timeline")));
		movie.setRuntime("" + rs.getInt("Movie.runtime"));
		movie.setMpaa_rating(rs.getString("Movie.mpaa_rating"));
		movie.setUsersRatingScore(rs.getFloat("Movie.users_rating_score"));
		movie.setStudio(rs.getString("Movie.studio"));
		movie.setCritics_consensus(rs.getString("Movie.critics_consensus"));
		movie.setSynopsis(rs.getString("Movie.synopsis"));
		return movie;
	}

	@Override
	protected void doUpdate(DomainObject o, PreparedStatement s)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doInsert(DomainObject o, PreparedStatement s)
			throws SQLException {
		Movie movie = (Movie) o;
		s.setLong(1, movie.getId());
		s.setString(2, movie.getTitle());
		s.setString(3, movie.getYear());
		s.setString(4, movie.getTimeline().toString());
		s.setInt(5, Integer.parseInt(movie.getRuntime()));
		s.setString(6, movie.getMpaa_rating());
		s.setFloat(7, movie.getUsersRatingScore());
		s.setString(8, movie.getStudio());
		s.setString(9, movie.getCritics_consensus());
		s.setString(10, movie.getSynopsis());
	}

	@Override
	protected void doDelete(DomainObject o, PreparedStatement s)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public DomainObject find(long id) {
		DomainObject result = (DomainObject) loadedMap.get(id);
		if (result != null) {
			return result;
		}
		PreparedStatement findStatement = null;
		try {
			findStatement = getConnection().prepareStatement(findStatement());
			findStatement.setLong(1, id);
			System.out.println(findStatement);
			ResultSet rs = findStatement.executeQuery();
			if (rs.next()) {
				return load(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			closeStatement(findStatement);
		}
	}
	
	public long insert(DomainObject obj) {
		PreparedStatement insertStatement = null;
		try {
			startTransaction();
			
			insertStatement = getConnection().prepareStatement(
					insertStatement());
			doInsert(obj, insertStatement);
			System.out.println(insertStatement);
			insertStatement.execute();

			Movie movie = (Movie)obj;
			Long lastInsertId = movie.getId();
			obj.setId(lastInsertId);
			loadedMap.put(lastInsertId, obj);
			
			ReleaseDates releases = movie.getRelease_dates();
			releases.setMovieId(movie.getId());
			getMapper(ReleaseDates.class).insert(releases);
			
			Posters posters = movie.getPosters();
			posters.setMovieId(movie.getId());
			getMapper(Posters.class).insert(posters);
			
			Ratings ratings = movie.getRatings();
			ratings.setMovieId(movie.getId());
			getMapper(Ratings.class).insert(ratings);
			
			commitTransaction();
			return lastInsertId;
		} catch (Exception e) {
			rollbackTransaction();
			throw new ApplicationException(e);
		} finally {
			closeStatement(insertStatement);
			endTransaction();
		}
	}
}
