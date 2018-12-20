package showSystem;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

public class Show {
    private  String name;
    private String description;
    private long lastOrderDate;
    private long showDate;
    private LocalTime showTime;
    private double ticketCost;
    private boolean includesTime;
    private int showID;
    private List<Order> notifyList;
    private List freeSeats;

    public Show(String name, String description, long lastOrderDate, long showDate, double ticketCost, boolean includesTime, int minutesTime, int hourTime ){
        this.name = name;
        this.description = description;
        this.lastOrderDate = lastOrderDate;
        this.showDate = showDate;
        this.ticketCost = ticketCost;
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
