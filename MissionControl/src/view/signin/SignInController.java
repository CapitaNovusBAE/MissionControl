package view.signin;

import java.sql.SQLException;

/**
 * Sign In FXML Controller
 * @author James Paul Novus BAE
 */

import controller.SignInAuthenticator;
import controller.MainApp;
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
    private void keyPress(ActionEvent event){

    	if(event.getSource().equals(unInput)) {
    		pwInput.requestFocus();
    	} else if (event.getSource().equals(pwInput)){
    		try {
				signInSubmit(event);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }

	@FXML
	private void signInSubmit(ActionEvent event) throws SQLException {

		 String  usernameInput	= unInput.getText();
         String  passwordInput	= pwInput.getText();
         boolean signInStatus = false;

         SignInAuthenticator authenticator = new SignInAuthenticator(usernameInput, passwordInput);
         signInStatus = authenticator.isLoginStatus();

         if (signInStatus){
        	 mainApp.initLayout();
         } else {
        	 if(signInAttempts < 4){signInAttempts++;}
        	 signInFailText.setText("Username or password incorrect."+"\n"+ (4 - signInAttempts) +" Attempts remaining");
         }

         if(signInAttempts > 3){
        	 System.exit(1);
         }

		 pwInput.setText("");
	}

	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;

		unInput.setText("Admin");
		pwInput.setText("admin123");
	}
}
