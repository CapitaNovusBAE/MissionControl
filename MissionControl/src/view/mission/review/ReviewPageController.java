package view.mission.review;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import controller.MainApp;
import dao.mission.Mission;
import dao.mission.MissionDAO;
import gov.nasa.worldwind.geom.Position;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
	private ListView<Position> lstPositions;
	@FXML
	private SwingNode mapNode;

	private Mission mission;

	private MissionDAO mdao;

	private MapView mapView;

	private static final long DELAY= 100;

	public void setMainApp(final MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}

	/**
	 * Action on add comment button click
	 */
	public void addComment(){

	}


	/**
	 * Action on find mission button click
	 */
	public void findMission(){
		this.mission = this.mdao.get(Integer.parseInt(this.txtMissionID.getText()));
		if(this.mission!=null){
			addWaypoints();
			this.btnAddComment.setDisable(false);
			this.btnDeleteMission.setDisable(false);
			this.btnUpdateMission.setDisable(false);
		}
	}


	/**
	 * Action on delete mission click
	 */
	public void deleteMission(){
		this.mdao.delete(this.mission);
	}


	/**
	 * Action on update button click
	 */
	public void updateMission(){
		this.mission.getPositions().clear();
		this.mission.getPositions().addAll(this.mapView.getPositions());
		this.mdao.update(this.mission);
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		this.mdao= new MissionDAO();
		this.mapView = new MapView();
		this.mapNode.setContent(this.mapView.getMap());
		this.btnAddComment.setDisable(true);
		this.btnDeleteMission.setDisable(true);
		this.btnUpdateMission.setDisable(true);
	}

	/**
	 * Add positions to the map from mission
	 */
	public void addWaypoints(){
		this.mapView.getPositions().addAll(this.mission.getPositions());
		this.mapView.updateView();
		updateView();
	}
	/**
	 * Update ListView on map click
	 * Vadim
	 */
	public void updateView(){
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						final ObservableList<Position> positions = ReviewPageController.this.lstPositions.getItems();
						positions.clear();
						positions.addAll(ReviewPageController.this.mapView.getPositions());
					}
				});
			}
		}, DELAY);
	}
}
