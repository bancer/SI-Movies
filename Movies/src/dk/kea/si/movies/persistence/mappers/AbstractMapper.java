package dk.kea.si.movies.persistence.mappers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.persistence.core.MapperFactory;
import dk.kea.si.movies.persistence.core.MysqlConnection;
import dk.kea.si.movies.util.ApplicationException;

/**
 * This class implements the logic of mapping domain objects to the database
 * tables.
 */
public abstract class AbstractMapper {

	/**
	 * Map of domain objects where key is value of id field of corresponding
	 * object.
	 */
	protected Map<Long, DomainObject> loadedMap = new HashMap<Long, DomainObject>();

	/**
	 * SQL statement to select one record from a table.
	 */
	protected abstract String findStatement();

	/**
	 * Gets a SQL statement retrieving all records from the table.
	 * 
	 * @return SELECT statement retrieving all records from the table.
	 */
	protected abstract String findAllStatement();

	/**
	 * Gets a SQL statement modifying a record in the table.
	 * 
	 * @return UPDATE statement modifying a row in the table.
	 */
	protected abstract String updateStatement();

	/**
	 * Gets a SQL statement inserting a record into the table.
	 * 
	 * @return INSERT statement creating a new row in the table.
	 */
	protected abstract String insertStatement();

	/**
	 * Gets a SQL statement removing a record from the table.
	 * 
	 * @return DELETE statement removing a row from the table.
	 */
	protected abstract String deleteStatement();

	/**
	 * Creates domain object from the ResultSet.
	 * 
	 * @param id
	 *            value of id field.
	 * @param rs
	 *            ResultSet holding the results of the SELECT query.
	 * @return domain object.
	 * @throws SQLException
	 */
	protected abstract DomainObject doLoad(Long id, ResultSet rs)
			throws SQLException;

	/**
	 * Sets UPDATE statement parameters.
	 * 
	 * @param o
	 *            modified domain object to be updated in the database.
	 * @param s
	 *            prepared statement object.
	 * @throws SQLException
	 */
	protected abstract void doUpdate(DomainObject o, PreparedStatement s)
			throws SQLException;

	/**
	 * Sets INSERT statement parameters.
	 * 
	 * @param o
	 *            newly created domain object to be saved into the database.
	 * @param s
	 *            prepared statement object.
	 * @throws SQLException
	 */
	protected abstract void doInsert(DomainObject o, PreparedStatement s)
			throws SQLException;

	/**
	 * Performs any operations after the object has been saved into the database
	 * using its auto-generated id. Override this method in concrete mapper
	 * classes.
	 * 
	 * @param obj
	 *            domain object that has been just saved.
	 * @param lastInsertId
	 *            id of the domain object that has been just saved.
	 * @throws DatabaseException
	 */
//	protected void doAfterInsert(DomainObject obj, long lastInsertId)
//			throws SQLException {
//	}

	/**
	 * Sets DELETE statement parameters.
	 * 
	 * @param o
	 *            domain object to be deleted from the database.
	 * @param s
	 *            prepared statement object.
	 * @throws SQLException
	 */
	protected abstract void doDelete(DomainObject o, PreparedStatement s)
			throws SQLException;

	/**
	 * Retrieves a row identified by the value of id column and converts that
	 * row into a domain object.
	 * 
	 * @param id
	 *            value of id column.
	 * @return domain object.
	 */
	public abstract DomainObject find(long id);

	/**
	 * Retrieves a row identified by the value of id column and converts that
	 * row into a domain object, saves the domain object in the loadedMap so
	 * that on subsequent requests the object is returned immediately from the
	 * map.
	 * 
	 * @param id
	 *            value of id column.
	 * @return domain object.
	 */
	protected DomainObject abstractFind(Long id) {
		DomainObject result = (DomainObject) loadedMap.get(id);
		if (result != null) {
			return result;
		}
		PreparedStatement findStatement = null;
		try {
			findStatement = getConnection().prepareStatement(findStatement());
			findStatement.setLong(1, id.longValue());
			System.out.println(findStatement);
			ResultSet rs = findStatement.executeQuery();
			if(rs.next()) {
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

	/**
	 * Retrieves the whole table and converts each row into a domain object.
	 * 
	 * @return list of domain objects.
	 */
	public ArrayList<DomainObject> findAll() {
		ArrayList<DomainObject> result = new ArrayList<DomainObject>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = getConnection().prepareStatement(
					findAllStatement());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result.add(load(resultSet));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			closeStatement(preparedStatement);
		}
		return result;
	}

	/**
	 * Verifies if the domain object is in the loaded map. If it is not then
	 * extracts the data from the result set and transforms it to the domain
	 * object. If the domain object was not in the map then puts it there.
	 * 
	 * @param rs
	 *            result set with domain object record.
	 * @return domain object.
	 * @throws SQLException
	 */
	protected DomainObject load(ResultSet rs) throws SQLException {
		Long id = new Long(rs.getLong(1));
		if (loadedMap.containsKey(id)) {
			return (DomainObject) loadedMap.get(id);
		}
		DomainObject result = doLoad(id, rs);
		loadedMap.put(id, result);
		return result;
	}

	/**
	 * Database connection getter.
	 * 
	 * @return database connection.
	 */
	protected Connection getConnection() {
		return MysqlConnection.getInstance().getConnection();
	}

	/**
	 * Releases this PreparedStatement object's database and JDBC resources
	 * immediately instead of waiting for this to happen when it is
	 * automatically closed.
	 * 
	 * @param statement last executed statement.
	 */
	protected void closeStatement(PreparedStatement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}

	public int update(DomainObject obj) {
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(updateStatement());
			// doBeforeUpdate(obj);
			doUpdate(obj, statement);
			loadedMap.put(obj.getId(), obj);
			// doAfterUpdate(obj);
			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			closeStatement(statement);
		}
	}

	public long insert(DomainObject obj) {
		PreparedStatement insertStatement = null;
		try {
			insertStatement = getConnection().prepareStatement(
					insertStatement(), Statement.RETURN_GENERATED_KEYS);
			doInsert(obj, insertStatement);
			System.out.println(insertStatement);
			insertStatement.execute();
			Long lastInsertId = findLastInsertId(insertStatement);
			//doAfterInsert(obj, lastInsertId);
			obj.setId(lastInsertId);
			loadedMap.put(lastInsertId, obj);
			return lastInsertId;
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			closeStatement(insertStatement);
		}
	}

	public int delete(DomainObject obj) {
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(deleteStatement());
			// doBeforeDelete();
			doDelete(obj, statement);
			if (loadedMap.containsKey(obj.getId())) {
				loadedMap.remove(obj.getId());
			}
			// doAfterDelete();
			return statement.executeUpdate();
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			closeStatement(statement);
		}
	}

	/**
	 * Retrieves the id of the last domain object that was saved in the
	 * database.
	 * 
	 * @param statement
	 *            prepared statement with result of the INSERT query.
	 * @return id of the last domain object that was saved in the database.
	 * @throws SQLException
	 */
	protected long findLastInsertId(PreparedStatement statement)
			throws SQLException {
		long autoIncKeyFromApi = -1L;
		ResultSet rs = statement.getGeneratedKeys();
		if (rs.next()) {
			autoIncKeyFromApi = rs.getLong(1);
		}
		return autoIncKeyFromApi;
	}
	
	/**
	 * Converts java.util.Calendar object to java.sql.Date object.
	 * 
	 * @param 	calendar to be converted.
	 * @return	SQL date.
	 */
	protected Date calendarToDate(Calendar calendar) {
		return new Date(calendar.getTime().getTime());
	}
	
	protected AbstractMapper getMapper(Class<? extends Object> domainClass) {
		return MapperFactory.getInstance().getMapper(domainClass);
	}
	
	protected void startTransaction() throws SQLException {
		System.out.println("START TRANSACTION");
		getConnection().setAutoCommit(false);
	}
	
	protected void commitTransaction() throws SQLException {
		System.out.println("COMMIT TRANSACTION");
		getConnection().commit();
	}
	
	protected void rollbackTransaction() {
		System.out.println("ROLLBACK TRANSACTION");
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}
	
	protected void endTransaction() {
		System.out.println("END TRANSACTION");
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			throw new ApplicationException(e);
		}
	}
}
