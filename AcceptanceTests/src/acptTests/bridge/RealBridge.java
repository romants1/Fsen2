package acptTests.bridge;
import acptTests.data.OrderInfo;
import acptTests.data.ShowInfo;
import showSystem.*;

import java.util.List;

public class RealBridge implements Bridge {
    private ShowSystem system;

    public RealBridge(ShowSystem system){
        this.system = system;
    }

    @Override
    public void addCity(String city) {
        system.addCity(city);
    }

    @Override
    public void addHall(String city, String hall, int sits) {
        int[] seats = new int[sits];
        for(int i = 0; i < sits; i++)
            seats[i] = i + 1;
        system.addHall(city, hall, seats);
    }

    @Override
    public void addAdmin(String city, String user, String pass) {
        system.addAdmin(city, user, pass);
    }

    @Override
    public int addNewShow(String user, String pass, ShowInfo showInfo) {
        return system.addShow(user, pass, new Show(showInfo.name, showInfo.description, showInfo.lastOrderDate, showInfo.showDate, showInfo.ticketCost, showInfo.hastime, showInfo.showTime.getMinute(), showInfo.showTime.getHour() ), showInfo.city, showInfo.hall);
    }

    @Override
    public void reserveMemberChairs(int showID, int from, int to) {
        system.reserveSeats(system.findShow(showID));
    }

    @Override
    public int newOrder(OrderInfo order) {
        return 0;
    }

    @Override
    public List<OrderInfo> getWaitings(int id) {
        return null;
    }
}
