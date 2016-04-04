package view.layout;

import controller.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LayoutController {

	@FXML
	Button home;
	@FXML
	Button assign;
	@FXML
	Button review;
	@FXML
	Button admin;

	private MainApp mainApp;

	@FXML
    private void buttonPress(ActionEvent event){

    	if(event.getSource().equals(home)) {
    		//***********************
    		//***********************
    	} else if (event.getSource().equals(assign)){
    		//***********************
    		//***********************
    	} else if (event.getSource().equals(review)){
    		//***********************
    		//***********************
    	} else if (event.getSource().equals(admin)){
    		mainApp.adminPage();
    	}
    }

	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}
}
