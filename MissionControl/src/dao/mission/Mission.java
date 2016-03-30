package dao.mission;

import java.util.ArrayList;
import java.util.List;

public class Mission {

	private int missionID;
	private String missionTitle;
	private List<Coordinates> coordinatesList;

	public Mission(String missionTitle) {
		this.missionTitle = missionTitle;
		this.coordinatesList=new ArrayList<Coordinates>();
	}
	
	public Mission(int missionID, String missionTitle,List<Coordinates> coordinates) {
		this.missionID = missionID;
		this.missionTitle = missionTitle;
		this.coordinatesList = coordinates;
	}
	
	public int getID() {
		return missionID;
	}

	public String getTittle() {
		return missionTitle;
	}

	public void setTittle(String missionTittle) {
		this.missionTitle = missionTittle;
	}
	
	public List<Coordinates> getCoordiantes(){
		return coordinatesList;
	}
	public void addCoordiantes(Coordinates coordinates){
		coordinatesList.add(coordinates);
	}
	public void removeCoordiantes(Coordinates coordinates){
		coordinatesList.remove(coordinates);
	}

	@Override
	public String toString() {
		return "Mission: [ID :" + missionID + " Title :" + missionTitle + "]";
	}
	
}
