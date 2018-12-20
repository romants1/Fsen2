package showSystem;
import java.util.List;

public class Hall {
    private String name;
    private int[] sits;
    private List<Show> shows;

    public Hall(String name, int[] sits){
        this.name = name;
        this.sits = sits;
    }

    public String getName() {
        return name;
    }

    public int[] getSits() {
        return sits;
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
