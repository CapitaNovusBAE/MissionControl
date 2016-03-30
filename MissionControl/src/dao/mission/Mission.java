package dao.mission;

public class Mission {

	private int missionID;
	private String missionTitle;

	public int getMissionID() {
		return missionID;
	}

	public void setMissionID(int missionID) {
		this.missionID = missionID;

	}

	public String getMissionTittle() {
		return missionTitle;
	}

	public void setMissionTittle(String missionTittle) {
		this.missionTitle = missionTittle;
	}

	@Override
	public String toString() {
		return "Mission: [Mission ID :" + missionID + " Mission Title :" + missionTitle + "]";
	}

	
}
