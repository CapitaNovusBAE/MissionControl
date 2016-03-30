package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnector {

	    private static final String URL = "jdbc:postgresql://localhost:5432/alis";
	    private static final String USER = "postgres";
	    private static final String PASS = "novustemp";
	    private static final String DRIVER = "org.postgresql.Driver";
	                 
	    public DAOConnector() throws InstantiationException, IllegalAccessException {
	    
	            try {
	                Class.forName(DRIVER).newInstance();
	                
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }      
	    }
	    
	    public Connection connect() throws SQLException
	    {
	       Connection connection=DriverManager.getConnection(URL,USER,PASS);	    
	       return connection;
	    }
	    
	}

