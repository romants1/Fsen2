package acptTests.bridge;
import acptTests.data.OrderInfo;
import acptTests.data.ShowInfo;
import showSystem.*;

import java.util.ArrayList;
import java.util.List;

public class RealBridge implements Bridge {
    private ShowSystem system;
    private int orderIdGenerator;
    public RealBridge(ShowSystem system){
        this.system = system;
        orderIdGenerator = 1;
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
        return (showInfo.showTime != null)?
                system.addShow(user, pass, new Show(showInfo.name, showInfo.description, showInfo.lastOrderDate, showInfo.showDate, showInfo.ticketCost, showInfo.hastime, showInfo.showTime.getMinute(), showInfo.showTime.getHour() ), showInfo.city, showInfo.hall) :
                system.addShow(user, pass, new Show(showInfo.name, showInfo.description, showInfo.lastOrderDate, showInfo.showDate, showInfo.ticketCost, showInfo.hastime, -1, -1 ), showInfo.city, showInfo.hall);
    }

    @Override
    public void reserveMemberChairs(int showID, int from, int to) {
        int[] chosenSeats = new int[to-from+1];
        int y = from;
        for(int i = 0; i < chosenSeats.length; i++){
            chosenSeats[i] = y;
            y++;
        }
        system.findShow(showID).reserveSeats(chosenSeats);

    }

    @Override
    public int newOrder(OrderInfo order) {
        String s = "";
        if (order.memberId != "" && (order.memberId != null ))
            s = system.orderShow(new Order(order.name,order.phone, order.chairsIds,order.showId,Integer.parseInt(order.memberId)));
        else
            s = system.orderShow(new Order(order.name,order.phone, order.chairsIds,order.showId,-1));
        if(s.contains("Succeeded"))
            return orderIdGenerator++;
        else
            return 0;
    }

    @Override
    public List<OrderInfo> getWaitings(int id) {
        List<Order> notifyList = system.findShow(id).getNotifyList();
        List<OrderInfo> waitings = new ArrayList<>();
        for (Order o: notifyList) {
            waitings.add(convertOrderToOrderInfo(o));
        }
        System.out.println(waitings);
        return waitings;
    }

    private OrderInfo convertOrderToOrderInfo(Order toCovert){
        OrderInfo converted = new OrderInfo();
        converted.name = toCovert.getUserName();
        converted.phone = toCovert.getPhoneNum();
        converted.chairsIds = toCovert.getSeats();
        converted.showId = toCovert.getShowId();
        converted.memberId = (toCovert.getUserId() == -1)? "" : toCovert.getUserId() + "";
        return converted;
    }

}
