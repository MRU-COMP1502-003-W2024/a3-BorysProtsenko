import java.util.concurrent.atomic.AtomicInteger;

public abstract class Product {
    private String sku;
    private String name;
    private double price;
    private int availableCount;

    // Constructor
    public Product(String sku, String name, double price, int availableCount) {
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

    // Abstract method to be implemented by subclasses
    public abstract String getProductType();

    // Abstract method to be implemented by subclasses
    public abstract void decrementStock();

    // Static method to get total available count across all products
    public static int getTotalAvailableCount(Product[] products) {
        AtomicInteger total = new AtomicInteger();
        for (Product product : products) {
            total.addAndGet(product.getAvailableCount());
        }
        return total.get();
    }
}
