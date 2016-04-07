package dao.user;

import java.util.List;

/**
 * Data Access Object for User Interface
 *
 * @author Ali Gurlek
 *
 */

public interface UserDAO {

	public List<User> getAllUsers();

	public boolean addUser(User user);

	public User getUser(String userName);

	public boolean updateUser(User user);

	public boolean deleteUser(String userName);

	public boolean enableUser(String userName);

}