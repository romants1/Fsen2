package showSystem;

import acptTests.data.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class ShowSystem  {
    private List<City> cities;
    private int showId;

    public ShowSystem(){
        cities = new ArrayList<City>();
        showId = 0;
    }

    public void addCity(String city) {
        cities.add(new City(city));
    }

    public void addHall(String city, String hall, int[] sits) {
        City c = findCity(city);
        if(c != null)
            c.addHall(hall, sits);
    }

    public void addAdmin(String city, String user, String pass) {
        City c = findCity(city);
        if(c != null)
            c.addAdmin(user, pass);
    }

    public boolean addShow(String user, String pass,Show show, String city, String hall) {
        City c = findCity(city);
        Hall h = c.findHall(hall);
        if((c != null) && c.isAdmin(user,pass) && (h != null) && (show.getLastOrderDate() < show.getShowDate()) && checkTime(show)){
            show.setShowID(showId++);
            h.addShow(show);
            return true;
        }
        return false;
    }

    private boolean checkTime(Show show) {
        return (show.isIncludesTime())? (show.showTime != null) : (show.showTime == null);
    }

    public String orderShow(Order order){
        Show show = findShow(order.getShowId());
        show.addToNotifyList(order);
        if(order.getUserId() != -1){
            reserveSeats(order, show);
        }
        return null;
    }

    private void reserveSeats(Order order, Show show) {
        show.
    }


    public void reserveMemberChairs(int showID, int from, int to) {
    }

    public int newOrder(OrderInfo order) {
        return 0;
    }

    public List<OrderInfo> getWaitings(int id) {
        return null;
    }

    private City findCity(String city){
        for (City c: cities) {
            if(c.getName().equals(city))
                return c;
        }
        return null;
    }

    private Show findShow (int id){
        for (City c: cities) {
            for (Hall h: c.getHalls()) {
                Show s = h.findShow(id);
                if(s != null)
                    return s;
            }
        }
        return null;
    }
}
