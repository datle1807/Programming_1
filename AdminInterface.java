import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminInterface {

    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Container> containers = new ArrayList<>();
    private List<Port> ports = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        AdminInterface admin = new AdminInterface();
        admin.loadData();
        admin.run();
    }

    private void loadData() throws IOException {

        //load vehicle data
        loadVehicles();

        //load container data
        loadContainers();

    }

    private void loadVehicles() throws IOException {

        Scanner scanner = new Scanner(new File("vehicle-data.txt"));

        while(scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] parts = line.split(",");

            //parse id
            String id = parts[0];

            //parse name
            String name = parts[1];

            //parse other fields
            String type = parts[2];
            int capacity = Integer.parseInt(parts[3]);

            Vehicle vehicle;

            if(type.equals("Ship")) {
                vehicle = new Ship(id, name, capacity);
            }
            else {
                vehicle = new Truck(id, name, capacity);
            }

            vehicles.add(vehicle);

        }
    }

    private void loadContainers() throws IOException {

        Scanner scanner = new Scanner(new File("container-data.txt"));

        while(scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] parts = line.split(",");

            //parse id
            String id = parts[0];

            //parse weight
            double weight = Double.parseDouble(parts[1]);

            //parse type
            String type = parts[2];

            ContainerType containerType = null;

            if(type.equals("Dry Storage")) {
                containerType = ContainerType.DRY_STORAGE;
            }
            else if(type.equals("Refrigerated")) {
                containerType = ContainerType.REFRIGERATED;
            }
            else if(type.equals("Open top")) {
                containerType = ContainerType.OPEN_TOP;
            }
            else if(type.equals("Open side")) {
                containerType = ContainerType.OPEN_SIDE;
            }
            else if(type.equals("Liquid")) {
                containerType = ContainerType.LIQUID;
            }

            Container container = new Container(
                    id,
                    weight,
                    containerType
            );

            containers.add(container);

        }
    }

    private void run() {
        login();
        displayMenu();

        while(true) {
            int choice = getMenuChoice();
            processChoice(choice);
        }
    }

    private void processChoice(int choice) {
    }

    private int getMenuChoice() {
    }

    private void displayMenu() {
    }

    void login() {
        //login code
        Scanner input = new Scanner(System.in);

        System.out.print("Username: ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        if(!validateLogin(username, password)) {
            System.out.println("Invalid credentials");
            return;
        }

        System.out.println("Login successful!");

    }

    private boolean validateLogin(String username, String password) {

        // Hardcoded credentials for example
        if(username.equals("admin") && password.equals("admin")) {
            return true;
        }

        return false;

    }
}

    private void displayMenu() {
        //menu options
        System.out.println("Admin Menu");

        System.out.println("1. Add Vehicle");
        System.out.println("2. View Vehicles");
        System.out.println("3. Add Container");
        System.out.println("4. View Containers");
        System.out.println("5. Add Port");
        System.out.println("6. View Ports");
        System.out.println("7. Assign Vehicle to Port");
        System.out.println("8. Load/Unload Containers");
        System.out.println("9. Logout");

        System.out.print("Enter choice: ");
    }

    private int getMenuChoice() {
        //input choice
        Scanner input = new Scanner(System.in);

        System.out.print("Enter choice: ");

        try {
            int choice = input.nextInt();
            input.nextLine();

            if(choice < 1 || choice > 9) {
                throw new IllegalArgumentException("Invalid choice");
            }

            return choice;

        } catch(IllegalArgumentException e) {
            System.out.println("Please enter a number between 1-9");
            return getMenuChoice();
        }
    }

    private void processChoice(int choice) {
        switch(choice) {
            case 1:
                addVehicle();
                break;

            case 2:
                viewVehicles();
                break;

            case 3:
                addContainer();
                break;

            case 4:
                viewContainers();
                break;

            case 5:
                addPort();
                break;

            case 6:
                viewPorts();
                break;

            case 7:
                assignVehicleToPort();
                break;

            case 8:
                loadUnloadContainers();
                break;

            case 9:
                logout();
                return;

            default:
                System.out.println("Invalid choice");
        }
    }

    private void addVehicle() {
        //get details
        //create vehicle
        //add to list
        Scanner input = new Scanner(System.in);

        System.out.print("Enter vehicle ID: ");
        String id = input.nextLine();

        System.out.print("Enter vehicle name: ");
        String name = input.nextLine();

        System.out.print("Enter vehicle type (Ship/Truck): ");
        String type = input.nextLine();

        Vehicle vehicle;

        if(type.equalsIgnoreCase("Ship")) {
            //get ship details
            vehicle = new Ship(id, name, //ship details);

        } else if(type.equalsIgnoreCase("Truck")){
            //get truck details
            vehicle = new Truck(id, name, //truck details);

        } else {
            System.out.println("Invalid vehicle type");
            return;
        }

        vehicles.add(vehicle);

        System.out.println("Vehicle added!");
    }

    private void viewVehicles() {
        //display vehicles
        System.out.println("Vehicles:");

        for(Vehicle vehicle : vehicles) {

            System.out.print("ID: " + vehicle.getId());
            System.out.print(" Name: " + vehicle.getName());

            if(vehicle instanceof Ship) {
                Ship ship = (Ship)vehicle;
                System.out.print(" Type: Ship");
                System.out.print(" Capacity: " + ship.getCarryingCapacity());

            } else if(vehicle instanceof Truck) {
                Truck truck = (Truck)vehicle;
                System.out.print(" Type: Truck");
                System.out.print(" Capacity: " + truck.getCarryingCapacity());

            }

            System.out.println();

        }

        System.out.println("Total Vehicles: " + vehicles.size());
    }

    //other methods to add/view
    //containers, ports etc

    //inner classes
}

}

