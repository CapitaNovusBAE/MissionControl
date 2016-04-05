package controller;

import dao.user.User;
import dao.user.UserDAOImpl;

/**
 * Authenticate Submitted Sign-In Details
 * @author James Paul Novus BAE
 */

public class SignInAuthenticator {

	private boolean loginStatus;

	/**Constructor
	 * @param usernameInput - User name
	 * @param passwordInput -User password
	 * @param mainApp Main - application
	 */
	public SignInAuthenticator(final String usernameInput, final String passwordInput, final MainApp mainApp){
		setLoginStatus(compareDetails(usernameInput, passwordInput, mainApp));
	}

	private boolean compareDetails(final String usernameInput, final String passwordInput, final MainApp mainApp){
		boolean isCorrect = false;
		final UserDAOImpl ud = new UserDAOImpl();
		final User u = ud.getUser(usernameInput);

		if (u != null&&usernameInput.equals(u.getName())){
			isCorrect = passwordInput.equals(u.getPassword());
			mainApp.setUser(u);
		}
		return isCorrect;
	}

	/**
	 * @return login status
	 */
	public boolean isLoginStatus() {
		return this.loginStatus;
	}

	/**
	 * @param loginStatus
	 */
	public void setLoginStatus(final boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
}