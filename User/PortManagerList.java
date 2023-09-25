package User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import File_Manager.*;

public class PortManagerList {
    static Scanner scanner = new Scanner(System.in);
    protected final static File PORTMANAGER_FILE = new File("./src/PORTMANAGER.txt");
    protected static ArrayList<PortManager> portManagerList = new ArrayList<>();

    public static File getPortmanagerFile() {
        return PORTMANAGER_FILE;
    }

    public ArrayList<PortManager> getPortManagerList() {
        return portManagerList;
    }

    // Create a Customer ArrayList to store the customer objects
    public void fromFileToList() throws FileNotFoundException {
        String ID, username, password, name;
        String line;

        Scanner fileScanner = new Scanner(PORTMANAGER_FILE);

        while (fileScanner.hasNext()) {
            line = fileScanner.nextLine();

            StringTokenizer reader = new StringTokenizer(line, ",");

            ID = reader.nextToken();
            username = reader.nextToken();
            password = reader.nextToken();
            name = reader.nextToken();


            portManagerList.add(new PortManager(ID, username, password, name));
        }

        fileScanner.close();
    }

    // --- Count the numbers of different types of membership (Done) --- //



    public PortManager search(String text) throws FileNotFoundException {
        fromFileToList();
        for (PortManager cus : portManagerList) {
            if (cus.getID().equalsIgnoreCase(text)) {
                return cus;
            }
        }

        return null;
    }

    // --- Delete a customer by ID --- //
    public void delete() throws FileNotFoundException {
        PortManager result = null;
        System.out.println("Enter Port Manager's ID: ");
        String ID = scanner.next();

        for (PortManager por : portManagerList) {
            if (por.getID().equalsIgnoreCase(ID)) {
                result = por;
                this.portManagerList.remove(por);
                break;
            }
        }
        if (result != null) {
            System.out.println("Customer removed successfully!");
        }
        else
            System.out.println("No matching customer found!");
        FileManager.updateContent(String.valueOf(PORTMANAGER_FILE), portManagerList);
    }

    // --- Display all port manager ---
    public void display() throws IOException {

        for (PortManager portManager : portManagerList) {
            portManager.displayInterface();
        }
    }
}

