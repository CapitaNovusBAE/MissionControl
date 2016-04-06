package view.mission.assign;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.SignInAuthenticator;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Fligh Details Controller
 * @author Ebe
 *
 */
public class FlightDetailsController implements Initializable{
	@FXML
	private TextField missionNum ;
	@FXML
	private TextField distance;
	@FXML
	private TextField runtime;
	@FXML
	private TextField date;
	@FXML
	private TextField starttime;
	@FXML
	private Button changeDataBtn;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		/*this.missionNum = new TextField();
		this.missionNum.setEditable(false);
		this.missionNum.setText("ondfv");*/
	}
	
	/**
	 * Action on ChangeData button click
	 */
	@FXML 
	public void changeData(final ActionEvent event){
		
		this.missionNum.setEditable(false);
		this.missionNum.setText("ondfv");
		
	}
}
