package User;
public class Admin extends User {
    private User user;

    public Admin(User user) {
        this.user = user;
    }

    public Admin(String username, String password, String name,  User user) {
        super(username, password, name);
        this.user = user;
    }

    public static void login() {

    }

}