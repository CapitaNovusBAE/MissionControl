package dao.mission;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import gov.nasa.worldwind.geom.Position;


/**
 * Test for {@link MissionDAO}
 * @author Vadim Khoruzhenko
 *
 */
public class MisionDAOTest {

	@Mock
	private Connection connection;

	@Mock
	private PreparedStatement ps;

	@Mock
	private ResultSet rs;

	private MissionDAO missionDAO;

	/**
	 * Set up the tests before running
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.missionDAO = Mockito.spy(new MissionDAO());

		Mockito.doReturn(this.connection).when(this.missionDAO).getConnection();
		Mockito.when(this.connection.prepareStatement(Mockito.any())).thenReturn(this.ps);
		Mockito.when(this.ps.executeQuery()).thenReturn(this.rs);
		Mockito.when(this.rs.next()).thenReturn(true).thenReturn(false);
		Mockito.when(this.rs.getInt("id")).thenReturn(1);
		Mockito.when(this.rs.getString("title")).thenReturn("Mission 1");
		Mockito.when(this.rs.getString("username")).thenReturn("User");
		Mockito.when(this.rs.getString("positions")).thenReturn("[{\"latitude\":\"11.11\",\"longitude\":\"22.22\",\"elevation\":\"33.33\"},{\"latitude\":\"112.112\",\"longitude\":\"222.222\",\"elevation\":\"332.332\"},{\"latitude\":\"113.113\",\"longitude\":\"223.223\",\"elevation\":\"333.333\"}]");
		Mockito.when(this.rs.getString("comments")).thenReturn("Comment 1,Comment 2,Comment 3");
		Mockito.when(this.rs.getDate("departure")).thenReturn(new Date(0));
		Mockito.when(this.rs.getDate("arrival")).thenReturn(new Date(0));
		Mockito.when(this.ps.execute()).thenReturn(true);
	}

	/**
	 * Test for {@link MissionDAO#getAll()}
	 * @throws SQLException
	 */
	@Test
	public void testGetAll() throws SQLException {
		final List<Mission> missions = this.missionDAO.getAll();

		Mockito.verify(this.connection).prepareStatement(Mockito.eq("SELECT * FROM missions"));
		Mockito.verify(this.ps).executeQuery();

		Assert.assertNotNull(missions);
		Assert.assertEquals(1, missions.size());

		final List<String> comments = missions.get(0).getComments();
		Assert.assertEquals(3, comments.size());
		Assert.assertEquals("Comment 1", comments.get(0));
		Assert.assertEquals("Comment 2", comments.get(1));
		Assert.assertEquals("Comment 3", comments.get(2));

		final List<Position> positions = missions.get(0).getPositions();
		Assert.assertEquals(3, positions.size());

		final Position position1 = positions.get(0);
		Assert.assertEquals(Double.doubleToLongBits(11.11),Double.doubleToLongBits(position1.latitude.degrees));
		Assert.assertEquals(Double.doubleToLongBits(22.22),Double.doubleToLongBits(position1.longitude.degrees));
		Assert.assertEquals(Double.doubleToLongBits(33.33),Double.doubleToLongBits(position1.getElevation()));

		final Position position2 = positions.get(1);
		Assert.assertEquals(Double.doubleToLongBits(112.112),Double.doubleToLongBits(position2.latitude.degrees));
		Assert.assertEquals(Double.doubleToLongBits(222.222),Double.doubleToLongBits(position2.longitude.degrees));
		Assert.assertEquals(Double.doubleToLongBits(332.332),Double.doubleToLongBits(position2.getElevation()));


		final Position position3 = positions.get(2);
		Assert.assertEquals(Double.doubleToLongBits(113.113),Double.doubleToLongBits(position3.latitude.degrees));
		Assert.assertEquals(Double.doubleToLongBits(223.223),Double.doubleToLongBits(position3.longitude.degrees));
		Assert.assertEquals(Double.doubleToLongBits(333.333),Double.doubleToLongBits(position3.getElevation()));

		Mockito.verify(this.missionDAO).closeQuietly(this.connection);
		Mockito.verify(this.missionDAO).closeQuietly(this.ps);
		Mockito.verify(this.missionDAO).closeQuietly(this.rs);

	}


	/**
	 * Test for {@link MissionDAO#add(Mission)}
	 * @throws SQLException
	 */
	@Test
	public void testAdd() throws SQLException {
		final Mission mission = new Mission("user","mission 1");
		Assert.assertEquals(true,this.missionDAO.add(mission));
		Mockito.verify(this.connection).prepareStatement("INSERT INTO missions(title,username,positions,comments,departure,arrival) VALUES (?,?,?,?,?,?);");
		Mockito.verify(this.ps).setString(1,"mission 1");
		Mockito.verify(this.ps).setString(2,"user");
		Mockito.verify(this.ps).setString(3,"");
		Mockito.verify(this.ps).setString(4,"");
		Mockito.verify(this.ps).setDate(5,null);
		Mockito.verify(this.ps).setDate(6,null);
		Mockito.verify(this.ps).execute();
		Mockito.verify(this.missionDAO).closeQuietly(this.connection);
		Mockito.verify(this.missionDAO).closeQuietly(this.ps);
	}

	/**
	 * Test for {@link MissionDAO#get(int)}
	 * @throws SQLException
	 */
	@Test
	public void testGet() throws SQLException {
		final Mission mission = this.missionDAO.get(1);

		Mockito.verify(this.connection).prepareStatement("SELECT * FROM missions WHERE id = 1");
		Mockito.verify(this.ps).executeQuery();
		Assert.assertNotNull(mission);

		Mockito.verify(this.missionDAO).closeQuietly(this.connection);
		Mockito.verify(this.missionDAO).closeQuietly(this.ps);
		Mockito.verify(this.missionDAO).closeQuietly(this.rs);
	}

	/**
	 * Test for {@link MissionDAO#update(Mission)}
	 * @throws SQLException
	 */
	@Test
	public void testUpdate() throws SQLException {
		final Mission mission = new Mission(1,"user","mission 1",new ArrayList<Position>(),new ArrayList<>(),null,null);
		Assert.assertEquals(true,this.missionDAO.update(mission));

		Mockito.verify(this.connection).prepareStatement("UPDATE missions SET title='mission 1', username='user', positions='', comments='', departure=null, arrival=null  WHERE id=1");
		Mockito.verify(this.ps).execute();

		Mockito.verify(this.missionDAO).closeQuietly(this.connection);
		Mockito.verify(this.missionDAO).closeQuietly(this.ps);
	}

	/**
	 * Test for {@link MissionDAO#delete(Mission)}
	 * @throws SQLException
	 */
	@Test
	public void testDelete() throws SQLException {
		final Mission mission = new Mission(1,"user","mission 1",new ArrayList<Position>(),new ArrayList<>(),null,null);
		Assert.assertEquals(true,this.missionDAO.delete(mission));

		Mockito.verify(this.connection).prepareStatement("DELETE from missions WHERE id=1");
		Mockito.verify(this.ps).execute();

		Mockito.verify(this.missionDAO).closeQuietly(this.connection);
		Mockito.verify(this.missionDAO).closeQuietly(this.ps);
	}
}
