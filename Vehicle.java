import java.util.List;

public abstract class Vehicle {
    protected String ID;
    protected String name;
    protected double carryingCapacity;
    protected double fuelCapacity;
    protected double currentFuel;
    protected Port currentPort;
    protected int totalContainers;
    protected double fuelLevel;



    public String getName() {
        return name;
    }

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

    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    protected void incrementTotalContainers() {
        totalContainers++;
    }

    protected void decrementTotalContainers() {
        totalContainers--;
    }
    public abstract void refuel(); // for truck
    public void setCurrentPort(Port port) {
        currentPort = port;
    }
    public abstract void refuel(double amount); // for ship
    public abstract double getFuelConsumptionRate();

    public String getId() {
        return ID;
    }


}