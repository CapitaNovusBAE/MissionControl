package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.AbstractDAO;
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

	// return all users from db
	@Override
	public List<User> getAllUsers() {

		final List<User> userList = new ArrayList<User>();
		final Connection conn = getConnection();
		PreparedStatement prs = null;
		ResultSet rs = null;
		try {

			final String query = "SELECT * FROM users";

			prs = conn.prepareStatement(query);
			rs = prs.executeQuery();
			while (rs.next()) {
				final User user = new User(rs.getString(USERNAME), rs.getString(PASSWORD),
						PermissionLevels.valueOf(rs.getString(PERMISSIONLEVELS)), rs.getBoolean(ACTIVE));

				userList.add(user);

			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {

			closeQuietly(conn);
			closeQuietly(prs);
			closeQuietly(rs);
		}

		return userList;
	}

	// Add new user to db
	@Override
	public boolean addUser(final User user) {

		PreparedStatement prs = null;
		final Connection conn = getConnection();
		boolean result = false;
		try {

			final String query = "INSERT INTO users (" + PERMISSIONLEVELS + "," + USERNAME + "," + PASSWORD
					+ ") VALUES (?,?,?);";
			prs = conn.prepareStatement(query);
			prs.setString(1, user.getPermissionLevel().name());
			prs.setString(2, user.getName());
			prs.setString(3, user.getPassword());
			result = prs.execute();

		} catch (final Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result && closeQuietly(conn) && closeQuietly(prs);
	}

	// return user by its username if exist
	@Override
	public User getUser(final String userName) {

		PreparedStatement prs = null;
		ResultSet rs = null;
		final Connection conn = getConnection();
		try {
			final String query = "SELECT * FROM users WHERE " + USERNAME + " = '" + userName + "' ";
			prs = conn.prepareStatement(query);
			rs = prs.executeQuery();

			while (rs.next()) {

				return new User(rs.getString(USERNAME), rs.getString(PASSWORD),
						PermissionLevels.valueOf(rs.getString(PERMISSIONLEVELS)), rs.getBoolean(ACTIVE));

			}

		} catch (final Exception e) {
			e.printStackTrace();

		} finally {

			closeQuietly(conn);
			closeQuietly(prs);
			closeQuietly(rs);
		}
		return null;
	}

	// update users information and permission level
	@Override
	public boolean updateUser(final User user) {
		final Connection conn = getConnection();
		PreparedStatement prs = null;
		boolean result = false;
		try {
			final String query = "UPDATE users SET " + PASSWORD + "='" + user.getPassword() + "'," + PERMISSIONLEVELS + "='"
					+ user.getPermissionLevel() + "' WHERE " + USERNAME + " = '" + user.getName() + "'";

			prs = conn.prepareStatement(query);
			result = prs.execute();

		} catch (final Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result && closeQuietly(conn) && closeQuietly(prs);
	}

	// delete user -- activate=false
	@Override
	public boolean deleteUser(final String userName) {
		final Connection conn = getConnection();
		PreparedStatement prs = null;
		boolean result = false;
		try {
			final String query = "UPDATE users SET " + ACTIVE + "=" + false + " WHERE " + USERNAME + " = '" + userName + "'";
			prs= conn.prepareStatement(query);
			result = prs.execute();

		} catch (final Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result && closeQuietly(conn) && closeQuietly(prs);
	}

}
