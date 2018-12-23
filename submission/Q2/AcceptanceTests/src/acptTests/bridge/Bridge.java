package acptTests.bridge;

import java.util.List;

import acptTests.data.OrderInfo;
import acptTests.data.ShowInfo;

public interface Bridge {

	void addCity(String city);

	void addHall(String city, String hall, int sits);

	void addAdmin(String city, String user, String pass);

	/**
	 * Adds new show
	 * 
	 * @param user
	 *            username
	 * @param pass
	 *            password
	 * @param showInfo
	 *            contains show information (used to reduce the amount of
	 *            parameters)
	 * @return If succeed returns unique show id (a positive number). Otherwise
	 *         return 0.
	 */
	int addNewShow(String user, String pass, ShowInfo showInfo);

	/**
	 * reserve chairs [from-to] for Pais members only
	 * 
	 * @param showID
	 *            show id (as return from addNewShow)
	 * @param from
	 *            minimum chair id
	 * @param to
	 *            maximum chair id
	 */
	void reserveMemberChairs(int showID, int from, int to);

	/**
	 * @param order
	 *            order information
	 * @return If succeed return an unique reservation id. Otherwise return 0.
	 */
	int newOrder(OrderInfo order);

	List<OrderInfo> getWaitings(int id);
}