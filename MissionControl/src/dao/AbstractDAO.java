package dao;

import java.sql.Connection;

/**
 * Base class for DAO
 * @author Vadim Khoruzhenko
 *
 */
public abstract class AbstractDAO {

	/**
	 * @return {@link Connection}
	 */
	public Connection getConnection(){
		DAOConnector dc;
		try {
			dc = new DAOConnector();
			return dc.connect();
		}
		catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
