import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnector {

	    private String URL = "jdbc:postgresql://localhost:5432/alis";
	    private String user = "postgres";
	    private String pass = "novustemp";
	    private String Driver = "org.postgresql.Driver";
	                 
	    public DAOConnector() throws InstantiationException, IllegalAccessException {
	    
	            try {
	                Class.forName(Driver).newInstance();
	                
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }      
	    }
	    
	    public Connection connect() throws SQLException
	    {
	       Connection connection=DriverManager.getConnection(URL,user,pass);
	        if(connection == null){
	            System.out.println("no connection");
	        }else{
	            System.out.println("Connected");
	        }
	        return connection;
	    }
	    
	}

