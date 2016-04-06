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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Adim Page FXML Controller.
 * @author James Paul.
 */
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
	private Text editUserAlert;
	@FXML
	private Button updateUserBtn;
	@FXML
	private CheckBox activeBox;

	private final ObservableList<String> permissionLevels = FXCollections.observableArrayList("LOW", "MEDIUM", "HIGH");

	@SuppressWarnings("unused")
	private MainApp mainApp;

	@FXML
	private void addUser(final ActionEvent event){

		boolean blankFields = false;
		boolean addUser = true;

		final List<String> missingElements = new ArrayList<String>();

		this.missingFields.setText("");

		// SEARCH FOR EMPTY FIELDS
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

		// ADD USER IF ALL IS WELL.
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

	//***********************************************************************

	User u;

	@FXML
	private void searchUsers(){
		u = this.ud.getUser(this.searchUsernames.getText());

		if (u==null){
			this.editUserAlert.setText("User not found.");
			return;
		}

		this.showUsername.setText(u.getName());
		this.showPassword.setText(u.getPassword());
		this.showPermissions.setValue(u.getPermissionLevel().toString());
		this.activeBox.setSelected(u.isActive());

		this.editUserAlert.setText("User found.");

		searchUsernames.setText("");;
	}

	@FXML
	private void updateUserDB(){

		if(updateUserBtn.getText().equals("Edit")){
			//MAKE USER FIELDS EDITABLE
			this.showUsername.setEditable(true);
			this.showPassword.setEditable(true);
			this.showPermissions.setEditable(true);
			this.activeBox.setDisable(false);
			this.showPermissions.setItems(this.permissionLevels);
			//CHANGE TEXT ON BUTTON
			updateUserBtn.setText("Update");
		}else{

			this.editUserAlert.setText("User Updated.");

			u.setName(showUsername.getText());
			u.setPassword(showPassword.getText());
			//u.setpLevel(PermissionLevels.valueOf(showPermissions.toString())); <-broken
			u.setActive(activeBox.isSelected());

			ud.updateUser(u);

			//MAKE USER FIELDS NOT EDITABLE
			this.showUsername.setEditable(false);
			this.showPassword.setEditable(false);
			this.showPermissions.setEditable(false);
			this.activeBox.setDisable(true);
			// EMPTY USER FIELDS
			this.showUsername.setText("");
			this.showPassword.setText("");
			this.showPermissions.setItems(null);
			this.activeBox.setSelected(false);
			//CHANGE TEXT ON BUTTON
			updateUserBtn.setText("Edit");
		}
	}

	/**
	 * Set items within combo boxes.
	 * Disable input in edit user section on initialise.
	 */
	public void initialize(){
		this.permissionBox.setItems(this.permissionLevels);
		this.showUsername.setEditable(false);
		this.showPassword.setEditable(false);
		this.showPermissions.setEditable(false);
		this.activeBox.setDisable(true);
		updateUserBtn.setText("Edit");
	}

	/**
	 * @param mainApp - link to MainApp.
	 */
	public void setMainApp(final MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}
}
