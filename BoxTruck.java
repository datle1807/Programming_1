public class BoxTruck extends Truck {

    public BoxTruck(String id, String name, double carryingCapacity, double fuelCapacity) {
        super(id, name, carryingCapacity, fuelCapacity);
    }

    @Override
    public void refuel(double amount) {

    }

    @Override
    public double getFuelConsumptionRate() {
        return 0;
    }

}