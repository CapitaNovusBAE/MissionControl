/**
 * 
 */
package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dao.user.User.PermissionLevels;

/**
 * @author Ali Gurlek Test for {@link UserDAOImpl}
 *
 */
public class UserDAOTest {

	@Mock
	private Connection connection;

	@Mock
	private PreparedStatement ps;

	@Mock
	private ResultSet rs;

	private UserDAOImpl userDAO;

	/**
	 * Set up the tests before running
	 * 
	 * @throws Exception
	 *
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.userDAO = Mockito.spy(new UserDAOImpl());

		Mockito.doReturn(this.connection).when(this.userDAO).getConnection();
		Mockito.when(this.connection.prepareStatement(Mockito.any())).thenReturn(this.ps);
		Mockito.when(this.ps.executeQuery()).thenReturn(this.rs);
		Mockito.when(this.rs.next()).thenReturn(true).thenReturn(false);
		Mockito.when(this.rs.getString("username")).thenReturn("User");
		Mockito.when(this.rs.getString("password")).thenReturn("Password");
		Mockito.when(this.rs.getString("permissionLevel")).thenReturn("MEDIUM");
		Mockito.when(this.rs.getBoolean("active")).thenReturn(true);

		Mockito.when(this.ps.execute()).thenReturn(true);
	}

	/**
	 * Test method for {@link dao.user.UserDAOImpl#getAllUsers()}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetAllUsers() throws SQLException {
		final List<User> userList = new ArrayList<User>();

		userDAO.getAllUsers();
		Mockito.verify(this.connection).prepareStatement(Mockito.eq("SELECT * FROM users WHERE active=true"));
		Mockito.verify(this.ps).executeQuery();

		Assert.assertNotNull(userList);

		Mockito.verify(this.userDAO).closeQuietly(this.connection);
		Mockito.verify(this.userDAO).closeQuietly(this.ps);
		Mockito.verify(this.userDAO).closeQuietly(this.rs);
	}

	/**
	 * Test method for {@link dao.user.UserDAOImpl#addUser(dao.user.User)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testAddUser() throws SQLException {
		final User user = new User("user", "password", PermissionLevels.MEDIUM, true);
		Assert.assertEquals(true, this.userDAO.addUser(user));
		Mockito.verify(this.connection)
				.prepareStatement("INSERT INTO users(permissionLevel,username,password) VALUES (?,?,?);");
		Mockito.verify(this.ps).setString(1, "MEDIUM");
		Mockito.verify(this.ps).setString(2, "user");
		Mockito.verify(this.ps).setString(3, "password");
		Mockito.verify(this.ps).execute();
		Mockito.verify(this.userDAO).closeQuietly(this.connection);
		Mockito.verify(this.userDAO).closeQuietly(this.ps);
	}

	/**
	 * Test method for {@link dao.user.UserDAOImpl#getUser(java.lang.String)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetUser() throws SQLException {
		final User user = this.userDAO.getUser("User");

		Mockito.verify(this.connection).prepareStatement("SELECT * FROM users WHERE username = 'User' ");
		Mockito.verify(this.ps).executeQuery();
		Assert.assertNotNull(user);
		Mockito.verify(this.userDAO).closeQuietly(this.connection);
		Mockito.verify(this.userDAO).closeQuietly(this.ps);
		Mockito.verify(this.userDAO).closeQuietly(this.rs);
	}

	/**
	 * Test method for {@link dao.user.UserDAOImpl#updateUser(dao.user.User)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testUpdateUser() throws SQLException {

		User u = new User("user", "password", PermissionLevels.LOW, true);
		this.userDAO.updateUser(u);

		Mockito.verify(this.connection)
				.prepareStatement("UPDATE users SET password='password',permissionLevel='LOW' WHERE username = 'user'");
		Mockito.verify(this.ps).execute();

		Mockito.verify(this.userDAO).closeQuietly(this.connection);
		Mockito.verify(this.userDAO).closeQuietly(this.ps);

	}

	/**
	 * Test method for {@link dao.user.UserDAOImpl#deleteUser(java.lang.String)}
	 * .
	 * @throws SQLException 
	 */
	@Test
	public void testDeleteUser() throws SQLException {
		
		this.userDAO.deleteUser("User");
		
		Mockito.verify(this.connection).prepareStatement("UPDATE users SET active=false WHERE username = 'User'");
		Mockito.verify(this.ps).execute();
		Mockito.verify(this.userDAO).closeQuietly(this.connection);
		Mockito.verify(this.userDAO).closeQuietly(this.ps);
	}

}
