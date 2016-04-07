package view.mission.assign;

import java.io.IOException;

import dao.mission.Mission;
import dao.mission.MissionDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

/**
 * @author Controller for
 *
 */
public class AssignPageController{

	@FXML
	private Tab flightPathTab;

	@FXML
	private Tab flighDetailsTab;

	@FXML
	private Tab flightReviewTab;


	private Mission mission;
	private MissionDAO mdao;
	private String userName = "Unknown";

	/**
	 * action on selection changed
	 */
	public void selected(){
		//		if(this.mission!=null) {
		//			if(this.mdao.get(this.mission.getID())==null){
		//			saveMission();
		//			}
		//			else{
		//			updateMission();
		//			}
		//		}
	}

	/**
	 * Save mission in database
	 */
	public void saveMission(){
		System.out.println("Save mission called");
		if(this.mdao!=null){
			System.out.println("Save mission " + this.mdao.add(this.mission));
		}

	}
	/**
	 * Update mission in database
	 */
	public void updateMission(){
		System.out.println("update mission called");
		this.mdao.update(this.mission);
	}

	/**
	 * @param userName logged user name
	 */
	public void setUserName(final String userName) {
		this.userName = userName;
		this.mission = new Mission(this.userName, "Untitled");
		this.mdao = new MissionDAO();

		FXMLLoader fxmlLoader = new FXMLLoader();
		try {
			final SplitPane p = fxmlLoader.load(getClass().getResource("/view/mission/assign/FlightDetails.fxml").openStream());
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final FlightDetailsController flightDetailsController = (FlightDetailsController) fxmlLoader.getController();

		//		this.flightPathPage.getC
		//		this.flightPathController.setMission(this.mission);
		flightDetailsController.init(this.mission);

		fxmlLoader = new FXMLLoader();

		try {
			final SplitPane p = fxmlLoader.load(getClass().getResource("/view/mission/assign/FlightPathPage.fxml").openStream());
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final FlightPathController flightPathController = (FlightPathController) fxmlLoader.getController();

		flightPathController.setMission(this.mission);

		fxmlLoader = new FXMLLoader();

		try {
			final AnchorPane p = fxmlLoader.load(getClass().getResource("/view/mission/assign/FlightReviewPage.fxml").openStream());
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final FlightReviewPageController flightReviewPageController = (FlightReviewPageController) fxmlLoader.getController();

		flightReviewPageController.setMission(this.mission);
	}



}
