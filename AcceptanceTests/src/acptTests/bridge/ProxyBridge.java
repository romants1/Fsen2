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
		// TODO
	}

	@Override
	public void addHall(String city, String hall, int sits) {
		// TODO
	}

	@Override
	public void addAdmin(String city, String user, String pass) {
		// TODO
	}

	@Override
	public int addNewShow(String user, String pass, ShowInfo showInfo) {
		// TODO
		return 1;
	}

	@Override
	public void reserveMemberChairs(int showID, int from, int to) {
		// TODO
	}

	@Override
	public int newOrder(OrderInfo order) {
		// TODO
		return 1;
	}

	@Override
	public List<OrderInfo> getWaitings(int id) {
		// TODO
		return new LinkedList<>();
	}
}