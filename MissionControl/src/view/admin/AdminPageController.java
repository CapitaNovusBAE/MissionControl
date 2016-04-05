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

	private final ObservableList<String> permissionLevels = FXCollections.observableArrayList("LOW", "MEDIUM", "HIGH");

	@SuppressWarnings("unused")
	private MainApp mainApp;

	@FXML
	private void addUser(final ActionEvent event){

		boolean blankFields = false;
		boolean addUser = true;

		final List<String> missingElements = new ArrayList<String>();

		this.missingFields.setText("");

		if(this.addUserUsername.getText().trim().isEmpty()){
			blankFields = true;
			missingElements.add("Username");
		}
		if(this.addUserPassword.getText().trim().isEmpty()){
			blankFields = true;
			missingElements.add("password");
		}
		if(this.addUserConfirmPassword.getText().trim().isEmpty()){
			blankFields = true;
			missingElements.add("confirm-password");
		}
		if(this.permissionBox.getValue() == null){
			blankFields = true;
			missingElements.add("permission-level");
		}

		if(blankFields){
			//CHECK 1. ALERT ADMIN THAT BLANK FIELDS ARE PRESENT.
			this.missingFields.setText("Please fill in the missing fields:" + "\n" + missingElements);
			return;
		} else if(this.addUserPassword.getText().length() < 8){
			//CHECK 2. ALERT ADMIN THE PASSWORD IS NOT LONG ENOUGH.
			this.missingFields.setText("Password's must be at least 8 characters long.");
			return;
		} else if (!this.addUserPassword.getText().equals(this.addUserConfirmPassword.getText())){
			//CHECK 3. ALERT ADMIN THE PASSWORDS DO NOT MATCH.
			this.missingFields.setText("Password's do not match.");
			return;
		}

		//SET VARIABLES BASED ON USER INPUT.
		final String username = this.addUserUsername.getText();
		final String password = this.addUserPassword.getText();
		final String permLvl = this.permissionBox.getValue();
		final PermissionLevels lvl = PermissionLevels.valueOf(permLvl);

		//CHECK USERNAME IS NOT IN USE.
		final User u = this.ud.getUser(username);

		if(u != null){
			addUser = false;
			this.missingFields.setText("Username or password in use.");
			return;
		}

		//CHECK PASSWORD IS NOT IN USE.
		final List<User> ul = this.ud.getAllUsers();

		for(int i=0; i< ul.size(); i++){
			final User temp = ul.get(i);

			final String up = temp.getPassword();

			if(up.equals(password)){
				addUser = false;
				this.missingFields.setText("Username or password in use.");
				return;
			}
		}

		//**********
		if (addUser){
			final User user = new User(username, password, lvl, true);
			this.ud.addUser(user);
			this.missingFields.setText("User Added!");

			this.addUserUsername.setText("");
			this.addUserPassword.setText("");
			this.addUserConfirmPassword.setText("");
			this.permissionBox.setValue("");
		}
	}

	private boolean search = false;

	// LOCK INPUT ON RIGHT HAND SIDE UNTIL SEARCH TAKES PLACE!!!!!
	@FXML
	private void searchUsers(){
		final User u = this.ud.getUser(this.searchUsernames.getText());

		if (u==null){
			//ALERT USER ABOUT "NO USER FOUND". <<<<<<<<<<<<<-------------------
			this.editUserAlert.setText("User not found.");
			return;
		}

		this.showUsername.setEditable(true);
		this.showPassword.setEditable(true);
		this.showPermissions.setEditable(true);

		this.showUsername.setText(u.getName());
		this.showPassword.setText(u.getPassword());
		this.showPermissions.setValue(u.getPermissionLevel().toString());

		this.editUserAlert.setText("User found.");

		this.search = true;
	}

	@FXML
	private void removeUser(){

		if(!this.search){
			this.editUserAlert.setText("Search for user to remove.");
			return;
		}

		this.ud.deleteUser(this.showUsername.getText());

		this.showUsername.setText("");
		this.showPassword.setText("");
		this.showPermissions.setValue("");

		this.editUserAlert.setText("User Removed.");
	}

	public void initialize(){
		this.permissionBox.setItems(this.permissionLevels);
		this.showPermissions.setItems(this.permissionLevels);

		this.showUsername.setEditable(false);
		this.showPassword.setEditable(false);
		this.showPermissions.setEditable(false);
	}

	public void setMainApp(final MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}
}
