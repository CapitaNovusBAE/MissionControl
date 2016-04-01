package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ali Gurlek
 *
 */
public class DAOConnector {

	private static final String URL = "jdbc:postgresql://localhost:5432/alis";
	private static final String USER = "postgres";
	private static final String PASS = "novustemp";
	private static final String DRIVER = "org.postgresql.Driver";

	/**
	 * Constructor
	 * 
	 * @throws InstantiationException if no driver found
	 * @throws IllegalAccessException
	 */
	public DAOConnector() throws InstantiationException, IllegalAccessException {

		try {
			Class.forName(DRIVER).newInstance();

		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return {@link Connection}
	 * @throws SQLException
	 */
	public Connection connect() throws SQLException {
		final Connection connection = DriverManager.getConnection(URL, USER, PASS);
		return connection;
	}

}
