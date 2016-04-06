package view.mission.assign;

import dao.mission.Mission;
import dao.mission.MissionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

/**
 * @author Controller for
 *
 */
public class AssignPageController{

	@FXML
	private Tab flightPathTab;
	@FXML
	private Tab flighDetailsTab;
	

	private Mission mission;
	private MissionDAO mdao;
	private String userName;


	public Mission getMission(){
		return this.mission;
	}

	public String getUserName(){
		return this.userName;
	}
	/**
	 * Save mission in database
	 */
	public void saveMission(){
		System.out.println("Save mission called");
		if(this.mdao!=null){
			this.mdao.add(this.mission);
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
		this.mission = new Mission(userName, "Untitled");
		this.mdao = new MissionDAO();
	}

}
