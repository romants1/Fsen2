package showSystem;
import acptTests.data.OrderInfo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Show {
    private  String name;
    private String description;
    private long lastOrderDate;
    private long showDate;
    public LocalTime showTime;
    private double ticketCost;
    private boolean includesTime;
    private int showID;
    private List<Order> notifyList = new ArrayList<>();

    public Show(String name, String description, long lastOrderDate, long showDate, double ticketCost, boolean includesTime, int minutesTime, int hourTime ){
        this.name = name;
        this.description = description;
        this.lastOrderDate = lastOrderDate;
        this.showDate = showDate;
        this.ticketCost = ticketCost;
        this.includesTime = includesTime;
        if(minutesTime == -1 || hourTime == -1)
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
        notifyList.add(order);
    }


}
