package dao.mission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.AbstractDAO;

public class MissionDAO extends AbstractDAO {

	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String CORDINATES = "cordinates";
	private static final String TABLE="missions";

	public List<Mission> getAll(){
		List<Mission> list = new ArrayList<Mission>();
		try {
			Connection conn =getConnection();
			if(conn==null){
				return list;
			}
			PreparedStatement prs = null;
			ResultSet rs = null;
			String query = "SELECT * FROM " + TABLE;
			prs = conn.prepareStatement(query);
			rs = prs.executeQuery();
			
			while (rs.next()) {
				Mission mission = new Mission(rs.getInt(ID), rs.getString(TITLE),jsonToCoordinates(rs.getString(CORDINATES)));
				list.add(mission);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;	
	}


	public boolean add(Mission mission){
		PreparedStatement prs = null;

		try {
			Connection conn = getConnection();
			if(conn==null){
				return false;
			}
			String query = "INSERT INTO " + TABLE +  "(" + TITLE+ "," + CORDINATES + ") VALUES (?,?);";
			prs = conn.prepareStatement(query);
			prs.setString(1, mission.getTittle());
			prs.setString(2, coordinatesToJson(mission.getCoordiantes()));
			return prs.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Mission get(int id){
		PreparedStatement prs = null;
				try {
			Connection conn = getConnection();
			if(conn==null){
				return null;
			}
			String query = "SELECT * FROM "+TABLE+" WHERE " + ID + " = " +  id;
			prs = conn.prepareStatement(query);
			ResultSet rs = prs.executeQuery();
			while (rs.next()) {
				return new Mission(rs.getInt(ID), rs.getString(TITLE),jsonToCoordinates(rs.getString(CORDINATES)));
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	public boolean update(Mission mission){
		try {
			Connection conn = getConnection();
			if(conn==null){
				return false;
			}
			String query = "UPDATE "+ TABLE +" SET " + TITLE + "='" + mission.getTittle()+ "', " + CORDINATES + "='" + coordinatesToJson(mission.getCoordiantes()) + "'  WHERE " + ID + " = "+ mission.getID();
			PreparedStatement prs = conn.prepareStatement(query);
			return prs.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	private List<Coordinates> jsonToCoordinates(String json){
		
		return new ArrayList<Coordinates>();
		//TODO
	}
	private String coordinatesToJson(List<Coordinates> coordinates){
		
		return null;
		//TODO
	}

}
