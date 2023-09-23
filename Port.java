public class Port {
    private String name;
    private double latitude;
    private double longitude;
    private boolean hasLanding;
    private Port currentPort;
    private double currentFuel;

    public Port(String name, double latitude, double longitude, boolean hasLanding, Port currentPort, double currentFuel) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hasLanding = hasLanding;
        this.currentPort = currentPort;
        this.currentFuel = currentFuel;
    }

    public boolean isHasLanding() {
        return hasLanding;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public String getName() {
        return name;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public boolean hasLandingAbility() {
        return hasLanding;
    }
    public boolean canMoveToPort(Port port) {
        return port.hasLandingAbility() &&
                calculateDistance(this.currentPort, port) <= this.currentFuel;
    }
    public Port(double initialFuelAmount) {
        this.currentFuel = initialFuelAmount;
    }

    public void moveToPort(Port port){

        if(canMoveToPort(port)){

            this.currentPort = port;
            double distance = calculateDistance(this.currentPort, port);
            this.currentFuel -= distance;

        } else {

            System.out.println("Cannot move to port");

        }

    }

    protected double calculateDistance(Port startingPort, Port endingport) {
        double lat1 = startingPort.getLatitude();
        double lat2 = endingport.getLatitude();
        double lon1 = startingPort.getLongitude();
        double lon2 = endingport.getLongitude();

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
    public void refuelShip(Ship ship, double amount) {

        // Validation
        if(amount > currentFuel) {
            throw new IllegalArgumentException("Not enough fuel");
        }

        // Refuel ship
        ship.refuel(amount);

        // Deduct fuel from port
        currentFuel -= amount;

    }
    @Override
    public String toString() {
        return "Port [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }


}