package uni.tcu.onlineskishop.enums;

public enum ProductType {
    JACKET("Jacket"),
    BOOTS("Boots"),
    SKI("Ski"),
    GLOVES("Gloves"),
    HELMET("Helmet"),
    POLES("Poles");

    private final String value;

    ProductType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
