package showSystem;
public class Admin {
    private String user;
    private String pass;

    public Admin (String user, String pass){
        this.user = user;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public String getUser() {
        return user;
    }
}
