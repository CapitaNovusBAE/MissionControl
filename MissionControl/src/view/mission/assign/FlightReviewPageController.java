package view.mission.assign;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import dao.mission.Mission;
import dao.mission.MissionDAO;
import gov.nasa.worldwind.geom.Position;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import map.MapView;

public class FlightReviewPageController implements Initializable {

	@FXML
	private ListView<Position> lstPositions;
	@FXML
	private SwingNode mapNode;

	private Mission mission;

	private MissionDAO mdao;

	private MapView mapView;

	private static final long DELAY= 100;

	public void setMission(final Mission mission){
		this.mission = mission;
		addWaypoints();
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		this.mdao= new MissionDAO();
		this.mapView = new MapView();
		this.mapNode.setContent(this.mapView.getMap());
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
	 */
	public void updateView(){
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						final ObservableList<Position> positions = FlightReviewPageController.this.lstPositions.getItems();
						positions.clear();
						positions.addAll(FlightReviewPageController.this.mapView.getPositions());
					}
				});
			}
		}, DELAY);
	}
}