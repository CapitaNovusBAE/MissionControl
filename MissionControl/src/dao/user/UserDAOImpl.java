package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.AbstractDAO;
import dao.DAOConnector;
import dao.user.User.PermissionLevels;

/**
 * Data Access Object for User
 * 
 * @author Ali Gurlek
 *
 */

public class UserDAOImpl extends AbstractDAO implements UserDAO {

	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String PERMISSIONLEVELS = "permissionLevel";
	private static final String ACTIVE = "active";

	/**
	 * @param getAllUsers
	 * @return return list of users from db
	 */
	@Override
	public List<User> getAllUsers() {

		List<User> userList = new ArrayList<User>();
		final Connection conn = getConnection();
		PreparedStatement prs = null;
		ResultSet rs = null;
		try {

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
		} finally {

			closeQuietly(conn);
			closeQuietly(prs);
			closeQuietly(rs);
		}

		return userList;
	}

	/**
	 * @param addUser
	 * @return add user to  db
	 */
	@Override
	public boolean addUser(User user) {

		PreparedStatement prs = null;

		try {

			final Connection conn = getConnection();
			String query = "INSERT INTO users (" + PERMISSIONLEVELS + "," + USERNAME + "," + PASSWORD
					+ ") VALUES (?,?,?);";
			prs = conn.prepareStatement(query);
			prs.setString(1, user.getPermissionLevel().name());
			prs.setString(2, user.getName());
			prs.setString(3, user.getPassword());
			return prs.execute() && closeQuietly(conn) && closeQuietly(prs);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param getUser
	 * @return return user by its username if exist in db
	 */
	@Override
	public User getUser(String userName) {

		PreparedStatement prs = null;
		ResultSet rs = null;
		final Connection conn = getConnection();
		try {
			String query = "SELECT * FROM users WHERE " + USERNAME + " = '" + userName + "' ";
			prs = conn.prepareStatement(query);
			rs = prs.executeQuery();

			while (rs.next()) {

				return new User(rs.getString(USERNAME), rs.getString(PASSWORD),
						PermissionLevels.valueOf(rs.getString(PERMISSIONLEVELS)), rs.getBoolean(ACTIVE));

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			closeQuietly(conn);
			closeQuietly(prs);
			closeQuietly(rs);
		}
		return null;
	}

	/**
	 * @param updateUser
	 * @return update provided information in db with the new inputs
	 */
	@Override
	public boolean updateUser(User user) {

		try {

			final Connection conn = getConnection();

			String query = "UPDATE users SET " + PASSWORD + "='" + user.getPassword() + "'," + PERMISSIONLEVELS + "='"
					+ user.getPermissionLevel() + "' WHERE " + USERNAME + " = '" + user.getName() + "'";

			PreparedStatement prs = conn.prepareStatement(query);
			return prs.execute() && closeQuietly(conn) && closeQuietly(prs);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param deleteUser
	 * @return delete user from db  -- its deactivate the activate field -- data entegrity
	 */
	@Override
	public boolean deleteUser(String userName) {

		try {

			final Connection conn = getConnection();

			String query = "UPDATE users SET " + ACTIVE + "=" + false + " WHERE " + USERNAME + " = '" + userName + "'";
			PreparedStatement prs = conn.prepareStatement(query);
			return prs.execute() && closeQuietly(conn) && closeQuietly(prs);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
