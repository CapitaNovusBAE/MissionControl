package view.mission.review;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainApp;
import dao.mission.Mission;
import dao.mission.MissionDAO;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import map.MapView;

public class ReviewPageController implements Initializable {

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
	@FXML
	private SwingNode mapNode;

	private Mission mission;

	private MissionDAO mdao;

	private MapView mapView;

	public void setMainApp(final MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}

	public void addComment(){
	}


	public void findMission(){
		this.mission = this.mdao.get(Integer.parseInt(this.txtMissionID.getText()));
	}


	public void deleteMission(){
		this.mdao.delete(this.mission);
	}


	public void updateMission(){

		this.mdao.update(this.mission);
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		this.mdao= new MissionDAO();
		this.mapView = new MapView();
		this.mapNode.setContent(this.mapView.getMap());
	}
}
