package view.signin;

import java.sql.SQLException;

import controller.MainApp;

/**
 * Sign In FXML Controller
 * @author James Paul Novus BAE
 */

import controller.SignInAuthenticator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignInController{

	@FXML
	private PasswordField pwInput;
	@FXML
	private TextField unInput;
	@FXML
	private Button signInBtn;
	@FXML
	private Text signInFailText;

	private MainApp mainApp;

	int signInAttempts = 0;

	@FXML
	private void keyPress(final ActionEvent event){

		if(event.getSource().equals(this.unInput)) {
			this.pwInput.requestFocus();
		} else if (event.getSource().equals(this.pwInput)){
			try {
				signInSubmit(event);
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void signInSubmit(final ActionEvent event) throws SQLException {

		final String  usernameInput	= this.unInput.getText();
		final String  passwordInput	= this.pwInput.getText();
		boolean signInStatus = false;

		final SignInAuthenticator authenticator = new SignInAuthenticator(usernameInput, passwordInput, this.mainApp);
		signInStatus = authenticator.isLoginStatus();

		if (signInStatus){
			this.mainApp.initalizeLayout();
		} else {
			if(this.signInAttempts < 4){this.signInAttempts++;}
			this.signInFailText.setText("Username or password incorrect."+"\n"+ (4 - this.signInAttempts) +" Attempts remaining");
		}

		if(this.signInAttempts > 3){
			System.exit(1);
		}

		this.pwInput.setText("");
	}

	public void setMainApp(final MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;

		this.unInput.setText("Admin");
		this.pwInput.setText("admin123");
	}
}
