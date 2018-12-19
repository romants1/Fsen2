package acptTests.junit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import acptTests.data.ShowInfo;

public class AddShowTest extends ProjectTest {

	private ShowInfo goodShow1;
	private ShowInfo goodShow2;

	protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	@Before
	public void setUp() {
		super.setUp();
		setUpGoodShows();
	}

	private void setUpGoodShows() {
		this.goodShow1 = new ShowInfo();
		this.goodShow1.city = (String) this.halls[0][HALL_CITY];
		this.goodShow1.hall = (String) this.halls[0][HALL_NAME];
		this.goodShow1.description = "show description here";
		this.goodShow1.name = "an example show";
		this.goodShow1.ticketCost = 120;
		this.goodShow1.hastime = false;
		try {
			this.goodShow1.lastOrderDate = dateFormat.parse("20.2.2017 23:59:59").getTime();
			this.goodShow1.showDate = dateFormat.parse("20.3.2017 00:00:00").getTime();
		} catch (ParseException e) {
			System.err.println("Wrong time or date: " + e.getMessage());
		}

		this.goodShow2 = new ShowInfo();
		this.goodShow2.city = (String) this.halls[1][HALL_CITY];
		this.goodShow2.hall = (String) this.halls[1][HALL_NAME];
		this.goodShow2.description = "another description here";
		this.goodShow2.name = "another example";
		this.goodShow2.ticketCost = 30;
		this.goodShow1.hastime = false;
		try {
			this.goodShow2.lastOrderDate = dateFormat.parse("25.2.2017 23:59:59").getTime();
			this.goodShow2.showDate = dateFormat.parse("30.03.2017 00:00:00").getTime();
		} catch (ParseException e) {
			System.err.println("Wrong time or date: " + e.getMessage());
		}
	}

	@After
	public void tearDown() {
		this.goodShow1 = null;
		this.goodShow2 = null;
	}

	@Test
	public void testAddShow() {
		int showId1 = this.addShow(this.users[0][USER_USER], this.users[0][USER_PASS], this.goodShow1);
		assertTrue(showId1 > 0);
		int showId2 = this.addShow(this.users[1][USER_USER], this.users[1][USER_PASS], this.goodShow2);
		assertTrue(showId2 > 0);
		assertTrue("Different shows must have different ids !", showId1 != showId2);
	}

	@Test
	public void testAddShowWrongUserLoc() {
		int showId;
		showId = this.addShow(this.users[1][USER_USER], this.users[1][USER_PASS], this.goodShow1);
		assertEquals(this.users[1][USER_CITY] + " admin add show to " + this.goodShow1.city, 0, showId);
		showId = this.addShow(this.users[0][USER_USER], this.users[0][USER_PASS], this.goodShow2);
		assertEquals(this.users[0][USER_CITY] + " admin add show to " + this.goodShow2.city, 0, showId);
	}

	@Test
	public void testAddShowWrongUserOrPass() {
		int showId;
		showId = this.addShow(this.users[0][USER_USER], this.users[0][USER_PASS] + "xxx", this.goodShow1);
		assertEquals(0, showId);
		showId = this.addShow(this.users[1][USER_USER], this.users[0][USER_PASS], this.goodShow2);
		assertEquals(0, showId);
		showId = this.addShow("noExists", this.users[1][USER_PASS], this.goodShow2);
		assertEquals(0, showId);
		showId = this.addShow(null, this.users[0][USER_PASS], this.goodShow1);
		assertEquals(0, showId);
		showId = this.addShow(this.users[1][USER_USER], null, this.goodShow2);
		assertEquals(0, showId);
	}

	@Test
	public void testAddShowWrongDates() {
		switchShowDates(goodShow1);
		int showId1 = this.addShow(this.users[0][USER_USER], this.users[0][USER_PASS], this.goodShow1);
		assertEquals(0, showId1);
		switchShowDates(goodShow2);
		int showId2 = this.addShow(this.users[1][USER_USER], this.users[1][USER_PASS], this.goodShow2);
		assertEquals(0, showId2);
	}

	private void switchShowDates(ShowInfo show) {
		long tmp = show.lastOrderDate;
		show.lastOrderDate = show.showDate;
		show.showDate = tmp;
	}

	@Test
	public void testAddShowMissingLoc() {
		goodShow1.city = null;
		int showId1 = this.addShow(this.users[0][USER_USER], this.users[0][USER_PASS], this.goodShow1);
		assertEquals(0, showId1);
		goodShow2.hall = null;
		int showId2 = this.addShow(this.users[1][USER_USER], this.users[1][USER_PASS], this.goodShow2);
		assertEquals(0, showId2);
	}

	@Test
	public void testAddShowWithwrongmissingdate() {
		int showId;
		this.goodShow1.hastime = true;
		showId = this.addShow(this.users[1][USER_USER], this.users[1][USER_PASS], this.goodShow1);
		assertEquals(0, showId);
		this.goodShow2.showTime = LocalTime.parse("20:00");
		showId = this.addShow(this.users[0][USER_USER], this.users[0][USER_PASS], this.goodShow2);
		assertEquals(this.users[0][USER_CITY] + " admin add show to " + this.goodShow2.city, 0, showId);
	}
}