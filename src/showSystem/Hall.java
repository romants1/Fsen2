package showSystem;
import java.util.ArrayList;
import java.util.List;

public class Hall {
    private String name;
    private int[] seats;
    private List<Show> shows;

    public Hall(String name, int[] sits){
        this.name = name;
        this.seats = sits;
        shows = new ArrayList<Show>();
    }

    public String getName() {
        return name;
    }

    public int[] getSits() {
        return seats;
    }

    public void addShow(Show show){
        shows.add(show);
    }

    public List<Show> getShows() {
        return shows;
    }

    public Show findShow(int id){
        for (Show s: shows) {
            if(s.getShowID() == id)
                return s;
        }
        return null;
    }
}
