import dao.user.User;
import dao.user.UserDAOImpl;

public class DAOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String username  = "AliGrlk";
		String permission = "HIGH";
		String password = "novustemp"; 
        UserDAOImpl ud = new UserDAOImpl();
        
        for(User users : ud.getAllUsers()){
            System.out.println("Username :" + users.getName() + " Password: " + users.getPassword()
            				+ " Permission: " + users.getPermissionLevel() + " Active: " + users.isActive());
            
        }
        
        
        
        
        
	}

}
