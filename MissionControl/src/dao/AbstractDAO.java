package dao;

import java.sql.Connection;

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
	 * @return true if connection closed successfully else false
	 */
	public boolean closeQuietly(final AutoCloseable closeable) {

		try {
			if (closeable != null) {
				closeable.close();

			}
			return true;

		} catch (final Exception e) {

			System.out.println("Cannot close" + closeable);
			return false;
		}

	}
}
