package view.mission.review;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainApp;
import dao.mission.Mission;
import dao.mission.MissionDAO;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
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
	private ListView<Position> lstPositions;
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
		addWaypoints();
	}


	public void deleteMission(){
		this.mdao.delete(this.mission);
	}


	public void updateMission(){
		this.mission.getPositions().clear();
		this.mission.getPositions().addAll(mapView.getPositions());
		this.mdao.update(this.mission);
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		this.mdao= new MissionDAO();
		this.mapView = new MapView();
		this.mapNode.setContent(this.mapView.getMap());
	}

	public void addWaypoints(){

		this.mapView.getPositions().addAll(mission.getPositions());		
		this.mapView.updateView();		
		synchronized (this.mapView.getLock()) {

			updateView();
		}
	}
	/**
	 * Update ListView on map click
	 */
	public void  updateView(){
		final ObservableList<Position> positions = this.lstPositions.getItems();
		positions.clear();
		System.out.println(" in review control" +mapView.getPositions().size());
		positions.addAll(this.mapView.getPositions());
	}
}
