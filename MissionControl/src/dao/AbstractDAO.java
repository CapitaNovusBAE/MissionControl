package dao;

import java.sql.Connection;

public abstract class AbstractDAO {

	public Connection getConnection(){
		DAOConnector dc;
		try {
			dc = new DAOConnector();
			return dc.connect();
		}
		catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
	}
}
