package dk.kea.si.movies.persistence.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dk.kea.si.movies.domain.Clip;
import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.domain.Links;

public class ClipMapper extends AbstractMapper {
	
	public static final String ALIAS = "Clip";

	public static final String ID = ALIAS + ".id";

	public static final String MOVIE_ID = ALIAS + ".movie_id";

	private static final String DURATION = ALIAS + ".duration";

	private static final String THUMBNAIL = ALIAS + ".thumbnail";

	private static final String LINK = ALIAS + ".link";
	
	public static final String COLUMNS = ID + ", " + MOVIE_ID + ", " + DURATION
			+ ", " + THUMBNAIL + ", " + LINK;

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
		return "INSERT INTO Clip (movie_id, duration, thumbnail, link)"
				+ " VALUES (?, ?, ?, ?);";
	}

	@Override
	protected String deleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
		Clip clip = new Clip();
		clip.setId(rs.getInt(ID));
		clip.setMovieId(rs.getInt(MOVIE_ID));
		clip.setDuration(rs.getInt(DURATION));
		clip.setThumbnail(rs.getString(THUMBNAIL));
		Links links = new Links();
		links.setAlternate(rs.getString(LINK));
		clip.setLinks(links);
		return clip;
	}

	@Override
	protected void doUpdate(DomainObject o, PreparedStatement s)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doInsert(DomainObject o, PreparedStatement s)
			throws SQLException {
		Clip clip = (Clip) o;
		s.setLong(1, clip.getMovieId());
		s.setInt(2, clip.getDuration());
		s.setString(3, clip.getThumbnail());
		s.setString(4, clip.getLinks().getAlternate());
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
