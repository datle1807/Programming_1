package Vehicle;
public class CargoShip extends Ship {

    public CargoShip(String id, String name, double carryingCapacity, double fuelCapacity) {
        super(id, name, carryingCapacity, fuelCapacity);
    }

    @Override
    public void refuel() {

    }

    @Override
    public double getFuelConsumptionRate() {
        return 0;
    }

}