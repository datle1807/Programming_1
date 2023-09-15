public class Truck extends Vehicle {
    public Truck(String ID, String name, double carryingCapacity, double fuelCapacity) {
        super(ID, name, carryingCapacity, fuelCapacity);
    }

    @Override
    public void loadContainer(Container container) {
        if (totalContainers + 1 <= carryingCapacity) {
            incrementTotalContainers();
            updateFuelConsumption(container.getContainerType(), true);
        } else {
            System.out.println("Truck cannot carry more containers.");
        }
    }




    @Override
    public void unloadContainer(Container container) {
        if (totalContainers > 0) {
            decrementTotalContainers();
            updateFuelConsumption(container.getContainerType(), false);
        } else {
            System.out.println("Truck does not have any containers to unload.");
        }
    }
}
