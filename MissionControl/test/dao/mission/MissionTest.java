package dao.mission;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;

/**
 * Test cases for {@link Mission}
 * @author Vadim Khoruzhenko
 *
 */
public class MissionTest {

	private static final int ID = 1;
	private static final String USERNAME="user";
	private static final String MISSION_TITLE="title";
	private final List<Position> positionList = new ArrayList<>();
	private final List<String> commentList = new ArrayList<>();
	private static final Date DEPARTURE_DATE = new Date(0);
	private static final Date ARRIVAL_DATE = new Date(1);
	private final Position position = new Position(LatLon.fromDegrees(11.11111,22.22222),33.3333);
	private final String comment = "Comment";


	/**
	 * Test for {@link Mission#Mission(String, String)}
	 */
	@Test
	public void testMissionStringString() {
		final Mission m = new Mission(USERNAME, MISSION_TITLE);
		Assert.assertEquals(USERNAME, m.getUsername());
		Assert.assertEquals(MISSION_TITLE, m.getTittle());
		Assert.assertEquals(0, m.getPositions().size());
		Assert.assertEquals(0, m.getComments().size());
		Assert.assertEquals(null, m.getDepartureDate());
		Assert.assertEquals(null, m.getArrivalDate());
	}

	/**
	 * Test for {@link Mission#Mission(int, String, String, List, List, Date, Date)}
	 */
	@Test
	public void testMissionIntStringStringListOfPositionListOfStringDateDate() {
		final Mission m = new Mission(ID,USERNAME, MISSION_TITLE,this.positionList,this.commentList,DEPARTURE_DATE,ARRIVAL_DATE);
		Assert.assertEquals(USERNAME, m.getUsername());
		Assert.assertEquals(MISSION_TITLE, m.getTittle());
		Assert.assertEquals(this.positionList, m.getPositions());
		Assert.assertEquals(this.commentList, m.getComments());
		Assert.assertEquals(DEPARTURE_DATE, m.getDepartureDate());
		Assert.assertEquals(ARRIVAL_DATE, m.getArrivalDate());
	}


	/**
	 * Test {@link Mission#addPositions(Position)}
	 */
	@Test
	public void testAddPositions() {
		final Mission m = new Mission(USERNAME, MISSION_TITLE);
		m.addPositions(this.position);
		Assert.assertEquals(1, m.getPositions().size());
		Assert.assertEquals(this.position, m.getPositions().get(0));
	}


	/**
	 *  Test {@link Mission#removePosition(Position)}
	 */
	@Test
	public void testRemovePosition() {
		final Mission m = new Mission(USERNAME, MISSION_TITLE);
		m.addPositions(this.position);
		Assert.assertEquals(1, m.getPositions().size());
		m.removePosition(this.position);
		Assert.assertEquals(0, m.getPositions().size());
	}

	/**
	 * Test for {@link Mission#addComment(String)}
	 */
	@Test
	public void testAddComment() {
		final Mission m = new Mission(USERNAME, MISSION_TITLE);
		m.addComment(this.comment);
		Assert.assertEquals(1, m.getComments().size());
		Assert.assertEquals(this.comment, m.getComments().get(0));
	}

	/**
	 * Test for {@link Mission#removeComment(String)}
	 */
	@Test
	public void testRemoveComment() {
		final Mission m = new Mission(USERNAME, MISSION_TITLE);
		m.addComment(this.comment);
		Assert.assertEquals(1, m.getComments().size());
		m.removeComment(this.comment);
		Assert.assertEquals(0, m.getComments().size());
	}

	/**
	 * Test for {@link Mission#setDepartureDate(Date)}
	 */
	@Test
	public void testSetDepartureDate() {
		final Mission m = new Mission(USERNAME, MISSION_TITLE);
		final Date date = new Date(2);
		m.setDepartureDate(date);
		Assert.assertEquals(date, m.getDepartureDate());
	}

	/**
	 * Test for {@link Mission#setArrivalDate(Date)}
	 */
	@Test
	public void testSetArrivalDate() {
		final Mission m = new Mission(USERNAME, MISSION_TITLE);
		final Date date = new Date(2);
		m.setArrivalDate(date);
		Assert.assertEquals(date, m.getArrivalDate());
	}

	/**
	 * Test for {@link Mission#setUsername(String)}
	 */
	@Test
	public void testSetUsername() {
		final Mission m = new Mission(USERNAME, MISSION_TITLE);
		final String newUser = "new user";
		m.setUsername(newUser);
		Assert.assertEquals(newUser, m.getUsername());
	}

}

