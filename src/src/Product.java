package src;

// Abstract class representing a product
public abstract class Product {
    private String sku;
    private String name;
    private double price;
    private int availableCount; // New attribute for available count

    // Constructor
    public Product(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.availableCount = 0; // Initialize available count to 0
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

    // Getter and setter for available count
    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    // Abstract method to be implemented by subclasses
    public abstract String getProductType();
}

// Figure class representing figure products
class Figure extends Product {
    private String classification;

    // Constructor
    public Figure(String sku, String name, double price, String classification) {
        super(sku, name, price);
        this.classification = classification;
    }

    // Getter and setter for classification
    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public int getAvailableCount() {
        // Implementation specific to Figure class
        return 0; // Return available count for Figure
    }

    // Override getProductType method
    @Override
    public String getProductType() {
        return "Figure";
    }
}

// Puzzle class representing puzzle products
class Puzzle extends Product {
    private int numPieces;

    // Constructor
    public Puzzle(String sku, String name, double price, int numPieces) {
        super(sku, name, price);
        this.numPieces = numPieces;
    }

    // Getter and setter for numPieces
    public int getNumPieces() {
        return numPieces;
    }

    public void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
    }

    @Override
    public int getAvailableCount() {
        // Implementation specific to Puzzle class
        return 0; // Return available count for Puzzle
    }

    // Override getProductType method
    @Override
    public String getProductType() {
        return "Puzzle";
    }
}

// VideoGame class representing video game products
class VideoGame extends Product {
    private String team;

    // Constructor
    public VideoGame(String sku, String name, double price, String team) {
        super(sku, name, price);
        this.team = team;
    }

    // Getter and setter for team
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public int getAvailableCount() {
        // VideoGame doesn't have an available count, so return 0
        return 0;
    }

    // Override getProductType method
    @Override
    public String getProductType() {
        return "VideoGame";
    }
}

// BoardGame class representing board game products
class BoardGame extends Product {
    private int minAge;

    // Constructor
    public BoardGame(String sku, String name, double price, int minAge) {
        super(sku, name, price);
        this.minAge = minAge;
    }

    // Getter and setter for minAge
    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public int getAvailableCount() {
        // Implementation specific to BoardGame class
        return 0; // Return available count for BoardGame
    }

    // Override getProductType method
    @Override
    public String getProductType() {
        return "BoardGame";
    }
}
