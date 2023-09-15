public abstract class Vehicle {
    protected String ID;
    protected String name;
    protected double carryingCapacity;
    protected double fuelCapacity;
    protected double currentFuel;
    protected Port currentPort;
    protected int totalContainers;

    public Vehicle(String ID, String name, double carryingCapacity, double fuelCapacity) {
        this.ID = ID;
        this.name = name;
        this.carryingCapacity = carryingCapacity;
        this.fuelCapacity = fuelCapacity;
        this.currentFuel = fuelCapacity;
        this.currentPort = null;
        this.totalContainers = 0;
    }

    public abstract void loadContainer(Container container);

    public abstract void unloadContainer(Container container);

    public boolean canMoveToPort(Port port) {
        return port.hasLandingAbility() && calculateDistance(currentPort, port) <= currentFuel;
    }

    public void moveToPort(Port port) {
        if (canMoveToPort(port)) {
            currentPort = port;
            double distance = calculateDistance(currentPort, port);
            currentFuel -= distance;
        } else {
            System.out.println("Vehicle cannot move to the specified port.");
        }
    }

    public void refuel() {
        currentFuel = fuelCapacity;
    }

    protected void incrementTotalContainers() {
        totalContainers++;
    }

    protected void decrementTotalContainers() {
        totalContainers--;
    }

    private double calculateDistance(Port port1, Port port2) {
        // Implement the distance calculation logic here
        return 0.0;
    }
}