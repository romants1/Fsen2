package showSystem;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

public class Show {
    private String name;
    private String show_info;
    private long lastOrderDate;
    private long showDate;
    private LocalTime showTime;
    private double price;
    private boolean includesTime;
    private int showID;
    private List<Order> notifyList;
    private List freeSeats;

    public Show(String name, String show_info, long lastOrderDate, long showDate, double price, boolean includesTime, int minutesTime, int hourTime ){
        this.name = name;
        this.show_info = show_info;
        this.lastOrderDate = lastOrderDate;
        this.showDate = showDate;
        this.price = price;
        this.includesTime = includesTime;
        this.notifyList = new ArrayList<>();
        if(minutesTime != -1 && hourTime != -1)
            this.showTime = LocalTime.of(hourTime, minutesTime);
        else
            this.showTime = null;
    }

    public String getName() {
        return name;
    }

    public boolean isIncludesTime() {
        return includesTime;
    }

    public long getLastOrderDate() {
        return lastOrderDate;
    }

    public long getShowDate() {
        return showDate;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public void addToNotifyList(Order order){
        if(!notifyList.contains(order))
            notifyList.add(order);
    }

    public void setFreeSeats(List freeSeats) {
        this.freeSeats = freeSeats;
    }

    public List getFreeSeats() {
        return freeSeats;
    }

    public LocalTime getShowTime() {
        return showTime;
    }
}
