//import User.PortManager;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenu {
    static Scanner scanner = new Scanner(System.in);
    public static boolean active = true;
    // Getter and Setter

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        MainMenu.active = active;
    }
    private AdminInterface adminInterface;

    // Constructor
    public MainMenu(boolean active) throws FileNotFoundException {
        MainMenu.active = active;
        adminInterface = new AdminInterface();
    }

    // Display main menu for system
    public void display() throws Exception {
        int action;
        PortManager portManager = new PortManager();
        PortHandler portHandler = new PortHandler();


        while (this.isActive()) {
            System.out.println("#==========================#");
            System.out.println("Do you want to log in as admin or customer ?");
            System.out.println("0. Close Program");
            System.out.println("1. Log in as Port Manager");
            System.out.println("2. Log in as Admin");
            System.out.println("3. Create a port manager account");
            System.out.println("Please choose action by enter a number from 0 to 3: ");
            action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    this.setActive(false);
                    System.out.println("Thank you! Bye Bye!");
                    break;
                case 1:
                    /* Customer Login */
                    PortHandler.login();
                    break;
                case 2:
                    /* Admin Login*/
                    this.adminInterface.login();
                    break;
                case 3:
                    PortHandler.create();
                    System.out.println("Your account has been successfully created.");
                    System.out.println("Please run program again to login!");
                    this.setActive(false);
                    break;
                default:
                    System.out.println("Wrong number please enter again!");
                    break;
            }
        }
    }
}

