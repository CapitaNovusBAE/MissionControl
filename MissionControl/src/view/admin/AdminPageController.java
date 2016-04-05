package view.admin;
import java.util.ArrayList;
import java.util.List;

/**
 * Admin Page FXML Controller
 * @author James Paul - Novus BAE
 */

import controller.MainApp;
import dao.user.User;
import dao.user.User.PermissionLevels;
import dao.user.UserDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AdminPageController {

	@FXML
	private TextField addUserUsername;
	@FXML
	private PasswordField addUserPassword;
	@FXML
	private PasswordField addUserConfirmPassword;
	@FXML
	private ComboBox<String> permissionBox;
	@FXML
	private Button addUserBtn;
	@FXML
	private Button searchUsersBtn;
	@FXML
	private Text missingFields;

	private ObservableList<String> permissionLevels = FXCollections.observableArrayList("LOW", "MEDIUM", "HIGH");

	@SuppressWarnings("unused")
	private MainApp mainApp;

    @FXML
    private void addUser(ActionEvent event){

    	boolean blankFields = false;
    	boolean addUser = false;

    	List<String> missingElements = new ArrayList<String>();

    	missingFields.setText("");

    	if(addUserUsername.getText().trim().isEmpty()){
    		blankFields = true;
    		missingElements.add("Username");
    	}
    	if(addUserPassword.getText().trim().isEmpty()){
    		blankFields = true;
    		missingElements.add("password");
    	}
    	if(addUserConfirmPassword.getText().trim().isEmpty()){
    		blankFields = true;
    		missingElements.add("confirm-password");
    	}
    	if(permissionBox.getValue() == null){
    		blankFields = true;
    		missingElements.add("permission-level");
    	}

    	if(blankFields){
    		//CHECK 1. ALERT ADMIN THAT BLANK FIELDS ARE PRESENT.
    		missingFields.setText("Please fill in the missing fields:" + "\n" + missingElements);
    	} else if(addUserPassword.getText().length() < 8){
    		//CHECK 2. ALERT ADMIN THE PASSWORD IS NOT LONG ENOUGH.
    		missingFields.setText("Password's must be at least 8 characters long.");
        } else if (!addUserPassword.getText().equals(addUserConfirmPassword.getText())){
        	//CHECK 3. ALERT ADMIN THE PASSWORDS DO NOT MATCH.
        	missingFields.setText("Password's do not match.");
    	} else {
    		//INPUT FIELDS FILLED CORRECTLY.
    		addUser = true;
    	}

    	//**********
		String username = addUserUsername.getText();
		String password = addUserPassword.getText();
		String permLvl = permissionBox.getValue();
		PermissionLevels lvl = PermissionLevels.valueOf(permLvl);

		//**********
    	UserDAOImpl ud = new UserDAOImpl();
    	User u = ud.getUser(username);

    	if(u != null){
    		addUser = false;
    		missingFields.setText("Username in use.");
    	}

    	// CHECK PASSWORDS <<<<<<<<<<<<<<<<---------------------------------

    	//**********
		if (addUser){
			User user = new User(username, password, lvl, true);
			ud.addUser(user);
			missingFields.setText("User Added!");
    	}
    }

    @FXML
    private void searchUsers(){

    }

	public void initialize(){
		permissionBox.setItems(permissionLevels);
	}

	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}
}
