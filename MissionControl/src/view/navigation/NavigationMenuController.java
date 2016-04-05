package view.navigation;

/**
 * Navigation Menu FXML Controller
 * @author James Paul Novus BAE
 */

import controller.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NavigationMenuController {

	@FXML
	Button homeBtn;

	@FXML
	Button assignBtn;

	@FXML
	Button reviewBtn;

	@FXML
	Button adminBtn;

	private MainApp mainApp;

	@FXML
	private void buttonPress(final ActionEvent event){

		if(event.getSource().equals(this.homeBtn)) {
			this.mainApp.loadHomePage();
		} else if (event.getSource().equals(this.assignBtn)){
			this.mainApp.loadAssignPage();
		} else if (event.getSource().equals(this.reviewBtn)){
			//***********************
			//***********************
		} else if (event.getSource().equals(this.adminBtn)){
			this.mainApp.loadAdminPage();
		}
	}

	public void setMainApp(final MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}
}
