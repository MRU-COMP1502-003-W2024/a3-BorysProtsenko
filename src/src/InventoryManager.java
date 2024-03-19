import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Product> productList;

    // Constructor
    public InventoryManager() {
        this.productList = new ArrayList<>();
    }

    // Method to add a new product to the inventory
    public void addProduct(Product product) {
        productList.add(product);
    }

    // Method to remove a product from the inventory
    public void removeProduct(String sku) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getSku().equals(sku)) {
                productList.remove(i);
                return;
            }
        }
        System.out.println("Product with SKU " + sku + " not found.");
    }

    // Method to search for products by name
    public List<Product> searchByName(String keyword) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : productList) {
            System.out.println("Checking product: " + product.getName()); // Debug print statement
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    // Method to purchase a product
    public boolean purchaseProduct(String sku) {
        for (Product product : productList) {
            if (product.getSku().equals(sku)) {
                if (product.getAvailableCount() > 0) {
                    product.setAvailableCount(product.getAvailableCount() - 1);
                    System.out.println("Product purchased: " + product.getName());
                    return true;
                } else {
                    System.out.println("Product with SKU " + sku + " is out of stock.");
                    return false;
                }
            }
        }
        System.out.println("Product with SKU " + sku + " not found.");
        return false;
    }
}
