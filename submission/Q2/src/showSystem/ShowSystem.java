package showSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowSystem  {
    private List<City> cities;
    private int showIdGenerator;

    public ShowSystem(){
        cities = new ArrayList<City>();
        showIdGenerator = 1;
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

    public int addShow(String user, String pass, Show show, String city, String hall) {
        City c = findCity(city);
        if(c != null) {
            Hall h = c.findHall(hall);
            if (c.isAdmin(user, pass) && (h != null) && (show.getLastOrderDate() < show.getShowDate()) && checkTime(show)) {
                show.setShowID(showIdGenerator++);
                show.setFreeSeats(Arrays.asList(h.getSits()));
                h.addShow(show);
                return showIdGenerator - 1;
            }
        }
        return 0;
    }

    private boolean checkTime(Show show) {
        return (show.isIncludesTime())? (show.getShowTime() != null) : (show.getShowTime() == null);
    }

    public String orderShow(Order order){
        Show show = findShow(order.getShowId());
        if(order.getUserName() == null || order.getPhoneNum() == "" || order.getSeats() == null || order.getSeats().length == 0)
            return "Order Failed: Missing Info";
        if(show == null)
            return "Order Failed: No Such Show";
        if(System.currentTimeMillis() > show.getLastOrderDate())
            return "Order Failed: Can't order after last order date, the seats are not reserved";
        show.addToNotifyList(order);
        if(order.getUserId() != -1){
            show.reserveMemberSeats(order.getSeats());
        }
        else{
            if (!show.reserveSeats(order.getSeats()))
                return "Order Failed: Only members can reserve member seats.";
        }
        return "Order Succeeded: Seats are reserved, the payment is made through a phone call to 012-3456789 ";
    }

    public void setMemberSeats(int show_id, int from, int to){
        Show sh = findShow(show_id);
        if(sh != null){
            sh.setMemberSeats(from, to);
        }
    }

    private City findCity(String city){
        for (City c: cities) {
            if(c.getName().equals(city))
                return c;
        }
        return null;
    }

    public Show findShow (int id){
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
