package acptTests.showSystem;

import acptTests.bridge.*;
import acptTests.data.OrderInfo;
import acptTests.data.ShowInfo;

import java.util.ArrayList;
import java.util.List;

public class ShowSystem implements Bridge {
    private List<City> cities;

    public ShowSystem(){
        cities = new ArrayList<City>();
    }

    @Override
    public void addCity(String city) {
        cities.add(new City(city));
    }

    @Override
    public void addHall(String city, String hall, int sits) {
        City c = findCity(city);
        if(c != null)
            c.addHall(hall, sits);
    }

    @Override
    public void addAdmin(String city, String user, String pass) {
        City c = findCity(city);
        if(c != null)
            c.addAdmin(user, pass);
    }

    @Override
    public int addNewShow(String user, String pass, ShowInfo showInfo) {
        City c = findCity(showInfo.city);
        if((c != null) && c.isAdmin(user,pass) && (c.findHall(showInfo.hall) != null) /* cont*/){

        }
        return 0;
    }

    @Override
    public void reserveMemberChairs(int showID, int from, int to) {

    }

    @Override
    public int newOrder(OrderInfo order) {
        return 0;
    }

    @Override
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
}
