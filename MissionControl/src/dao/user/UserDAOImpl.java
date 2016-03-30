package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DAOConnector;
import dao.user.User.PermissionLevels;

public class UserDAOImpl implements UserDAO {

	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String PERMISSIONLEVELS = "permissionLevel";
	private static final String ACTIVE = "active";

	@Override
	public List<User> getAllUsers() {

		List<User> userList = new ArrayList<User>();

		try {
			DAOConnector dc = new DAOConnector();
			Connection conn = dc.connect();
			PreparedStatement prs = null;
			ResultSet rs = null;
			String query = "SELECT * FROM users";

			prs = conn.prepareStatement(query);
			rs = prs.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getString(USERNAME), rs.getString(PASSWORD),
						PermissionLevels.valueOf(rs.getString(PERMISSIONLEVELS)), rs.getBoolean(ACTIVE));

				userList.add(user);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public boolean addUser(User user) {

		PreparedStatement prs = null;

		try {

			DAOConnector dc = new DAOConnector();
			Connection conn = dc.connect();

			String query = "INSERT INTO users (" + PERMISSIONLEVELS + "," + USERNAME + "," + PASSWORD
					+ ") VALUES (?,?,?);";
			prs = conn.prepareStatement(query);
			prs.setString(1, user.getPermissionLevel().name());
			prs.setString(2, user.getName());
			prs.setString(3, user.getPassword());
			return prs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUser(String userName) {

		PreparedStatement prs = null;

		try {
			DAOConnector dc = new DAOConnector();
			Connection conn = dc.connect();

			String query = "SELECT * FROM users WHERE " + USERNAME + " = '" + userName + "' ";
			prs = conn.prepareStatement(query);
			ResultSet rs = prs.executeQuery();

			while (rs.next()) {

				return new User(rs.getString(USERNAME), rs.getString(PASSWORD),
						PermissionLevels.valueOf(rs.getString(PERMISSIONLEVELS)), rs.getBoolean(ACTIVE));

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public boolean updateUser(User user) {

		try {

			DAOConnector dc = new DAOConnector();
			Connection conn = dc.connect();

			String query = "UPDATE users SET " + PASSWORD + "='" + user.getPassword() + "'," + PERMISSIONLEVELS + "='"
					+ user.getPermissionLevel() + "' WHERE " + USERNAME + " = '" + user.getName() + "'";

			PreparedStatement prs = conn.prepareStatement(query);
			return prs.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(String userName) {

		try {
			DAOConnector dc = new DAOConnector();
			Connection conn = dc.connect();

			String query = "UPDATE users SET " + ACTIVE + "=" + false + " WHERE " + USERNAME + " = '" + userName + "'";
			PreparedStatement prs = conn.prepareStatement(query);
			return prs.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
