package view.assign.flightdetails;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainApp;
import dao.mission.Mission;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class FlightDetailsController implements Initializable{

	@FXML
	private ListView<Position> positionsView;;

	private MainApp mainApp;

	private Mission mission;


	public void setMainApp(final MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
		this.mission = new Mission(mainApp.getUser().getName(),"Untitled");
	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		final ObservableList<Position> positions = this.positionsView.getItems();

		positions.add(new Position(LatLon.fromDegrees(11.11,22.22),33.33));
		positions.add(new Position(LatLon.fromDegrees(11.11,22.22),33.33));
		positions.add(new Position(LatLon.fromDegrees(11.11,22.22),33.33));
		positions.add(new Position(LatLon.fromDegrees(11.11,22.22),33.33));

		//		this.mainApp.setMission(this.mission);
	}
}
