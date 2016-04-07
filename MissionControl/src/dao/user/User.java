package dao.user;

/**
 * User Class
 *
 * @author Ali Gurlek
 *
 */
public class User {

	private String name;
	private String password;
	private PermissionLevels pLevel;
	private boolean active;

	public enum PermissionLevels {

		LOW, MEDIUM, HIGH
	}

	/**Constructor
	 * @param userName User name
	 * @param password User password
	 */
	public User(final String userName, final String password) {
		super();
		this.name = userName;
		this.password = password;
		this.pLevel = PermissionLevels.LOW;
		this.active = true;
	}

	/**Constructor
	 * @param userName User name
	 * @param password User password
	 * @param pLevel User permission level
	 * @param active
	 *	 */
	public User(final String userName, final String password, final PermissionLevels pLevel, final boolean active) {
		super();
		this.name = userName;
		this.password = password;
		this.pLevel = pLevel;
		this.active = active;
	}

	public PermissionLevels getPermissionLevel() {
		return this.pLevel;
	}

	public void setpLevel(final PermissionLevels pLevel) {
		this.pLevel = pLevel;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String userName) {
		this.name = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User: [Name: " + this.name + " Permission Level: " + this.pLevel + " Active: " + isActive() + "]";
	}

}
