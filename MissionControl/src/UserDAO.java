
import java.util.List;

public interface UserDAO {
    
    public List<User> getAllUsers();
    public void addUser(User user);
    public User getUser(int userID);
    public void updateUser(User user);
    public void deleteUser(int userID);
    
}