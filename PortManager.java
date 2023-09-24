import java.io.File;

public class PortManager extends User {
    private User user;
    private String ID = "C0000";
    public PortManager(User user) {
        this.user = user;
    }

    public PortManager() {
    }


    public PortManager(String ID, String username, String password, String name) {
        super(username, password, name);
        this.setUsername(username);
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
    public void displayInterface() {
        System.out.printf("%20s%25s\n", this.ID, super.getName());
    }

}