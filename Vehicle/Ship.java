package Vehicle;
import Container.*;
public abstract class Ship extends Vehicle {
    public Ship(String id, String name, double carryingCapacity, double fuelCapacity) {
        super(id, name, carryingCapacity, fuelCapacity);
    }
    @Override
    public void loadContainer(Container container) {
        validateNotNull(container);
        if(totalContainers + 1 > carryingCapacity) {
            System.out.println("Ship is full");
            return;
        }
        incrementTotalContainers();
        updateFuelConsumption(container.getContainerType(), true);
    }
    @Override
    public void unloadContainer(Container container) {
        if(totalContainers == 0) {
            throw new IllegalStateException("Ship is empty");
        }
        decrementTotalContainers();
        updateFuelConsumption(container.getContainerType(), false);
    }

    @Override
    public void refuel() {

    }

    private void validateNotNull(Container container) {
        if(container == null) {
            throw new NullPointerException("Container cannot be null");
        }
    }
    private void validatePortHasLanding() {
        if(!currentPort.hasLandingAbility()) {
            throw new IllegalStateException("Ship cannot land at this port");
        }
    }
    private void updateFuelConsumption(ContainerType type, boolean isLoading) {
        double fuelUsed = getFuelConsumptionRate(type);
        if(isLoading) {
            if(currentFuel - fuelUsed < 0) {
                throw new IllegalStateException("Not enough fuel");
            }
            currentFuel -= fuelUsed;
        } else {
            currentFuel += fuelUsed;
        }
    }
    private double getFuelConsumptionRate(ContainerType type) {
        double rate = switch(type) {
            case DRY_STORAGE -> 2.0;
            case OPEN_TOP -> 1.5;
            case OPEN_SIDE -> 1.5;
            case REFRIGERATED -> 3.0;
            case LIQUID -> 2.5;
            default -> throw new IllegalArgumentException("Ship cannot carry this type");
        };
        return rate;
    }
    @Override
    public void refuel(double amount) {
        // Ship refueling logic e.g. at port
        if(amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if(currentFuel + amount > fuelCapacity) {
            throw new IllegalStateException("Not enough space in tank");
        }
        currentFuel = Math.min(fuelCapacity, currentPort.getCurrentFuel());
    }

    @Override
    public double getFuelConsumptionRate() {
        return 0;
    }
}