package dao.mission;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import gov.nasa.worldwind.geom.Position;

/**
 * Mission object
 * @author Vadim Khoruzhenko
 *
 */
public class Mission {

	private int missionID;
	private final String missionTitle;
	private final List<Position> postionList;
	private final List<String> comments;
	private String userName;
	private Date departure;
	private Date arrival;

	/**Constructor
	 * @param userName the user who created/edited the mission
	 * @param missionTitle a mission's title
	 */
	public Mission(final String userName, final String missionTitle) {
		this.userName = userName;
		this.missionTitle = missionTitle;
		this.postionList=new ArrayList<Position>();
		this.comments = new ArrayList<String>();
	}

	/**Constructor to create a mission object from db
	 * @param missionID mission's id
	 * @param userName User created/edited the mission
	 * @param missionTitle mission's title
	 * @param positionList mission's coordinates
	 * @param comments Comments
	 * @param departure departure date
	 * @param arrival arrival date
	 */
	public Mission(final int missionID, final String userName, final String missionTitle,final List<Position> positionList, final List<String> comments, final Date departure, final Date arrival) {
		this.missionID = missionID;
		this.missionTitle = missionTitle;
		this.postionList = positionList;
		this.comments = comments;
		this.userName = userName;
		this.departure = departure;
		this.arrival = arrival;
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
	 * @return all positions for to the mission
	 */
	public List<Position> getPositions(){
		return this.postionList;
	}
	/**Add new position to the positions' list
	 * @param position {@link Position}
	 */
	public void addPositions(final Position position){
		this.postionList.add(position);
	}
	/**Remove position from the positions' list
	 * @param position {@link Position}
	 */
	public void removePosition(final Position position){
		this.postionList.remove(position);
	}

	/**
	 * @return all comments for to a mission
	 */
	public List<String> getComments(){
		return this.comments;
	}

	/**
	 *  Add comment for to a mission
	 * @param comment String comment
	 */
	public void addComment(final String comment){
		this.comments.add(comment);
	}

	/**Remove comment
	 * @param comment string comment
	 */
	public void removeComment(final String comment){
		this.comments.remove(comment);
	}
	/**
	 * @return departure {@link Date}
	 */
	public Date getDepartureDate(){
		return this.departure;
	}

	/**
	 * Set departure date
	 * @param departure Departure date
	 */
	public  void setDepartureDate(final Date departure){
		this.departure = departure;
	}

	/**
	 * @return arrival {@link Date}
	 */
	public Date getArrivalDate(){
		return this.arrival;
	}

	/**
	 * Set arrival date
	 * @param arrival
	 */
	public  void setArrivalDate(final Date arrival){
		this.arrival = arrival;
	}

	/**
	 * @return name of latest user worked with the mission
	 */
	public String getUsername(){
		return this.userName;
	}

	/**
	 * Set name of user created/edited the mission
	 * @param userName Name of latest user worked with the mission
	 */
	public void setUsername(final String userName){
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Mission [missionID=" + this.missionID + ", missionTitle=" + this.missionTitle + ", postions="
				+ (this.postionList!=null?this.postionList.size():"0") + ", comments=" + (this.comments!=null?this.comments.size():"0") + ", username=" + this.userName + ", departure="
				+ this.departure + ", arrival=" + this.arrival + "]";
	}

}
