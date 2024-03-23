package src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class InventoryManager {

    private List<Product> products;
    
    
    private static final String TOYS_DATA_FILE = "../res/toysData.txt";

    
    
    // Getters and setters for the products list
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product searchProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
    //Removes product
    public boolean removeProduct(String productName) {
    Product productToRemove = null;
    for (Product product : products) {
        if (product.getName().equalsIgnoreCase(productName)) {
            productToRemove = product;
            break;
        }
    }
    if (productToRemove != null) {
        if (productToRemove.getAvailableCount() == 0) {
            // If product has no available count, remove it directly
            products.remove(productToRemove);
            saveInventoryData();
            return true;
        } else {
            // If product has available count, ask user if they want to remove completely or reduce quantity
            System.out.println("Product " + productName + " has available count. Do you want to:");
            System.out.println("1. Remove completely");
            System.out.println("2. Reduce available count");
            System.out.print("Enter option (1 or 2): ");
            try (Scanner scanner = new Scanner(System.in)) {
                int option = scanner.nextInt();
                if (option == 1) {
                    products.remove(productToRemove);
                    saveInventoryData();
                    return true;
                } else if (option == 2) {
                    // Ask user for quantity to remove
                    System.out.print("Enter quantity to reduce: ");
                    int quantityToRemove = scanner.nextInt();
                    int newAvailableCount = productToRemove.getAvailableCount() - quantityToRemove;
                    productToRemove.setAvailableCount(Math.max(newAvailableCount, 0));
                    saveInventoryData();
                    return true;
                } else {
                    System.out.println("Invalid option. No action taken.");
                    return false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                return false;
            }
        }
    } else {
        System.out.println("Product " + productName + " not found.");
        return false;
    }
}


   

    private void saveInventoryData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TOYS_DATA_FILE))) {
            for (Product product : products) {
                String data = getProductData(product);
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to inventory file: " + e.getMessage());
        }
    }

    private String getProductData(Product product) {
        return product.getSku() + ";" + product.getName() + ";" + product.getPrice() + ";" + getProductSpecificData(product);
    }

    protected abstract String getProductSpecificData(Product product);

    public List<SearchResult> searchByName(String keyword) {
        List<SearchResult> searchResults = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TOYS_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //Splits the data and assign each data to its variable
                String[] parts = line.split(";");
                String sku = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int availableCount = Integer.parseInt(parts[3]);
                String additionalInfo = null;
    
                // Check if the name contains the keyword (case-insensitive)
                if (name.toLowerCase().contains(keyword.toLowerCase())) {
                    char productType = sku.charAt(0);
                    switch (productType) {
                        case '0':
                        case '1':
                            String figureClassification = parts[4];
                            additionalInfo = "Classification: " + figureClassification + ", Available Count: " + availableCount;
                            searchResults.add(createSearchResult(sku, name, price, availableCount, null, additionalInfo));
                            break;
                        case '2':
                        case '3':
                            String team = parts[3];
                            searchResults.add(createSearchResult(sku, name, price, 0, team, null));
                            break;
                        case '4':
                        case '5':
                        case '6':
                            int puzzleNumPieces = Integer.parseInt(parts[4]);
                            additionalInfo = "Number of Pieces: " + puzzleNumPieces + ", Available Count: " + availableCount;
                            searchResults.add(createSearchResult(sku, name, price, availableCount, null, additionalInfo));
                            break;
                        case '7':
                        case '8':
                        case '9':
                            int boardGameMinAge = Integer.parseInt(parts[4]);
                            additionalInfo = "Minimum Age: " + boardGameMinAge + ", Available Count: " + availableCount;
                            searchResults.add(createSearchResult(sku, name, price, availableCount, null, additionalInfo));
                            break;
                        default:
                            continue;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return searchResults;
    }
    
    
    
    //Gets all the data when you searched the keyword
    private SearchResult createSearchResult(String sku, String name, double price, int availableCount, String team, String additionalInfo) {
        return new SearchResult(sku, name, price, availableCount, team, additionalInfo);
    }



    public static class SearchResult {
        // Fields to store product information
        private String sku;
        private String name;
        private double price;
        private int availableCount;
        private String team;
        private String additionalInfo;

        // Constructor to initialize a SearchResult object with provided data
        public SearchResult(String sku, String name, double price, int availableCount, String team, String additionalInfo) {
            this.sku = sku;
            this.name = name;
            this.price = price;
            this.availableCount = availableCount;
            this.team = team;
            this.additionalInfo = additionalInfo;
        }
        // Getter methods to retrieve the values of the fields
        public String getSku() {
            return sku;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getAvailableCount() {
            return availableCount;
        }

        public String getTeam() {
            return team;
        }

        public String getAdditionalInfo() {
            return additionalInfo;
        }

        @Override
        public String toString() {
            return "SKU: " + sku + ", Name: " + name + ", Price: " + price + ", Available Count: " + availableCount
                    + ", Team: " + team + ", Additional Info: " + additionalInfo;
        }
    }
    
    
}
