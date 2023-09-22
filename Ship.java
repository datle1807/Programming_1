public class Ship extends Vehicle {
    public Ship(String id, String name, double carryingCapacity, double fuelCapacity) {
        super(id, name, carryingCapacity, fuelCapacity);
    }

    @Override
    public void loadContainer(Container container) {

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
        return 5.0;
    }
}