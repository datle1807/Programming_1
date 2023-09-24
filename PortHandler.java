import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PortHandler {
    private List<Port> ports = new ArrayList<>();
    private PortManagerList portManagerList = new PortManagerList();
    private final static File PORTMANAGER_FILE = new File("./src/PORTMANAGER.txt");
    private PortManager currentPortManager;

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
    private String loginInput() {
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
    public void login() throws Exception {

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
    public boolean checkUser(String user) {
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
    private boolean loginVerify(String user) {
        for (PortManager portManager : portManagerList.getPortManagerList()) {
            if (checkUser(user) && user.equals("," + portManager.getUsername() + "," + portManager.getPassword() + ",")) {
                this.currentPortManager = new PortManager(portManager.getID(), portManager.getUsername(), portManager.getPassword(),
                        portManager.getName());
                return true;
            }
        }

        return false;
    }
    // Add port
    public void updateFile(String text, Integer action) throws IOException {

        for (PortManager customer : portManagerList.getPortManagerList()) {
            if (customer.getID().equalsIgnoreCase(currentPortManager.getID())) {

                if (action.equals(1)) {
                    customer.setPassword(text);
                    break;
                } else if (action.equals(2)) {
                    customer.setName(text);
                    break;
            }
        }

        FileManager.updateContent(String.valueOf(PORTMANAGER_FILE), portManagerList.getPortManagerList());

        System.out.println("Update your information successfully!");
    }

    // Get port
    public Port getPort(String name) {
        return ports.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new PortNotFoundException());
    }

    // Update port
    public void updatePort(Port port) {
        int index = ports.indexOf(getPort(port.getName()));
        ports.set(index, port);
    }

    // Delete port
    public void removePort(String name) {
        ports.remove(getPort(name));
    }

    public void menuPort(/* todo */) throws Exception {
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

}
