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

	UserDAOImpl ud = new UserDAOImpl();

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
	private Text missingFields;

	@FXML
	private Button searchUsersBtn;
	@FXML
	private TextField searchUsernames;
	@FXML
	private TextField showUsername;
	@FXML
	private PasswordField showPassword;
	@FXML
	private ComboBox<String> showPermissions;
	@FXML
	private Button deleteUserBtn;
	@FXML
	private Text editUserAlert;

	private ObservableList<String> permissionLevels = FXCollections.observableArrayList("LOW", "MEDIUM", "HIGH");

	@SuppressWarnings("unused")
	private MainApp mainApp;

    @FXML
    private void addUser(ActionEvent event){

    	boolean blankFields = false;
    	boolean addUser = true;

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
    		return;
    	} else if(addUserPassword.getText().length() < 8){
    		//CHECK 2. ALERT ADMIN THE PASSWORD IS NOT LONG ENOUGH.
    		missingFields.setText("Password's must be at least 8 characters long.");
    		return;
        } else if (!addUserPassword.getText().equals(addUserConfirmPassword.getText())){
        	//CHECK 3. ALERT ADMIN THE PASSWORDS DO NOT MATCH.
        	missingFields.setText("Password's do not match.");
        	return;
    	}

    	//SET VARIABLES BASED ON USER INPUT.
		String username = addUserUsername.getText();
		String password = addUserPassword.getText();
		String permLvl = permissionBox.getValue();
		PermissionLevels lvl = PermissionLevels.valueOf(permLvl);

		//CHECK USERNAME IS NOT IN USE.
    	User u = ud.getUser(username);

    	if(u != null){
    		addUser = false;
    		missingFields.setText("Username or password in use.");
    		return;
    	}

    	//CHECK PASSWORD IS NOT IN USE.
    	List<User> ul = ud.getAllUsers();

    	for(int i=0; i< ul.size(); i++){
    		User temp = ul.get(i);

    		String up = temp.getPassword();

    		if(up.equals(password)){
    			addUser = false;
    			missingFields.setText("Username or password in use.");
    			return;
    		}
    	}

    	//**********
		if (addUser){
			User user = new User(username, password, lvl, true);
			ud.addUser(user);
			missingFields.setText("User Added!");

			addUserUsername.setText("");
			addUserPassword.setText("");
			addUserConfirmPassword.setText("");
			permissionBox.setValue("");
    	}
    }

    private boolean search = false;

    // LOCK INPUT ON RIGHT HAND SIDE UNTIL SEARCH TAKES PLACE!!!!!
    @FXML
    private void searchUsers(){
    	User u = ud.getUser(searchUsernames.getText());

    	if (u==null){
    		//ALERT USER ABOUT "NO USER FOUND". <<<<<<<<<<<<<-------------------
    		editUserAlert.setText("User not found.");
    		return;
    	}

		showUsername.setEditable(true);
    	showPassword.setEditable(true);
    	showPermissions.setEditable(true);

    	showUsername.setText(u.getName());
    	showPassword.setText(u.getPassword());
    	showPermissions.setValue(u.getPermissionLevel().toString());

    	editUserAlert.setText("User found.");

    	search = true;
    }

    @FXML
    private void removeUser(){

    	if(!search){
    		editUserAlert.setText("Search for user to remove.");
    		return;
    	}

    	ud.deleteUser(showUsername.getText());

    	showUsername.setText("");
    	showPassword.setText("");
    	showPermissions.setValue("");

    	editUserAlert.setText("User Removed.");
    }

	public void initialize(){
		permissionBox.setItems(permissionLevels);
		showPermissions.setItems(permissionLevels);

		showUsername.setEditable(false);
    	showPassword.setEditable(false);
    	showPermissions.setEditable(false);
	}

	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}
}
