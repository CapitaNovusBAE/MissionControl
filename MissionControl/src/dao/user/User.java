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
	 * @param User username, password
	 */
	public User(String userName, String password) {
		super();
		this.name = userName;
		this.password = password;
		this.pLevel = PermissionLevels.LOW;
		this.active = true;
	}

	/**Constructor
	 * @param User username, password, permissonLevel, active
	 */
	public User(String userName, String password, PermissionLevels pLevel, boolean active) {
		super();
		this.name = userName;
		this.password = password;
		this.pLevel = pLevel;
		this.active = active;
	}

	public PermissionLevels getPermissionLevel() {
		return pLevel;
	}

	public void setpLevel(PermissionLevels pLevel) {
		this.pLevel = pLevel;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String userName) {
		this.name = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User: [Name: " + name + " Permission Level: " + pLevel + " Active: " + isActive() + "]";
	}

}
