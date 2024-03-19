public class ProductClass {
    private String sku;
    private String name;
    private double price;
    private int availableCount;

    // Constructor
    public ProductClass(String sku, String name, double price, int availableCount) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.availableCount = availableCount;
    }

    // Getters and setters
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    // toString method to display product information
    @Override
    public String toString() {
        return "[" + sku + "]: " + name + ", available count: " + availableCount + ", $" + price;
    }
}
