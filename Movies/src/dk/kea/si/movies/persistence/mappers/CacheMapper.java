package dk.kea.si.movies.persistence.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import dk.kea.si.movies.domain.Cache;
import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.util.ApplicationException;

public class CacheMapper extends AbstractMapper {

	private static final String ID = "Cache.id";

	private static final String HASH = "Cache.hash";

	private static final String REQUEST = "Cache.request";

	private static final String RESPONSE = "Cache.response";

	private static final String TIME = "Cache.time";

	public static final String COLUMNS = ID + ", " + HASH + ", " + REQUEST
			+ ", " + RESPONSE + ", " + TIME;

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

	protected String findByHashStatement() {
		return "SELECT " + COLUMNS + " FROM Cache AS Cache" + " WHERE " + HASH
				+ "=?";
	}

	@Override
	protected String updateStatement() {
		return "UPDATE `Cache` SET hash =?, request =?, response =?, time =?"
				+ " WHERE id=?";
	}

	@Override
	protected String insertStatement() {
		return "INSERT INTO `Cache` (hash, request, response)"
				+ " VALUES (?, ?, ?)";
	}

	@Override
	protected String deleteStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
		Cache cache = new Cache();
		cache.setId(rs.getLong(ID));
		cache.setHash(rs.getInt(HASH));
		cache.setRequest(rs.getString(REQUEST));
		cache.setResponse(rs.getString(RESPONSE));
		cache.setTimestamp((long) (rs.getTimestamp(TIME).getTime() / 1000L));
		return cache;
	}

	@Override
	protected void doUpdate(DomainObject o, PreparedStatement s)
			throws SQLException {
		Cache cache = (Cache) o;
		s.setLong(1, cache.getHash());
		s.setString(2, cache.getRequest());
		s.setString(3, cache.getResponse());
		s.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
		s.setLong(5, cache.getId());
	}

	@Override
	protected void doInsert(DomainObject o, PreparedStatement s)
			throws SQLException {
		Cache cache = (Cache) o;
		s.setLong(1, cache.getHash());
		s.setString(2, cache.getRequest());
		s.setString(3, cache.getResponse());
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

	public Cache find(String request) {
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(findByHashStatement());
			statement.setInt(1, request.hashCode());
			System.out.println(statement);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Cache cache = (Cache) load(rs);
				if (request.equals(cache.getRequest())) {
					return cache;
				}
			}
			return null;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			closeStatement(statement);
		}
	}

}
