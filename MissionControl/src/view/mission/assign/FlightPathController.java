package view.mission.assign;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import gov.nasa.worldwind.geom.LatLon;
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

/**
 * @author Controller for
 *
 */
public class FlightPathController extends AssignPageController implements Initializable {

	private static final long DELAY = 100;

	@FXML
	private ListView<Position> positionsView;

	@FXML
	private SwingNode mapNode;

	@FXML
	private TextField latitude;

	@FXML
	private TextField longtitude;

	@FXML
	private TextField elevation;

	@FXML
	private Button addWaypointButton;;

	private MapView mapView;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		this.mapView = new MapView();
		this.mapNode.setContent(this.mapView.getMap());
	}

	/**
	 * Action on add waypoint button click
	 */
	public void addWaypoint(){

		this.mapView.getPositions().add(new Position(LatLon.fromDegrees(Double.parseDouble(this.latitude.getText()),Double.parseDouble(this.longtitude.getText())),Double.parseDouble(this.elevation.getText())));
		this.mapView.updateView();

		this.latitude.clear();
		this.longtitude.clear();
		this.elevation.clear();

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
						final ObservableList<Position> positions = FlightPathController.this.positionsView.getItems();
						positions.clear();
						positions.addAll(FlightPathController.this.mapView.getPositions());
					}
				});
			}
		}, DELAY);
	}
}
