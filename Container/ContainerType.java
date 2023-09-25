package Container;

public enum ContainerType {
    DRY_STORAGE,
    OPEN_TOP,
    OPEN_SIDE,
    REFRIGERATED,
    LIQUID;

    public static ContainerType getType(String type) {
        if(type == null) {
            return null;
        }

        for(ContainerType c : ContainerType.values()) {
            if(type.equalsIgnoreCase(c.name())) {
                return c;
            }
        }

        throw new IllegalArgumentException("No matching container type: " + type);
    }

}
