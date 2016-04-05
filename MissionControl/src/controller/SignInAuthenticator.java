package controller;

import java.sql.SQLException;
import dao.user.User;
import dao.user.UserDAOImpl;

/**
 * Authenticate Submitted Sign-In Details
 * @author James Paul Novus BAE
 */

public class SignInAuthenticator {

	private boolean loginStatus;

	public SignInAuthenticator(String usernameInput, String passwordInput, MainApp mainApp) throws SQLException{

		this.setLoginStatus(compareDetails(usernameInput, passwordInput, mainApp));
		
	}

	private boolean compareDetails(String usernameInput, String passwordInput, MainApp mainApp) throws SQLException{
		boolean isCorrect = false;

		UserDAOImpl ud = new UserDAOImpl();
		User u = ud.getUser(usernameInput);
		
		System.out.println(u);

		if(u == null){ return false; }

        if (usernameInput.equals(u.getName())){
            isCorrect = passwordInput.equals(u.getPassword());
            mainApp.setUser(u);
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