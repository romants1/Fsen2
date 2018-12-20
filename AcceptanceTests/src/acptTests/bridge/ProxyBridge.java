package acptTests.bridge;

import java.util.LinkedList;
import java.util.List;

import acptTests.data.OrderInfo;
import acptTests.data.ShowInfo;

public class ProxyBridge implements Bridge {
	private Bridge real;

	public ProxyBridge() {
		real = null;
	}

	public void setRealBridge(Bridge implementation) {
		if (real == null)
			real = implementation;
	}

	@Override
	public void addCity(String city) {
		real.addCity(city);
	}

	@Override
	public void addHall(String city, String hall, int sits) {
		real.addHall(city, hall, sits);
	}

	@Override
	public void addAdmin(String city, String user, String pass) {
		real.addAdmin(city, user, pass);
	}

	@Override
	public int addNewShow(String user, String pass, ShowInfo showInfo) {
		return real.addNewShow(user, pass, showInfo);
	}

	@Override
	public void reserveMemberChairs(int showID, int from, int to) {
		real.reserveMemberChairs(showID, from, to);
	}

	@Override
	public int newOrder(OrderInfo order) {
		return real.newOrder(order);
	}

	@Override
	public List<OrderInfo> getWaitings(int id) {
		return real.getWaitings(id);
	}
}