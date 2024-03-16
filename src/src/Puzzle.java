public class Puzzle extends Product {
    private int numPieces;

    // Constructor
    public Puzzle(String sku, String name, double price, int availableCount, int numPieces) {
        super(sku, name, price, availableCount);
        this.numPieces = numPieces;
    }

    // Getter and setter for numPieces
    public int getNumPieces() {
        return numPieces;
    }

    public void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
    }

    // Overriding getProductType method from Product class
    @Override
    public String getProductType() {
        return "Puzzle";
    }

    // Override toString method to provide custom string representation
    @Override
    public String toString() {
        return "Puzzle: " + super.toString() + ", Number of Pieces: " + numPieces;
    }

    @Override
    public void decrementStock() {
        if (getAvailableCount() > 0) {
            setAvailableCount(getAvailableCount() - 1); // Decrement the available count by 1
            System.out.println("Stock decremented for puzzle: " + getName() + " (SKU: " + getSku() + ")");
        } else {
            System.out.println("No stock available for puzzle: " + getName() + " (SKU: " + getSku() + ")");
        }
    }
}
