package acptTests.data;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ShowInfo {
	public String city;
	public String hall;
	public String name;
	public String description;
	public long lastOrderDate;
	public LocalTime showTime;
	public long showDate;
	public double ticketCost;
	public boolean hastime;
	public List<OrderInfo> userstoinform = new LinkedList<>();

	@Override
	public String toString() {
		return "ShowInfo [city=" + city + ", hall=" + hall + ", name=" + name + ", description=" + description
				+ ", lastOrderDate=" + convertTime(lastOrderDate) + ", showTime=" + showTime + ", showDate="
				+ convertTime(showDate) + ", ticketCost=" + ticketCost + ", hastime=" + hastime + ", userstoinform="
				+ userstoinform + "]";
	}

	public String convertTime(long time) {
		Date date = new Date(time);
		Format format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(date);
	}
}