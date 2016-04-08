package view.admin;

import java.util.ArrayList;
import java.util.List;

import dao.user.User;
import dao.user.User.PermissionLevels;
import dao.user.UserDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import view.FXMLControllerAbstract;

/**
 * Adim Page FXML Controller.
 * @author James Paul.
 */
public class AdminPageController extends FXMLControllerAbstract{

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
	private TextField searchUsernames;
	@FXML
	private Button searchUsersBtn;
	@FXML
	private PasswordField showPassword;
	@FXML
	private PasswordField showConfirmPassword;
	@FXML
	private ComboBox<String> showPermissions;
	@FXML
	private Text editUserAlert;
	@FXML
	private CheckBox activeBox;
	@FXML
	private Button updateUserBtn;
	@FXML
	private Button updateCancelBtn;

	private UserDAOImpl ud = new UserDAOImpl();
	private User u;

	private final ObservableList<String> permissionLevels = FXCollections.observableArrayList("LOW", "MEDIUM", "HIGH");

	//***********************************************************************
	//ADD USER
	//***********************************************************************
	@FXML
	private void addUser(){

		final List<String> missingElements = new ArrayList<String>();
		this.missingFields.setText("");

		// SEARCH FOR EMPTY FIELDS, ADD FIELD NAME TO ARRAY LIST IF EMPTY.
		if(this.addUserUsername.getText().trim().isEmpty()){ missingElements.add("Username"); }
		if(this.addUserPassword.getText().trim().isEmpty()){ missingElements.add("password"); }
		if(this.addUserConfirmPassword.getText().trim().isEmpty()){ missingElements.add("confirm-password"); }
		if(this.permissionBox.getValue() == null){ missingElements.add("permission-level"); }

		//CHECK 1. ALERT ADMIN THAT BLANK FIELDS ARE PRESENT.
		//CHECK 2. ALERT ADMIN THE PASSWORD IS NOT LONG ENOUGH.
		//CHECK 3. ALERT ADMIN THE PASSWORDS DO NOT MATCH.
		if(missingElements.size() > 0){
			this.missingFields.setText("Please fill in the missing fields:" + "\n" + missingElements);
		} else if(this.addUserPassword.getText().length() < 8){
			this.missingFields.setText("Password's must be at least 8 characters long.");
		} else if (!this.addUserPassword.getText().equals(this.addUserConfirmPassword.getText())){
			this.missingFields.setText("Password's do not match.");
		} else{

			//SET VARIABLES BASED ON USER INPUT.
			final String username = this.addUserUsername.getText();
			final String password = this.addUserPassword.getText();
			final String permLvl = this.permissionBox.getValue();
			final PermissionLevels lvl = PermissionLevels.valueOf(permLvl);

			//CHECK USERNAME IS NOT IN USE.
			u = this.ud.getUser(username);

			if(u != null){
				this.missingFields.setText("Username or password in use.");
				return;
			}

			//CHECK PASSWORD IS NOT IN USE.
			final List<User> ul = this.ud.getAllUsers();

			for(int i=0; i< ul.size(); i++){
				u = ul.get(i);

				final String up = u.getPassword();

				if(up.equals(password)){
					this.missingFields.setText("Username or password in use.");
					return;
				}
			}

			// ADD USER IF ALL IS WELL.
			u = new User(username, password, lvl, true);
			this.ud.addUser(u);
			this.missingFields.setText("User Added!");

			this.addUserUsername.setText("");
			this.addUserPassword.setText("");
			this.addUserConfirmPassword.setText("");
			this.permissionBox.setValue("");
		}
	}


	@FXML
	private void enterKey(final KeyEvent event){

		if(event.getCode() == KeyCode.ENTER){
			if(event.getSource().equals(this.addUserUsername)) {
				this.addUserPassword.requestFocus();
			} else if(event.getSource().equals(this.addUserPassword)) {
				this.addUserConfirmPassword.requestFocus();
			} else if(event.getSource().equals(this.addUserConfirmPassword)) {
				this.permissionBox.requestFocus();
			} else if(event.getSource().equals(this.permissionBox)) {
				this.addUser();
			}
		}
	}


	//***********************************************************************
	//EDIT USER
	//***********************************************************************

	@FXML
	private void searchUsers(){
		u = this.ud.getUser(this.searchUsernames.getText());

		if (u==null){
			this.editUserAlert.setText("User not found.");
			resetFields();
		} else{
			this.showPassword.setText(u.getPassword());
			this.showConfirmPassword.setText(u.getPassword());
			this.showPermissions.setValue(u.getPermissionLevel().toString());
			this.activeBox.setSelected(u.isActive());

			this.editUserAlert.setText("User found.");
			this.updateUserBtn.setDisable(false);
			this.updateCancelBtn.setDisable(false);
			toggleEnabled();
		}
	}

	@FXML
	private void updateCancel(){
		toggleEnabled();
		resetFields();
		this.editUserAlert.setText("User Update Cancelled.");
	}

	@FXML
	private void updateUserDB(){

		if(showPassword.getText().length() < 8){
			this.editUserAlert.setText("Password's must be at least 8 characters long.");
		} else if (!this.showPassword.getText().equals(this.showConfirmPassword.getText())){
			this.editUserAlert.setText("Password's do not match.");
		} else {
			this.updateUserBtn.setDisable(true);
			this.updateCancelBtn.setDisable(true);
			this.editUserAlert.setText("User Updated.");

			u.setPassword(showPassword.getText());
			u.setpLevel(PermissionLevels.valueOf(this.showPermissions.getValue()));
			u.setActive(activeBox.isSelected());
			ud.updateUser(u);

			if(!activeBox.isSelected()){ ud.deleteUser(u.getName()); }
			else if(activeBox.isSelected()){ ud.enableUser(u.getName()); }

			toggleEnabled();
			resetFields();
		}
	}

	@FXML
	private void editEnterKey(final KeyEvent event){

		if(event.getCode() == KeyCode.ENTER){
			if(event.getSource().equals(this.searchUsernames)) {
				this.searchUsers();
				this.showPassword.requestFocus();
			} else if(event.getSource().equals(this.showPassword)) {
				this.showConfirmPassword.requestFocus();
			} else if(event.getSource().equals(this.showConfirmPassword)) {
				this.showPermissions.requestFocus();
			} else if(event.getSource().equals(this.showPermissions)) {
				this.activeBox.requestFocus();
			} else if(event.getSource().equals(this.activeBox)) {
				this.updateUserDB();
			}
		}
	}

	//***********************************************************************
	//FUNCTIONALITY
	//***********************************************************************

	/**
	 *Reset fields
	 */
	public void resetFields(){
		this.searchUsernames.setText("");
		this.showPassword.setText("");
		this.showConfirmPassword.setText("");
		this.showPermissions.setItems(null);
		this.activeBox.setSelected(false);
	}

	/**
	 *Toggle enabled
	 */
	public void toggleEnabled(){
		this.searchUsernames.setEditable(!searchUsernames.isEditable());
		this.showPassword.setEditable(!showPassword.isEditable());
		this.showConfirmPassword.setEditable(!showConfirmPassword.isEditable());
		this.searchUsersBtn.setDisable(!searchUsersBtn.isDisabled());
		this.showPermissions.setDisable(!showPermissions.isDisabled());
		this.activeBox.setDisable(!activeBox.isDisabled());

		this.showPermissions.setItems(this.permissionLevels);
	}

	/**
	 * Set ComboBox items.
	 * enable / disable certain input options.
	 */
	public void initialize(){
		this.showPermissions.setItems(this.permissionLevels);
		this.permissionBox.setItems(this.permissionLevels);
		//ENABLE
		this.searchUsernames.setEditable(true);
		this.searchUsersBtn.setDisable(false);
		//DISABLE
		this.showPassword.setEditable(false);
		this.showConfirmPassword.setEditable(false);
		this.showPermissions.setEditable(false);
		this.activeBox.setDisable(true);
		this.updateUserBtn.setDisable(true);
		this.updateCancelBtn.setDisable(true);
		this.showPermissions.setDisable(true);
	}
}
