import java.util.ArrayList;
import java.util.List;


class VehicleManager {

    private List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle getVehicle(String id) throws VehicleNotFoundException {

        return vehicles.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with id " + id + " not found"));
    }

    public void updateVehicle(String id, Vehicle newVehicle) throws VehicleNotFoundException {
        Vehicle vehicleToUpdate = getVehicle(id);
        vehicleToUpdate.setName(newVehicle.getName());
        //update other properties
    }

    public void removeVehicle(String id) {

        vehicles.removeIf(v -> v.getId().equals(id));

    }


}

