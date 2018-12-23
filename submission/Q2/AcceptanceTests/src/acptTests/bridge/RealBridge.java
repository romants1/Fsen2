package acptTests.bridge;
import acptTests.data.OrderInfo;
import acptTests.data.ShowInfo;
import showSystem.*;

import java.util.*;

public class RealBridge implements Bridge {
    private ShowSystem system;
    private int orderIdGenerator;
    private Map<Integer, List<OrderInfo>> showOrdersInfo;

    public RealBridge(ShowSystem system){
        this.system = system;
        orderIdGenerator = 1;
        showOrdersInfo = new HashMap<Integer, List<OrderInfo>>();
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
        int show_id;

        if (showInfo.showTime != null) {
            show_id = system.addShow(user, pass, new Show(showInfo.name, showInfo.description, showInfo.lastOrderDate, showInfo.showDate, showInfo.ticketCost, showInfo.hastime, showInfo.showTime.getMinute(), showInfo.showTime.getHour()), showInfo.city, showInfo.hall);
        }
        else{
            show_id = system.addShow(user, pass, new Show(showInfo.name, showInfo.description, showInfo.lastOrderDate, showInfo.showDate, showInfo.ticketCost, showInfo.hastime, -1, -1 ), showInfo.city, showInfo.hall);
        }
        showOrdersInfo.put(show_id, new ArrayList<OrderInfo>());
        return show_id;
    }

    @Override
    public void reserveMemberChairs(int showID, int from, int to) {
       system.setMemberSeats(showID, from, to);
    }

    @Override
    public int newOrder(OrderInfo order) {
        String s = "";
        if (order.memberId == "")
            s = system.orderShow(new Order(order.name,order.phone, order.chairsIds,order.showId, 0));
        else if (order.memberId != null )
            s = system.orderShow(new Order(order.name,order.phone, order.chairsIds,order.showId,Integer.parseInt(order.memberId)));
        else
            s = system.orderShow(new Order(order.name,order.phone, order.chairsIds,order.showId,-1));
        if(s.contains("Succeeded")) {
            boolean contains = false;
            for (OrderInfo oi : showOrdersInfo.get(order.showId)) {
                if (oi.name == order.name) contains = true;
            }
            if (!contains)
                showOrdersInfo.get(order.showId).add(order);
            return orderIdGenerator++;
        }
        else
            return 0;
    }

    @Override
    public List<OrderInfo> getWaitings(int id) {
        return showOrdersInfo.get(id);
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
