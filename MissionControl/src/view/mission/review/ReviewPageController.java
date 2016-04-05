package view.mission.review;

import controller.MainApp;
import dao.mission.Mission;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ReviewPageController {

	@SuppressWarnings("unused")
	private MainApp mainApp;

	@FXML 
	private Button btnAddComment;
	@FXML
	private Button btnDeleteMission;
	@FXML
	private Button btnUpdateMission;
	@FXML
	private Button btnFindMission;
	@FXML
	private TextField txtMissionID;
	@FXML
	private ListView<String> lstMissionComments;


	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}
	
	public void addComment(){
		
		
	}
	
	
	public boolean findMission(int missionID){
		
		return true;
	}
	
	
	public boolean deleteMission(int missionID){		
		
		return true;
	}
	
	
	public boolean updateMission(Mission mission){
		
		return true;
	}
}
