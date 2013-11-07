package dk.kea.si.movies.persistence.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dk.kea.si.movies.domain.Cache;
import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.util.ApplicationException;

public class CacheMapper extends AbstractMapper {

	public static final String COLUMNS = "Cache.id, Cache.hash," +
			" Cache.request, Cache.response, Cache.time";
	
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
		return "SELECT " + COLUMNS + " FROM Cache AS Cache" +
				" WHERE Cache.hash=?";
	}

	@Override
	protected String updateStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String insertStatement() {
		return "INSERT INTO Cache (hash, request, response)" +
				" VALUES (?, ?, ?)";
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
		// TODO Auto-generated method stub
		
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
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Cache cache = (Cache) load(rs);
				if(request.equals(cache.getRequest())) {
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
