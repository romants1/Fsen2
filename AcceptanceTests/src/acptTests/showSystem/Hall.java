package acptTests.showSystem;

public class Hall {
    private String name;
    private int sits;

    public Hall(String name, int sits){
        this.name = name;
        this.sits = sits;
    }

    public String getName() {
        return name;
    }

    public int getSits() {
        return sits;
    }
}
