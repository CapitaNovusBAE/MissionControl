
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOImpl implements UserDAO {

	@Override
	public List<User> getAllUsers() {

		List<User> userList = new ArrayList<User>();

		try {
			DAOConnector dc = new DAOConnector();
			Connection conn = dc.connect();
			Statement stmt = null;
			ResultSet rs = null;

			String query = "SELECT * FROM users";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				User user = new User();
				user.setUserName(rs.getString("user_name"));
				userList.add(user);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public void addUser(User user) {

		PreparedStatement prs=null;
		
		try {
			
			DAOConnector dc = new DAOConnector();

			Connection conn = dc.connect();


			String query = "Insert Into users (user_name,user_password) VALUES (?,?)";
			prs = conn.prepareStatement(query);
			prs.setString(1, user.getUserName());
			prs.setString(2, user.getPassword());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int userID) {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public void updateUser(User user) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void deleteUser(int userID) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

}
