public class PhysicalProduct extends Product {

    public PhysicalProduct(String sku, String name, double price, int availableCount) {
        super(sku, name, price, availableCount);
    }

    @Override
    public String getProductType() {
        return "PhysicalProduct";
    }

    @Override
    public void decrementStock() {
        if (getAvailableCount() > 0) {
            setAvailableCount(getAvailableCount() - 1);
            System.out.println("Stock decremented for product: " + getName());
        } else {
            System.out.println("Product is out of stock: " + getName());
        }
    }

    public void addStock(int amount) {
        setAvailableCount(getAvailableCount() + amount);
        System.out.println("Added " + amount + " stock for product: " + getName());
    }
}
