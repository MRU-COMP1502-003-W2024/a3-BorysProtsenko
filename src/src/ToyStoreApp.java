package src;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToyStoreApp {
    private static final ToyStoreApp.ToyInventoryManager inventoryManager = new ToyStoreApp().new ToyInventoryManager();
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final String TOYS_DATA_FILE = "../res/toysData.txt";


    public static void main(String[] args) {
        displayMenu();
    }

    private static void displayMenu() {
        System.out.println("Welcome to the Toy Store!");
        System.out.println("Choose an option:");
        System.out.println("1. Add a new product");
        System.out.println("2. Remove a product");
        System.out.println("3. Search for products by name");
        System.out.println("4. Purchase a product");
        System.out.println("5. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                addNewProduct();
                break;
            case 2:
                removeProduct();
                break;
            case 3:
                searchByName();
                break;
            case 4:
                purchaseProduct();
                break;
            case 5:
                saveInventoryData(); // Save inventory data before exiting
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        displayMenu(); // Display menu again
    }
    //Opens a file and is ready to add info of product
    private static void addNewProduct() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TOYS_DATA_FILE, true))) {
            System.out.println("Enter product type (VideoGame, Figure, Puzzle, BoardGame):");
            String type = scanner.nextLine();
    
            System.out.println("Enter SKU:");
            String sku = scanner.nextLine();
    
            System.out.println("Enter name:");
            String name = scanner.nextLine();
    
            System.out.println("Enter price:");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character
    
            switch (type) {
                case "VideoGame":
                    System.out.println("Enter team:");
                    String team = scanner.nextLine();
                    VideoGame videoGame = new VideoGame(sku, name, price, team);
                    writer.write(videoGame.getSku() + ";" + videoGame.getName() + ";" + videoGame.getPrice() + ";" + videoGame.getTeam() + "\n");
                    break;
                case "Figure":
                    System.out.println("Enter classification:");
                    String classification = scanner.nextLine();
                    Figure figure = new Figure(sku, name, price, classification);
                    writer.write(figure.getSku() + ";" + figure.getName() + ";" + figure.getPrice() + ";" + figure.getClassification() + "\n");
                    break;
                case "Puzzle":
                    System.out.println("Enter number of pieces:");
                    int numPieces = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    Puzzle puzzle = new Puzzle(sku, name, price, numPieces);
                    writer.write(puzzle.getSku() + ";" + puzzle.getName() + ";" + puzzle.getPrice() + ";" + puzzle.getNumPieces() + "\n");
                    break;
                case "BoardGame":
                    System.out.println("Enter minimum age:");
                    int minAge = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    BoardGame boardGame = new BoardGame(sku, name, price, minAge);
                    writer.write(boardGame.getSku() + ";" + boardGame.getName() + ";" + boardGame.getPrice() + ";" + boardGame.getMinAge() + "\n");
                    break;
                default:
                    System.out.println("Invalid product type.");
                    break;
            }
    
            System.out.println("Product added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to inventory file: " + e.getMessage());
        }
    }

    private static void removeProduct() {
        System.out.println("Enter the name of the product to remove:");
        String productName = scanner.nextLine();
    
        // Remove the product by name
        inventoryManager.removeProduct(productName);
        System.out.println("Product removed successfully.");
    }

    private static void searchByName() {
        System.out.println("Enter keyword to search:");
        // Assuming scanner is properly initialized in code
        String keyword = scanner.nextLine(); // Read the entire line entered by the user
        List<InventoryManager.SearchResult> searchResults = inventoryManager.searchByName(keyword);
        if (!searchResults.isEmpty()) {
            for (InventoryManager.SearchResult result : searchResults) {
                // Check if the name contains the keyword (case-insensitive)
                if (result.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println(result);
                }
            }
        } else {
            System.out.println("No products found with keyword: " + keyword);
        }
    }
    
    //Purchase product and activates the proper method
    private static void purchaseProduct() {
        System.out.println("Enter SKU of the product to purchase:");
        String sku = scanner.nextLine();
        if (inventoryManager.purchaseProduct(sku)) {
            System.out.println("Purchase successful.");
        } else {
            System.out.println("Purchase unsuccessful.");
        }
    }
    //Saves data of file
    private static void saveInventoryData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TOYS_DATA_FILE))) {
            for (Product product : inventoryManager.getProducts()) {
                if (product instanceof VideoGame) {
                    VideoGame videoGame = (VideoGame) product;
                    writer.write(videoGame.getSku() + ";" + videoGame.getName() + ";" + videoGame.getPrice() + ";" + videoGame.getTeam() + "\n");
                } else if (product instanceof Figure) {
                    Figure figure = (Figure) product;
                    writer.write(figure.getSku() + ";" + figure.getName() + ";" + figure.getPrice() + ";" + figure.getClassification() + "\n");
                } else if (product instanceof Puzzle) {
                    Puzzle puzzle = (Puzzle) product;
                    writer.write(puzzle.getSku() + ";" + puzzle.getName() + ";" + puzzle.getPrice() + ";" + puzzle.getNumPieces() + "\n");
                } else if (product instanceof BoardGame) {
                    BoardGame boardGame = (BoardGame) product;
                    writer.write(boardGame.getSku() + ";" + boardGame.getName() + ";" + boardGame.getPrice() + ";" + boardGame.getMinAge() + "\n");
                }
            }
            System.out.println("Inventory data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to inventory file: " + e.getMessage());
        }
    }

    public class ToyInventoryManager {
        private List<Product> products;

        public ToyInventoryManager() {
            this.products = new ArrayList<>();
        }

        public void addProduct(Product product) {
            products.add(product);
        }

        public boolean removeProduct(String productName) {
            for (Product product : products) {
                if (product.getName().equalsIgnoreCase(productName)) {
                    products.remove(product);
                    return true;
                }
            }
            return false;
        }

        public List<InventoryManager.SearchResult> searchByName(String keyword) {
            List<InventoryManager.SearchResult> searchResults = new ArrayList<>();
            for (Product product : products) {
                if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    searchResults.add(new InventoryManager.SearchResult(product.getSku(), product.getName(), product.getPrice(), 0, null, null));
                }
            }
            return searchResults;
        }
        //Purchase product
        public boolean purchaseProduct(String sku) {
            for (Product product : products) {
                if (product.getSku().equals(sku)) {
                    // Display product details including price
                    System.out.println("Product Details:");
                    System.out.println("Name: " + product.getName());
                    System.out.println("Price: " + product.getPrice());
                    System.out.println("Available Count: " + product.getAvailableCount());
                    
                    // Confirm purchase
                    System.out.print("Do you want to purchase this product? (yes/no): ");
                    try (Scanner scanner = new Scanner(System.in)) {
                        String choice = scanner.nextLine().trim().toLowerCase();
                        if (choice.equals("yes")) {
                            // Decrease available count by one
                            int newAvailableCount = product.getAvailableCount() - 1;
                            product.setAvailableCount(Math.max(newAvailableCount, 0));
                            
                            // Save updated inventory data
                            saveInventoryData();
                            
                            System.out.println("Purchase successful!");
                            return true;
                        } else {
                            System.out.println("Purchase cancelled.");
                            return false;
                        }
                    }
                }
            }
            // Product with the given SKU not found
            System.out.println("Product with SKU " + sku + " not found.");
            return false;
        }
        
        
        

        public List<Product> getProducts() {
            return products;
        }
    }
}

