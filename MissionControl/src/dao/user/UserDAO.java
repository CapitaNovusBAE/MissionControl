package dao.user;

import java.util.List;

public interface UserDAO {
    
    public List<User> getAllUsers();
    public void addUser(User user);
    public User getUser(String userName);
    public void updateUser(User user);
    public void deleteUser(String userName) ;

}