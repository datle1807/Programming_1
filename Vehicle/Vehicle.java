package Vehicle;

import Container.*;
import Port.*;

public abstract class Vehicle {
    protected String ID;
    protected String name;
    protected double carryingCapacity;
    protected double fuelCapacity;
    protected double currentFuel;
    protected Port currentPort;
    protected int totalContainers;
    protected double fuelLevel;

    public String getId() {
        return ID;
    }
    public String getName() {
        return name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public int getTotalContainers() {
        return totalContainers;
    }

    public void setTotalContainers(int totalContainers) {
        this.totalContainers = totalContainers;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
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





}