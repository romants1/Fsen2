package showSystem;
public class Order {
    private String userName;
    private String phoneNum;
    private int[] seats;
    private int showId ;
    private int userId;

    public Order(String userName, String phoneNum, int[] seats, int showId, int userId){
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.seats = seats;
        this.showId = showId;
        this.userId = userId;
    }

    public int getShowId() {
        return showId;
    }

    public int getUserId() {
        return userId;
    }

    public int[] getSeats() {
        return seats;
    }
}
