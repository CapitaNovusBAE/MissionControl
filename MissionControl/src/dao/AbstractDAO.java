package dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Base class for DAO
 * 
 * @author Vadim Khoruzhenko
 *
 */
public abstract class AbstractDAO {

	/**
	 * @return {@link Connection}
	 */
	public Connection getConnection() {
		DAOConnector dc;
		try {
			dc = new DAOConnector();
			return dc.connect();
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param closeable
	 * @return true if connection closed succesfully else false
	 */
	public boolean closeQuietly(AutoCloseable closeable) {

		try {
			if (closeable != null) {
				closeable.close();

			}
			return true;

		} catch (Exception e) {

			System.out.println("Cannot close" + closeable);
			return false;
		}

	}
}
