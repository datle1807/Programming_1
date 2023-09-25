package Vehicle;
import Container.*;
public abstract class Truck extends Vehicle {
    private Container currentContainer;
    public Truck(String ID, String name, double carryingCapacity, double fuelCapacity) {
        super(ID, name, carryingCapacity, fuelCapacity);
    }

    @Override
    public void loadContainer(Container container) {
        validateContainerNotNull(container);
        if(!canCarryContainerType(container.getType())) {
            System.out.println("Cannot carry container type");
            return;
        }
        if (totalContainers + 1 <= carryingCapacity) {
            incrementTotalContainers();
            updateFuelConsumption(container.getContainerType(), true);
        } else {
            System.out.println("Truck cannot carry more containers.");
        }
        currentContainer = container;

        updateFuelConsumption(container.getType(), true);
    }
    @Override
    public void unloadContainer(Container container) {
        if (totalContainers > 0) {
            decrementTotalContainers();
            updateFuelConsumption(container.getContainerType(), false);
        } else {
            System.out.println("Truck does not have any containers to unload.");
        }
        currentContainer = null;

        updateFuelConsumption(container.getType(), false);

    }

    private void updateFuelConsumption(ContainerType type, boolean isLoading) {
        double weight = currentContainer.getWeight();
        double fuelConsumptionRate = getFuelConsumptionRate(type);
        double fuelUsed = weight * fuelConsumptionRate;
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

        double rate = switch (type) {
            case DRY_STORAGE, OPEN_TOP, OPEN_SIDE -> 4.0;
            default -> throw new IllegalArgumentException("Truck cannot carry this type");
        };

        return rate;

    }
    private double getContainerWeight() {
        return currentContainer.getWeight();
    }

    private boolean canCarryContainerType(ContainerType type) {
        return type == ContainerType.DRY_STORAGE ||
                type == ContainerType.OPEN_TOP ||
                type == ContainerType.OPEN_SIDE;
    }

    private void validateContainerNotNull(Container container) {
        if(container == null) {
            throw new NullPointerException("Container cannot be null");
        }
    }
    @Override
    public void refuel() {
        // Validation
        if(currentFuel >= fuelCapacity) {
            throw new IllegalStateException("Tank already full");
        }

        // Truck refueling logic
        currentFuel = fuelCapacity;
    }

    @Override
    public void refuel(double amount) {

    }

    @Override
    public double getFuelConsumptionRate() {
        return 0;
    }
}
