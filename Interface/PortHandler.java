package Interface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import File_Manager.*;
import User.*;
import Port.*;
public class PortHandler {
    static Scanner scanner = new Scanner(System.in);

    private List<Port> ports = new ArrayList<>();
    private static PortManagerList portManagerList = new PortManagerList();
    private final static File PORTMANAGER_FILE = new File("./src/PORTMANAGER.txt");
    private static PortManager currentPortManager;

    public List<Port> getPorts() {
        return ports;
    }

    public PortManagerList getPortManagerList() {
        return portManagerList;
    }

    public PortManager getCurrentPortManager() {
        return currentPortManager;
    }

    public static void create() {
        System.out.println("Enter to continue");
        User.scanner.nextLine();
        System.out.println("------------PORT MANAGER REGISTRATION ------------");
        // Get input from user //

        // Get username
        System.out.println("Enter your username:");
        String username = User.scanner.nextLine();

        // Get password
        System.out.println("Enter your password: ");
        String password = User.scanner.nextLine();
        while (!User.validatePassword(password)) {
            User.passwordConditions();
            System.out.println("Please enter your password again: ");
            password = User.scanner.nextLine();
        }

        // Get name of user
        System.out.println("Enter your name: ");
        String name = User.scanner.nextLine();

        // Generate id for customer
        String ID = "C0000"; /* todo generate ID */

        // Create newCustomer and add to file
        String newPortManager = String.format("%s%s,%s,%s,%s,%s", "",
                ID, username, password, name, "0");

        // Add new customer to customer.txt
        FileManager.writeToFile(String.valueOf(PORTMANAGER_FILE), newPortManager);
    }

    private static String loginInput() {
        // Get username from user
        System.out.println("Please enter your username: ");
        String username = User.scanner.nextLine();

        // Get password from user
        System.out.println("Please enter your password: ");
        String password = User.scanner.nextLine();
        while (!User.validatePassword(password)) {
            User.passwordConditions();
            System.out.println("Please enter your password again: ");
            password = User.scanner.nextLine();
        }

        return "," + username + "," + password + ",";
    }

    public static void login() throws Exception {

        System.out.println("------------PORT LOGIN PAGE ------------");
        /* todo */
        String account = loginInput();

        portManagerList.fromFileToList();
        boolean flag = true;
        while (flag) {
            if (loginVerify(account)) {
                menuPort();
                flag = false;
            } else {
                System.out.println("Entered password or username is wrong please input again!");
                account = loginInput();
            }
        }
    }

    private static void menuPort() throws Exception{
        boolean flag = true;
        byte action;

        while (flag) {
            action = display();
            switch (action) {
                case 0:
                    flag = false;
//                    menu.display(); todo
                    break;
                case 1:
                    viewInfo();
                    break;
                case 2:
                    update();
                    break;

                default:
                    System.out.println("Please enter a number from 1 to 8! ");
            }
        }
    }


    public static boolean checkUser(String user) {
        try {
            Scanner scanner = new Scanner(PORTMANAGER_FILE);

            //now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if (line.contains(user)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            //handle this
        }
        return false;
    }

    private static boolean loginVerify(String user) {
        for (PortManager portManager : portManagerList.getPortManagerList()) {
            if (checkUser(user) && user.equals("," + portManager.getUsername() + "," + portManager.getPassword() + ",")) {
                currentPortManager = new PortManager(portManager.getID(), portManager.getUsername(), portManager.getPassword(),
                        portManager.getName());
                return true;
            }
        }

        return false;
    }

    // Add port
    public static void updateFile(String text, Integer action) throws IOException {

        for (PortManager portManager : portManagerList.getPortManagerList()) {
            if (portManager.getID().equalsIgnoreCase(currentPortManager.getID())) {

                if (action.equals(1)) {
                    portManager.setPassword(text);
                    break;
                } else if (action.equals(2)) {
                    portManager.setName(text);
                    break;
                }
            }

            FileManager.updateContent(String.valueOf(PORTMANAGER_FILE), portManagerList.getPortManagerList());

            System.out.println("Update your information successfully!");
        }
    }

    public static void update() throws IOException {
        int mode;
        boolean isRunning = true;

        /* todo Update Write to Customer.txt File */

        while (isRunning) {
            System.out.println("Choose what information you want to change update: ");
            System.out.println("0. Exit");
            System.out.println("1. Password");
            System.out.println("2. Name");
            System.out.println("3. Email");
            System.out.println("4. Address");
            System.out.println("5. Phone");
            System.out.println("Enter mode pls: ");
            mode = scanner.nextInt();
            scanner.nextLine();
            switch (mode) {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    System.out.println("Enter new password: ");
                    String password = scanner.nextLine();
                    while (!User.validatePassword(password)) {
                        User.passwordConditions();
                        System.out.println("Please enter your password again: ");
                        password = scanner.nextLine();
                    }
                    updateFile(password, 1);
                    break;
                case 2:
                    System.out.println("Enter new name: ");
                    String name = scanner.nextLine();
                    updateFile(name, 2);
                    break;
                default:
                    System.out.println("Enter mode again: ");
                    mode = scanner.nextByte();
            }

        }
    }


    private static void viewInfo() {
        System.out.printf("ID: %s\n", currentPortManager.getID());
        System.out.printf("Username: %s\n", currentPortManager.getUsername());
        System.out.printf("Password: %s\n", currentPortManager.getPassword());
        System.out.printf("Name: %s\n", currentPortManager.getName());
    }

    private static byte display() {
        System.out.println("------------ PORT MANAGER HOMEPAGE ------------");
        System.out.println("0. Logout.");
        System.out.println("1. View all your information.");
        System.out.println("2. Update your information.");
        System.out.println("You can choose action by entering a number: ");
        return scanner.nextByte();
    }
}
