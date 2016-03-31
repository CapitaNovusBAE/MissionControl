package dao.mission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import dao.AbstractDAO;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;

/**
 * Data Access Object for Mission class
 *
 * @author Vadim Khoruzhenko
 *
 */
public class MissionDAO extends AbstractDAO {

	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String USERNAME = "username";
	private static final String POSITIONS = "positions";
	private static final String COMMENTS = "comments";
	private static final String DEPARTURE = "departure";
	private static final String ARRIVAL = "arrival";
	private static final String TABLE = "missions";

	/**
	 * @return all missions from db
	 */
	public List<Mission> getAll() {
		final List<Mission> list = new ArrayList<Mission>();
		final Connection conn = getConnection();
		PreparedStatement prs = null;
		ResultSet rs = null;
		try {

			final String query = "SELECT * FROM " + TABLE;
			prs = conn.prepareStatement(query);
			rs = prs.executeQuery();

			while (rs.next()) {
				final Mission mission = new Mission(rs.getInt(ID), rs.getString(USERNAME),rs.getString(TITLE),
						jsonToPosition(rs.getString(POSITIONS)),stringToCommentList(rs.getString(COMMENTS)),rs.getDate(DEPARTURE),rs.getDate(ARRIVAL));
				list.add(mission);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			closeQuietly(conn);
			closeQuietly(prs);
			closeQuietly(rs);
		}

		return list;
	}

	/**
	 * Add new mission to db
	 *
	 * @param mission
	 *            {@link Mission}
	 * @return true if mission was successfully added
	 */
	public boolean add(final Mission mission) {
		PreparedStatement prs = null;
		final Connection conn = getConnection();
		boolean result = false;
		try {
			final String query = "INSERT INTO " + TABLE + "(" + TITLE + "," +  USERNAME + "," + POSITIONS + "," +  COMMENTS + ","+ DEPARTURE + "," + ARRIVAL + "," + ") VALUES (?,?,?,?,?,?);";
			prs = conn.prepareStatement(query);
			prs.setString(1, mission.getTittle());
			prs.setString(2, mission.getUsername());
			prs.setString(3, positionsToJson(mission.getPositions()));
			prs.setString(4, commentsListToString(mission.getComments()));
			prs.setDate(5,mission.getDepartureDate());
			prs.setDate(6,mission.getArrivalDate());
			result = prs.execute();

		} catch (final Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result&&closeQuietly(conn)&&closeQuietly(prs);
	}

	/**
	 * Get mission from db
	 *
	 * @param id mission id
	 * @return Mission by id
	 */
	public Mission get(final int id) {
		PreparedStatement prs = null;
		final Connection conn = getConnection();
		ResultSet rs =null;
		try {

			final String query = "SELECT * FROM " + TABLE + " WHERE " + ID + " = " + id;
			prs = conn.prepareStatement(query);
			rs = prs.executeQuery();
			while (rs.next()) {
				return new Mission(rs.getInt(ID), rs.getString(USERNAME),rs.getString(TITLE),
						jsonToPosition(rs.getString(POSITIONS)),stringToCommentList(rs.getString(COMMENTS)),rs.getDate(DEPARTURE),rs.getDate(ARRIVAL));
			}

		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			closeQuietly(conn);
			closeQuietly(prs);
			closeQuietly(rs);
		}
		return null;
	}

	/**
	 * @param mission  {@link Mission}
	 * @return true if updated, otherwise false
	 */
	public boolean update(final Mission mission) {
		final Connection conn = getConnection();
		PreparedStatement prs = null;
		boolean result = false;

		try {
			final String query = "UPDATE " + TABLE + " SET " + TITLE + "='" + mission.getTittle() + "', " + USERNAME + "='" + mission.getUsername() + "', " + POSITIONS
					+ "='" + positionsToJson(mission.getPositions()) + "'" + COMMENTS +"='" + commentsListToString(mission.getComments()) + "'" + DEPARTURE + " = " + mission.getDepartureDate() + "," + ARRIVAL + " = " + mission.getArrivalDate() + "  WHERE " + ID + " = " + mission.getID();
			prs = conn.prepareStatement(query);
			result = prs.execute();
		} catch (final Exception e) {
			e.printStackTrace();
			result = false;
		}

		return result&&closeQuietly(conn)&&closeQuietly(prs);
	}

	/*
	 * Convert string from db to comment list
	 */
	private List<String> stringToCommentList(final String commentsJson) {
		return  Arrays.asList(commentsJson.split(","));
	}

	/*
	 * Convert comments List to string to store in db
	 */
	private String commentsListToString(final List<String> comments) {

		final StringBuilder sb = new StringBuilder();
		for (final String c : comments) {
			sb.append(c);
			if(comments.indexOf(c)<comments.size()){
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/*
	 * Convert Json string from db to position list
	 */
	private List<Position> jsonToPosition(final String positionJson) {

		final List<Position> coordinatesList = new ArrayList<Position>();
		try {
			final JSONArray arr = new JSONArray(positionJson);

			for (int i = 0; i < arr.length(); i++) {
				double latitude = 0;
				double longitude = 0;
				double elevation= 0;

				final JSONObject json_data = arr.getJSONObject(i);

				if (json_data.has("latitude")) {
					latitude = json_data.getDouble("latitude");
				}
				if (json_data.has("longitude")) {
					longitude = json_data.getDouble("longitude");
				}
				if (json_data.has("elevation")) {
					elevation = json_data.getDouble("elevation");
				}
				coordinatesList.add(new Position(Angle.fromDegrees(latitude),Angle.fromDegrees(longitude), elevation));
			}
		} catch (final JSONException e) {
			e.printStackTrace();
		}
		return coordinatesList;
	}

	/*
	 * Convert positions List to Json string to store in db
	 */
	private String positionsToJson(final List<Position> positionsList) {

		final Gson gson = new Gson();
		final StringBuilder sb = new StringBuilder();
		for (final Position p : positionsList) {
			sb.append(gson.toJson(p));
		}
		return sb.toString();
	}

}
