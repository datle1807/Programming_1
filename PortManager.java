public class PortManager extends User {
    private User user;

    public PortManager(User user) {
        this.user = user;
    }

    public PortManager(String username, String password, String name,  User user) {
        super(username, password, name);
        this.user = user;
    }

    public static void login() {

    }

}