import dao.user.User;
import dao.user.User.PermissionLevels;
import dao.user.UserDAOImpl;

public class DAOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String username = "Ali";
		String permission = "MEDIUM";
		String password = "novustemp";
		UserDAOImpl ud = new UserDAOImpl();

		for (User users : ud.getAllUsers()) {
			System.out.println("Username :" + users.getName() + " Password: " + users.getPassword() + " Permission: "
					+ users.getPermissionLevel() + " Active: " + users.isActive());

		}

		User u = new User(username, password,PermissionLevels.valueOf(permission), true );
		// ud.updateUser(u);
		// ud.deleteUser(username);
		// System.out.println(ud.getUser(username));
		ud.addUser(u);

		for (User users : ud.getAllUsers()) {
			System.out.println("Username :" + users.getName() + " Password: " + users.getPassword() + " Permission: "
					+ users.getPermissionLevel() + " Active: " + users.isActive());

		}
	}

}
