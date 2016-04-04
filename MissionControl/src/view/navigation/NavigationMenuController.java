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
    private void buttonPress(ActionEvent event){

    	if(event.getSource().equals(homeBtn)) {
    		mainApp.homePage();
    	} else if (event.getSource().equals(assignBtn)){
    		//***********************
    		//***********************
    	} else if (event.getSource().equals(reviewBtn)){
    		//***********************
    		//***********************
    	} else if (event.getSource().equals(adminBtn)){
    		mainApp.adminPage();
    	}
    }



	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}
}
