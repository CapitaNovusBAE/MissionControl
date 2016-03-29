
public class DAOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        DAOImpl ud = new DAOImpl();
        
        for(User users : ud.getAllUsers()){
            System.out.println("Username :" + users.getUserName());
            
        }
	}

}
