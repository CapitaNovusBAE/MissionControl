package dao.mission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import dao.AbstractDAO;

/**
 * Data Access Object for Mission class
 * 
 * @author Vadim Khoruzhenko
 *
 */
public class MissionDAO extends AbstractDAO {

	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String COORDINATES = "coordinates";
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
				final Mission mission = new Mission(rs.getInt(ID), rs.getString(TITLE),
						jsonToCoordinates(rs.getString(COORDINATES)));
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

		try {
			final Connection conn = getConnection();
			final String query = "INSERT INTO " + TABLE + "(" + TITLE + "," + COORDINATES + ") VALUES (?,?);";
			prs = conn.prepareStatement(query);
			prs.setString(1, mission.getTittle());
			prs.setString(2, coordinatesToJson(mission.getCoordiantes()));
			return prs.execute() && closeQuietly(conn) && closeQuietly(prs);

		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Get mission from db
	 * 
	 * @param id
	 *            mission id
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
				return new Mission(rs.getInt(ID), rs.getString(TITLE), jsonToCoordinates(rs.getString(COORDINATES)));
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
	 * @param mission
	 *            {@link Mission}
	 * @return true if updated, otherwise false
	 */
	public boolean update(final Mission mission) {
		try {
			final Connection conn = getConnection();
			if (conn == null) {
				return false;
			}
			final String query = "UPDATE " + TABLE + " SET " + TITLE + "='" + mission.getTittle() + "', " + COORDINATES
					+ "='" + coordinatesToJson(mission.getCoordiantes()) + "'  WHERE " + ID + " = " + mission.getID();
			final PreparedStatement prs = conn.prepareStatement(query);
			return prs.execute() && closeQuietly(conn) && closeQuietly(prs);

		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Convert Json string from db to coordinates list
	 */
	private List<Coordinates> jsonToCoordinates(final String coordinatesJson) {

		final List<Coordinates> coordinatesList = new ArrayList<Coordinates>();
		try {
			final JSONArray arr = new JSONArray(coordinatesJson);

			for (int i = 0; i < arr.length(); i++) {
				double x = 0;
				double y = 0;

				final JSONObject json_data = arr.getJSONObject(i);

				if (json_data.has("x")) {
					x = json_data.getDouble("x");
				}
				if (json_data.has("y")) {
					y = json_data.getDouble("y");
				}
				coordinatesList.add(new Coordinates(x, y));
			}
		} catch (final JSONException e) {
			e.printStackTrace();
		}
		return coordinatesList;
	}

	/*
	 * Convert coordinates List to Json string to store in db
	 */
	private String coordinatesToJson(final List<Coordinates> coordinatesList) {

		final Gson gson = new Gson();
		final StringBuilder sb = new StringBuilder();
		for (final Coordinates c : coordinatesList) {
			sb.append(gson.toJson(c));
		}
		return sb.toString();
	}

}
