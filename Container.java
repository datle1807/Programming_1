public class Container {
    private String ID;
    private double weight;
    private ContainerType type;

    public Container(String ID, double weight, ContainerType type) {
        this.ID = ID;
        this.weight = weight;
        this.type = type;
    }
    public ContainerType getContainerType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public double getFuelConsumption() {
        double fuelConsumption = 0.0;

        switch (type) {
            case DRY_STORAGE:
                fuelConsumption = 4.6;
                break;
            case OPEN_TOP:
                fuelConsumption = 3.2;
                break;
            case OPEN_SIDE:
                fuelConsumption = 3.2;
                break;
            case REFRIGERATED:
                fuelConsumption = 5.4;
                break;
            case LIQUID:
                fuelConsumption = 5.3;
                break;
        }

        return fuelConsumption;
    }

    public ContainerType getType() {
        return type;
    }
}
