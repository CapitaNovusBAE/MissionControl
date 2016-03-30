package dao.mission;

import java.util.ArrayList;
import java.util.List;

/**
 * Mission object
 * @author Vadim Khoruzhenko
 *
 */
public class Mission {

	private int missionID;
	private final String missionTitle;
	private final List<Coordinates> coordinatesList;

	/**Constructor
	 * @param missionTitle a mission's title
	 */
	public Mission(final String missionTitle) {
		this.missionTitle = missionTitle;
		this.coordinatesList=new ArrayList<Coordinates>();
	}

	/**Constructor to create a mission object from db
	 * @param missionID mission's id
	 * @param missionTitle mission's title
	 * @param coordinates mission's coordinates
	 */
	public Mission(final int missionID, final String missionTitle,final List<Coordinates> coordinates) {
		this.missionID = missionID;
		this.missionTitle = missionTitle;
		this.coordinatesList = coordinates;
	}

	/**
	 * @return mission's id
	 */
	public int getID() {
		return this.missionID;
	}

	/**
	 * @return mission's title
	 */
	public String getTittle() {
		return this.missionTitle;
	}

	/**
	 * @return all coordinates for to a mission
	 */
	public List<Coordinates> getCoordiantes(){
		return this.coordinatesList;
	}
	/**Add new coordinates to the coordinates' list
	 * @param coordinates {@link Coordinates}
	 */
	public void addCoordiantes(final Coordinates coordinates){
		this.coordinatesList.add(coordinates);
	}
	/**Remove coordinates from the coordinates' list
	 * @param coordinates {@link Coordinates}
	 */
	public void removeCoordiantes(final Coordinates coordinates){
		this.coordinatesList.remove(coordinates);
	}

	@Override
	public String toString() {
		return "Mission: [ID :" + this.missionID + " Title :" + this.missionTitle + "]";
	}

}
