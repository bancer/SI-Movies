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

	public static final String ID = "User.id";

	private static final String ADDRESS = "User.address";

	private static final String EMAIL = "User.email";

	private static final String FIRST_NAME = "User.first_name";

	private static final String LAST_NAME = "User.last_name";

	private static final String DISPLAY_NAME = "User.display_name";

	private static final String PHONE = "User.phone";

	private static final String USERNAME = "User.username";

	private static final String PASSWORD = "User.password";

	private static final String SALT = "User.salt";

	private static final String BLOCKED_UNTIL = "User.blocked_until";
	
	private static final String ROLE = "User.role";
	
	public static final String COLUMNS = ID + ", " + ADDRESS + ", " + EMAIL
			+ ", " + FIRST_NAME + ", " + LAST_NAME + ", " + DISPLAY_NAME + ", "
			+ PHONE + ", " + USERNAME + ", " + PASSWORD + ", " + SALT + ", "
			+ BLOCKED_UNTIL + ", " + ROLE;

	protected String findStatement() {
		return "SELECT " + COLUMNS + " FROM User AS User" +
				" WHERE User.id=?" +
				" LIMIT 1;";
	}
	
	protected String findByOpenIdStatement() {
		return "SELECT " + COLUMNS + " FROM User AS User" +
				" LEFT JOIN open_ids AS OpenID" +
					" ON User.id=OpenID.user_id" +
				" WHERE OpenID.identifier=?" +
				" LIMIT 1;";
	}

	private String findByUsernameStatement() {
		return "SELECT " + COLUMNS +
				" FROM User AS User" +
				" LEFT JOIN OpenID AS OpenID" +
					" ON User.id=OpenID.user_id" +
				" WHERE User.username=?" +
				" LIMIT 1;";
	}

	@Override
	protected String findAllStatement() {
		return "SELECT " + COLUMNS + " FROM User AS User";
	}

	@Override
	protected String updateStatement() {
		return "UPDATE User SET address =?, email =?, first_name =?," +
				" last_name =?, username =?, phone =?" +
				" WHERE id=?";
	}

	private String blockUserStatement() {
		return 
			"UPDATE User SET blocked_until=NOW() + INTERVAL 5 MINUTE WHERE id=?";
	}

	@Override
	protected String insertStatement() {
		return "INSERT INTO User (address, email, first_name, last_name," +
				" display_name, phone, username, password, salt)" +
				" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	protected String deleteStatement() {
		return "DELETE FROM User WHERE id=?";
	}
	
	@Override
	protected String countByUserNameStatement() {
		return "SELECT COUNT(*) FROM User WHERE username=?;";
	}
	
	@Override
	protected String countByEmailStatement() {
		return "SELECT COUNT(*) FROM User WHERE email=?;";
	}

	private String getLoginTimeoutStatement() {
		return "SELECT TIMESTAMPDIFF(" +
					"SECOND, NOW(), (SELECT blocked_until FROM User WHERE id=?)" +
				");";
	}

	public User find(Long id) {
		return (User) abstractFind(id);
	}

	public User find(long id) {
		return (User) abstractFind(id);
	}

	protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(ID));
		user.setAddress(rs.getString(ADDRESS));
		user.setEmail(rs.getString(EMAIL));
		user.setFirstName(rs.getString(FIRST_NAME));
		user.setLastName(rs.getString(LAST_NAME));
		user.setDisplayName(rs.getString(DISPLAY_NAME));
		user.setPhone(rs.getString(PHONE));
		user.setUserName(rs.getString(USERNAME));
		user.setPassword(rs.getString(PASSWORD));
		user.setSalt(rs.getString(SALT));
		user.setBlockedUntil(rs.getTimestamp(BLOCKED_UNTIL));
		user.setRole(User.Role.valueOf(rs.getString(ROLE)));
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
		s.setString(5, user.getDisplayName());
		s.setString(6, user.getPhone());
	}

	@Override
	protected void doInsert(DomainObject obj, PreparedStatement s)
			throws SQLException {
		User user = (User) obj;
		s.setString(1, user.getAddress());
		s.setString(2, user.getEmail());
		s.setString(3, user.getFirstName());
		s.setString(4, user.getLastName());
		s.setString(5, user.getDisplayName());
		s.setString(6, user.getPhone());
		s.setString(7, user.getUserName());
		s.setString(8, user.getPassword());
		s.setString(9, user.getSalt());
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

	public DomainObject findByUsername(User user) {
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(findByUsernameStatement());
			statement.setString(1, user.getUserName());
			System.out.println(statement);
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

	public void block(long userId) {
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(blockUserStatement());
			statement.setLong(1, userId);
			System.out.println(statement);
			statement.executeUpdate();
			if (loadedMap.containsKey(userId)) {
				loadedMap.remove(userId);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			closeStatement(statement);
		}
	}

	public long getLoginTimeout(long userId) {
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(getLoginTimeoutStatement());
			statement.setLong(1, userId);
			System.out.println(statement);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			closeStatement(statement);
		}
	}
}
