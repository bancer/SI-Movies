package dk.kea.si.movies.persistence.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.domain.OpenID;
import dk.kea.si.movies.domain.User;
import dk.kea.si.movies.util.ApplicationException;


public class UserMapper extends AbstractMapper {

	public static final String COLUMNS = "User.id, User.address, User.email," +
			" User.first_name, User.last_name, User.user_name, User.phone";

	protected String findStatement() {
		return "SELECT " + COLUMNS + " FROM User AS User" +
				" WHERE User.id=?" +
				" LIMIT 1";
	}
	
	protected String findByOpenIdStatement() {
		return "SELECT " + COLUMNS + " FROM User AS User" +
				" LEFT JOIN open_ids AS OpenID" +
					" ON User.id=OpenID.user_id" +
				" WHERE OpenID.identifier=?" +
				" LIMIT 1";
	}

	@Override
	protected String findAllStatement() {
		return "SELECT " + COLUMNS + " FROM User AS User";
	}

	@Override
	protected String updateStatement() {
		return "UPDATE User SET address =?, email =?, first_name =?," +
				" last_name =?, user_name =?, phone =?" +
				" WHERE id=?";
	}

	@Override
	protected String insertStatement() {
		return "INSERT INTO User (address, email, first_name, last_name," +
				" user_name, phone)" +
				" VALUES (?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String deleteStatement() {
		return "DELETE FROM User WHERE id=?";
	}

	public User find(Long id) {
		return (User) abstractFind(id);
	}

	public User find(long id) {
		return (User) abstractFind(id);
	}

	protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("User.id"));
		user.setAddress(rs.getString("User.address"));
		user.setEmail(rs.getString("User.email"));
		user.setFirstName(rs.getString("User.first_name"));
		user.setLastName(rs.getString("User.last_name"));
		user.setUsername(rs.getString("User.user_name"));
		user.setPhone(rs.getString("User.phone"));
		return user;
	}

	@Override
	protected void doUpdate(DomainObject obj, PreparedStatement s)
			throws SQLException {
		User user = (User) obj;
		s.setString(1, user.getAddress());
		s.setString(2, user.getEmail());
		s.setString(3, user.getFirstName());
		s.setString(4, user.getLastName());
		s.setString(5, user.getUsername());
		s.setString(6, user.getPhone());
	}

	@Override
	protected void doInsert(DomainObject obj, PreparedStatement s)
			throws SQLException {
		User user = (User) obj;
		System.out.println(user);
		s.setString(1, user.getAddress());
		s.setString(2, user.getEmail());
		s.setString(3, user.getFirstName());
		s.setString(4, user.getLastName());
		s.setString(5, user.getUsername());
		s.setString(6, user.getPhone());
	}
	
	@Override
	public long insert(DomainObject obj) {
		long userId = super.insert(obj);
		ArrayList<OpenID> openIds = ((User) obj).getOpenIds();
		if (openIds != null) {
			for (int i = 0; i < openIds.size(); i++) {
				OpenID openId = openIds.get(i);
				openId.setUserId(userId);
				getMapper(OpenID.class).insert(openId);
			}
		}
		return userId;
	}

	@Override
	protected void doDelete(DomainObject obj, PreparedStatement s)
			throws SQLException {
		User user = (User) obj;
		s.setLong(1, user.getId());
	}

	public User findByOpenId(String identifier) {
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(findByOpenIdStatement());
			statement.setString(1, identifier);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				return (User) load(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			closeStatement(statement);
		}
	}
}
