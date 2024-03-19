import java.util.ArrayList;

public class ToyStoreCLI {

    private static final ArrayList<Product> inventory = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java ToyStoreCLI <options>");
            return;
        }

        switch (args[0]) {
            case "-s":
                searchInventory(args);
                break;
            case "-p":
                purchaseProduct(args);
                break;
            case "-a":
                addStock(args);
                break;
            case "-n":
                addNewProduct(args);
                break;
            case "-r":
                removeProduct(args);
                break;
            default:
                System.out.println("Invalid option: " + args[0]);
                break;
        }
    }

    private static void searchInventory(String[] args) {
        if (args.length < 2) {
            System.out.println("Missing keyword for search");
            return;
        }
        String keyword = args[1];
        boolean found = false;
        for (Product product : inventory) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(product.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products found with keyword: " + keyword);
        }
    }

    private static void purchaseProduct(String[] args) {
        if (args.length < 2) {
            System.out.println("Missing SKU for purchase");
            return;
        }
        String sku = args[1];
        for (Product product : inventory) {
            if (product.getSku().equals(sku)) {
                product.decrementStock();
                System.out.println("Purchase acknowledged for product with SKU: " + sku);
                return;
            }
        }
        System.out.println("Product with SKU " + sku + " not found");
    }

    private static void addStock(String[] args) {
        if (args.length < 3) {
            System.out.println("Missing SKU and/or amount for adding stock");
            return;
        }
        String sku = args[1];
        int amount = Integer.parseInt(args[2]);
        for (Product product : inventory) {
            if (product.getSku().equals(sku) && product instanceof PhysicalProduct) {
                ((PhysicalProduct) product).addStock(amount);
                System.out.println("Added " + amount + " stock for product with SKU: " + sku);
                return;
            }
        }
        System.out.println("Product with SKU " + sku + " not found or is not a physical product");
    }

    private static void addNewProduct(String[] args) {
        if (args.length < 5) {
            System.out.println("Missing SKU and/or attributes for adding new product");
            return;
        }
        String sku = args[1];
        String name = args[2];
        double price = Double.parseDouble(args[3]);
        String attribute = args[4];
        // Depending on attribute, create appropriate product subclass
        Product newProduct;
        switch (attribute) {
            case "VideoGame":
                newProduct = new VideoGame(sku, name, price, attribute, 0);
                break;
            case "Figure":
                newProduct = new Figure(sku, name, price, 0, attribute);
                break;
            case "Puzzle":
                newProduct = new Puzzle(sku, name, price, 0, 0);
                break;
            case "BoardGame":
                newProduct = new BoardGame(sku, name, price, 0, 0);
                break;
            default:
                System.out.println("Invalid attribute: " + attribute);
                return;
        }
        inventory.add(newProduct);
        System.out.println("Added new product: " + newProduct.toString());
    }

    private static void removeProduct(String[] args) {
        if (args.length < 2) {
            System.out.println("Missing SKU for removing product");
            return;
        }
        String sku = args[1];
        for (Product product : inventory) {
            if (product.getSku().equals(sku)) {
                inventory.remove(product);
                System.out.println("Removed product with SKU: " + sku);
                return;
            }
        }
        System.out.println("Product with SKU " + sku + " not found");
    }
}
