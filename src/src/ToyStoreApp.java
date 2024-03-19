import java.util.Scanner;

public class ToyStoreApp {
    private static final InventoryManager inventoryManager = new InventoryManager();
    private static final Scanner scanner = new Scanner(System.in);

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
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        displayMenu(); // Display menu again
    }

    private static void addNewProduct() {
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
                inventoryManager.addProduct(new VideoGame(sku, name, price, team, 0));
                break;
            case "Figure":
                System.out.println("Enter classification:");
                String classification = scanner.nextLine();
                inventoryManager.addProduct(new Figure(sku, name, price, 0, classification));
                break;
            case "Puzzle":
                System.out.println("Enter number of pieces:");
                int numPieces = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                inventoryManager.addProduct(new Puzzle(sku, name, price, 0, numPieces));
                break;
            case "BoardGame":
                System.out.println("Enter minimum age:");
                int minAge = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                inventoryManager.addProduct(new BoardGame(sku, name, price, 0, minAge));
                break;
            default:
                System.out.println("Invalid product type.");
                break;
        }

        System.out.println("Product added successfully.");
    }

    private static void removeProduct() {
        System.out.println("Enter SKU of the product to remove:");
        String sku = scanner.nextLine();
        inventoryManager.removeProduct(sku);
        System.out.println("Product removed successfully.");
    }

    private static void searchByName() {
        System.out.println("Enter keyword to search:");
        String keyword = scanner.nextLine();
        inventoryManager.searchByName(keyword).forEach(System.out::println);
    }

    private static void purchaseProduct() {
        System.out.println("Enter SKU of the product to purchase:");
        String sku = scanner.nextLine();
        if (inventoryManager.purchaseProduct(sku)) {
            System.out.println("Purchase successful.");
        } else {
            System.out.println("Purchase unsuccessful.");
        }
    }
}
