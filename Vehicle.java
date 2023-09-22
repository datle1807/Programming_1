public abstract class Vehicle {
    protected String ID;
    protected String name;
    protected double carryingCapacity;
    protected double fuelCapacity;
    protected double currentFuel;
    protected Port currentPort;
    protected int totalContainers;

    public String getID() {
        return ID;
    }

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
        double lat1 = port1.getLatitude();
        double lat2 = port2.getLatitude();
        double lon1 = port1.getLongitude();
        double lon2 = port2.getLongitude();

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        dist = dist * 1.609344; // Convert to kilometers

        return dist;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public Port getCurrentPort() {
        return currentPort;
    }
}