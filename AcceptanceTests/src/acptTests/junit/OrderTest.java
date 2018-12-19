package acptTests.junit;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import acptTests.data.OrderInfo;
import acptTests.data.ShowInfo;

public class OrderTest extends ProjectTest {

	protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	private int futureShowId, pastShowId;

	private OrderInfo goodOrder1, goodOrder2;

	@Before
	public void setUp() {
		super.setUp();
		setUpShows();
		setUpOrders();
	}

	private void setUpShows() {
		ShowInfo show1 = new ShowInfo(), show2 = new ShowInfo();
		show1.city = (String) this.halls[0][HALL_CITY];
		show1.hall = (String) this.halls[0][HALL_NAME];
		show1.description = "show description here";
		show1.name = "an example show";
		show1.ticketCost = 120;
		show1.hastime = false;
		try {
			show1.lastOrderDate = dateFormat.parse("30.05.2020 23:59:59").getTime();
			show1.showDate = dateFormat.parse("20.06.2020 23:59:59").getTime();
		} catch (ParseException e) {
			System.err.println("Wrong time or date: " + e.getMessage());
		}

		this.futureShowId = this.addShow(this.users[0][USER_USER], this.users[0][USER_PASS], show1);
		assertTrue(this.futureShowId > 0);
		this.reserveMemberChairs(futureShowId, 30, 60);

		show2.city = (String) this.halls[0][HALL_CITY];
		show2.hall = (String) this.halls[0][HALL_NAME];
		show2.description = "show description here";
		show2.name = "an example show";
		show2.ticketCost = 120;
		show2.hastime = false;
		try {
			show2.lastOrderDate = dateFormat.parse("01.01.2016 23:59:59").getTime();
			show2.showDate = dateFormat.parse("03.03.2016 23:59:59").getTime();
		} catch (ParseException e) {
			System.err.println("Wrong time or date: " + e.getMessage());
		}
		this.pastShowId = this.addShow(this.users[0][USER_USER], this.users[0][USER_PASS], show2);
		assertTrue(this.pastShowId > 0);
		this.reserveMemberChairs(pastShowId, 1, 30);
	}

	private void setUpOrders() {
		goodOrder1 = new OrderInfo();
		goodOrder1.name = "Ariela";
		goodOrder1.phone = "03-6940177";
		goodOrder1.chairsIds = new int[] { 50, 51, 52, 53 };
		goodOrder1.memberId = "";
		goodOrder1.showId = this.futureShowId;

		goodOrder2 = new OrderInfo();
		goodOrder2.name = "Ariel";
		goodOrder2.phone = "03-6940320";
		goodOrder2.chairsIds = new int[] { 35, 36 };
		goodOrder2.memberId = "11122322";
		goodOrder2.showId = this.futureShowId;
	}

	public void testPlaceOrder() {
		int reservationId1 = this.placeOrder(goodOrder1);
		assertTrue(reservationId1 > 0);
		assertTrue(this.getWaitings(goodOrder1.showId).contains(goodOrder1));
		int reservationId2 = this.placeOrder(goodOrder2);
		assertTrue(reservationId2 > 0);
		assertTrue(this.getWaitings(goodOrder2.showId).contains(goodOrder1));
		assertTrue(this.getWaitings(goodOrder2.showId).contains(goodOrder2));
		assertTrue("Different orders should have different ids !", reservationId1 != reservationId2);
	}

	@Test
	public void testPlaceOrderNotMember() {
		goodOrder2.memberId = null;
		int reservationId = this.placeOrder(goodOrder2);
		assertTrue("Only Pais members can order reserved chairs !", reservationId == 0);
	}

	@Test
	public void testPlacePastOrder() {
		goodOrder1.showId = this.pastShowId;
		goodOrder2.showId = this.pastShowId;
		int reservationId;
		reservationId = this.placeOrder(goodOrder1);
		assertTrue("member can not order tickets after last order date !", reservationId == 0);
		reservationId = this.placeOrder(goodOrder2);
		assertTrue("member can not order tickets after last order date !", reservationId == 0);
	}

	@Test
	public void testPlaceUnknownOrder() {
		goodOrder1.showId = -243;
		do {
			goodOrder2.showId = (int) (Math.random() * 10000) + 100;
		} while (goodOrder2.showId == this.futureShowId || goodOrder2.showId == this.pastShowId);
		int reservationId;
		reservationId = this.placeOrder(goodOrder1);
		assertTrue("member can not order tickets to unknown show !", reservationId == 0);
		reservationId = this.placeOrder(goodOrder2);
		assertTrue("member can not order tickets to unknown show !", reservationId == 0);
	}

	@Test
	public void testPlaceOrderMissingInfo1() {
		goodOrder1.name = null;
		goodOrder2.phone = "";
		int reservationId;
		reservationId = this.placeOrder(goodOrder1);
		assertTrue("member can not order tickets without name !", reservationId == 0);
		reservationId = this.placeOrder(goodOrder2);
		assertTrue("member can not order tickets without phone number !", reservationId == 0);
	}

	@Test
	public void testPlaceOrderMissingInfo2() {
		goodOrder1.chairsIds = null;
		goodOrder2.chairsIds = new int[0];
		int reservationId;
		reservationId = this.placeOrder(goodOrder1);
		assertTrue("member can not order tickets without chairs !", reservationId == 0);
		reservationId = this.placeOrder(goodOrder2);
		assertTrue("member can not order tickets without chairs !", reservationId == 0);
	}

	@Test
	public void testSameUser() {
		goodOrder2.name = goodOrder1.name;
		int reservationId1 = this.placeOrder(goodOrder1);
		assertTrue(reservationId1 > 0);
		assertTrue(this.getWaitings(goodOrder1.showId).contains(goodOrder1));
		int reservationId2 = this.placeOrder(goodOrder2);
		assertTrue(reservationId2 > 0);
		assertTrue(this.getWaitings(goodOrder2.showId).contains(goodOrder1));
		assertFalse(this.getWaitings(goodOrder2.showId).contains(goodOrder2));
	}
}