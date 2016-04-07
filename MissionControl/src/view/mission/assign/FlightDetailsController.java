package view.mission.assign;

import java.net.URL;
import java.util.ResourceBundle;

import dao.mission.Mission;
import dao.mission.MissionDAO;
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
	private TextField missionTitle ;
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

	private Mission mission;

	/**
	 * Action on ChangeData button click
	 */
	public void changeData(){
		this.mission.setTittle(this.missionTitle.getText());
		final MissionDAO mdao = new MissionDAO();
		mdao.update(this.mission);
	}

	/**
	 * @param mission assigned {@link Mission}
	 */
	public void init(final Mission mission) {
		this.mission = mission;
		//		this.missionTitle.setText(this.mission.getTittle());
		this.missionTitle.setText("Mission 1");
		//		this.missionNum.setText(String.valueOf(this.mission.getID()));
		this.missionNum.setText(String.valueOf(12));
		this.distance.setText("Not implemented yet");
		this.runtime.setText("Not implemented yet");

	}

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		this.missionTitle.setText("Mission 1");
		//		this.missionNum.setText(String.valueOf(this.mission.getID()));
		this.missionNum.setText(String.valueOf(12));
		this.distance.setText("Not implemented yet");
		this.runtime.setText("Not implemented yet");

	}
}
