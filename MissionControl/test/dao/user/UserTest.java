package dao.user;

import org.junit.Test;

import dao.user.User.PermissionLevels;

import org.junit.Assert;

/** Test cases for {@link User}
 * @author Ali Gurlek
 *
 */
public class UserTest {

	private static final String USERNAME = "AliGurlek";
	private static final String PASSWORD = "novustemp";
	private static final PermissionLevels PERMISSION = PermissionLevels.HIGH;
	
	
	/**
	 * Test for {@link User#User(String, String)}
	 */
	@Test
	public void testUserStringString() {
		User u = new User(USERNAME, PASSWORD);
		Assert.assertEquals(USERNAME, u.getName());
		Assert.assertEquals(PASSWORD, u.getPassword());
		Assert.assertEquals(PermissionLevels.LOW, u.getPermissionLevel());
		Assert.assertEquals(true, u.isActive());
	}

	/**
	 * Test for {@link User#User(String, String, PermissionLevels, boolean)}
	 */
	@Test
	public void testUserStringStringPermissionLevelsBoolean() {
		User u = new User(USERNAME, PASSWORD, PERMISSION, true);
		Assert.assertEquals(USERNAME, u.getName());
		Assert.assertEquals(PASSWORD, u.getPassword());
		Assert.assertEquals(PermissionLevels.HIGH, u.getPermissionLevel());
		Assert.assertEquals(true, u.isActive());
	}

	/**
	 * Test for {@link User#setpLevel(PermissionLevels)}
	 */
	@Test
	public void testSetpLevel() {
		User u = new User(USERNAME, PASSWORD, PERMISSION, true);
		Assert.assertEquals(PERMISSION, u.getPermissionLevel());

	}

	/**
	 * Test for {@link User#setActive(boolean)}
	 */
	@Test
	public void testSetActive() {
		User u = new User(USERNAME, PASSWORD);
		u.setActive(false);
		Assert.assertEquals(false,u.isActive());
	}


	/**
	 * Test for {@link User#setName(String)}
	 */
	@Test
	public void testSetName() {
		User u = new User(USERNAME, PASSWORD);
		u.setName(USERNAME);
		Assert.assertEquals(USERNAME,u.getName());
	}

	/**
	 * Test for {@link User #setPassword(String)}
	 */
	@Test
	public void testSetPassword() {
		User u = new User(USERNAME, PASSWORD);
		u.setName(PASSWORD);
		Assert.assertEquals(PASSWORD,u.getName());
	}

}
