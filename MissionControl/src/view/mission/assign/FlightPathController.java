package view.mission.assign;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import controller.MainApp;
import dao.mission.Mission;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * @author Controller for
 *
 */
public class FlightPathController implements Initializable{

	@FXML
	private ListView<Position> positionsView;;

	@FXML
	private SwingNode mapView;

	@FXML
	private TextField latitude;

	@FXML
	private TextField longtitude;

	@FXML
	private TextField elevation;

	@FXML
	private Button addWaypointButton;;


	private MainApp mainApp;

	private Mission mission;


	public void setMainApp(final MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		this.mission = new Mission("user", "Untitled");
	}

	/**
	 * Action on add waypoint button click
	 */
	public void addWaypoint(){
		this.mission.addPositions(new Position(LatLon.fromDegrees(Double.parseDouble(this.latitude.getText()),Double.parseDouble(this.longtitude.getText())),Double.parseDouble(this.elevation.getText())));
		this.latitude.clear();
		this.longtitude.clear();
		this.elevation.clear();
		updateView();
	}

	private void updateView(){
		final ObservableList<Position> positions = this.positionsView.getItems();
		positions.clear();
		positions.addAll(this.mission.getPositions());
	}

	private JPanel getMap(){

		//create a WorldWind main object
//		final WorldWindowGLCanvas worldWindCanvas = new WorldWindowGLCanvas();
//		worldWindCanvas.setModel(new BasicModel());
//
//		//build Java swing interface
		final JPanel panel = new JPanel();
//		panel.add(worldWindCanvas);
//		panel.setSize(800,600);
//		panel.setVisible(true);

			return panel;
	}

}
