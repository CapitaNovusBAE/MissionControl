package model;

import java.sql.SQLException;

public class Authenticator extends DatabaseInteraction{

	private final String usernameInput;
	private final String passwordInput;
	private boolean loginStatus;

	public Authenticator(String usernameInput, String passwordInput) throws SQLException{
		this.usernameInput = usernameInput;
		this.passwordInput = passwordInput;
		this.setLoginStatus(compareDetails());
	}

	private boolean compareDetails() throws SQLException{
		boolean isCorrect = false;

        connectToDatabase();
        getUsers();

        while(rs.next()){
        	String username = rs.getString(1);

            if (usernameInput.equals(username)){
                isCorrect = true;
                break;
            }
        }

        if (isCorrect){
        	String userId = rs.getString(1);
            getPassword(userId);

            String password = rs.getString(1);
            isCorrect = passwordInput.equals(password.trim());
        }

        return isCorrect;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
}