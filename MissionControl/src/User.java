
public class User {
    
   // private int userID;
    private String userName;
    private String password;
   // private int permissionID;

  

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User: [name=" + userName + "]"; 
    }
      
    
}
