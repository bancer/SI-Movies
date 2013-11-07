package dk.kea.si.movies.persistence.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dk.kea.si.movies.util.ApplicationException;


/**
 * This class manages the connection to MySQL database.
 * Singleton pattern is used for the implementation.
 */
public class MysqlConnection {

	/**
	 * JDBC driver class for MySQL.
	 */
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * A database URL of the form jdbc:subprotocol:subname
	 */
	private static final String DB_URL = "jdbc:mysql://localhost/movies";

	/**
	 * The database user on whose behalf the connection is being made.
	 */
	private static final String DB_USER = "root";

	/**
	 * The user's password.
	 */
	private static final String DB_PASSWORD = "admin";
	
	/**
	 * Instance of the MysqlConnection object.
	 */
	private static MysqlConnection instance = null;

	/**
	 * Connection to the database.
	 */
	private static Connection connection = null;

	/**
	 * Private constructor to prohibit the creation of
	 * MysqlConnection object from outside this class.
	 */
	private MysqlConnection() {
	}

	/**
	 * Creates and returns a connection to the database.
	 * 
	 * @return	an instance of the connection to the database.
	 */
	public static MysqlConnection getInstance() {
		if(instance == null) {
			instance = new MysqlConnection();
		}
		return instance;
	}

	/**
	 * Creates and returns a connection to the database.
	 * 
	 * @return the connection to the database.
	 * @throws DatabaseException 
	 */
	public Connection getConnection() {
		if(connection == null) {
			try {
				Class.forName(DB_DRIVER);
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			} catch (ClassNotFoundException e) {
				throw new ApplicationException(e);
			} catch (SQLException e) {
				throw new ApplicationException(e);
			}
		}
		return connection;
	}
}
