package showSystem;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private List<Hall> halls;
    private List<Admin> admins;

    public City(String name){
        this.name = name;
        halls = new ArrayList<Hall>();
        admins = new ArrayList<Admin>();
    }

    public void addHall(String hall, int[] sits){
        halls.add(new Hall(hall, sits));
    }

    public void addAdmin(String user, String pass){
        admins.add(new Admin(user,pass));
    }

    public boolean isAdmin(String user, String pass){
        for (Admin a: admins) {
            if(a.getUser().equals(user) && a.getPass().equals(pass))
                return true;
        }
        return false;
    }

    public Hall findHall(String hall){
        for (Hall h: halls) {
            if(h.getName().equals(hall))
                return h;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public List<Hall> getHalls() {
        return halls;
    }
}
