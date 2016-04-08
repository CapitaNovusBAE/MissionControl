package view.navigation;

import controller.MainApp;
import dao.user.User.PermissionLevels;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.FXMLControllerAbstract;

/**
 * Navigation Menu FXML Controller
 * @author James Paul
 */
public class NavigationMenuController extends FXMLControllerAbstract{

	@FXML
	Button homeBtn;
	@FXML
	Button assignBtn;
	@FXML
	Button reviewBtn;
	@FXML
	Button adminBtn;

	@FXML
	private void buttonPress(final ActionEvent event) {
		if (event.getSource().equals(this.homeBtn)) {
			this.mainApp.loadHomePage();
		} else if (event.getSource().equals(this.assignBtn)) {
			this.mainApp.loadAssignPage();
		} else if (event.getSource().equals(this.reviewBtn)) {
			this.mainApp.loadReviewPage();
		} else if (event.getSource().equals(this.adminBtn)) {
			this.mainApp.loadAdminPage();
		}
	}

	@Override
	public void setMainApp(final MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;

		if (!mainApp.getUser().getPermissionLevel().equals(PermissionLevels.HIGH)) {
			adminBtn.setDisable(true);
		}

		if (mainApp.getUser().getPermissionLevel().equals(PermissionLevels.LOW)) {
			assignBtn.setDisable(true);
		}
	}

}
