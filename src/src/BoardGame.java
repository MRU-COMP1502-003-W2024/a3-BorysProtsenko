public class BoardGame extends Product {
    private int minAge;

    // Constructor
    public BoardGame(String sku, String name, double price, int availableCount, int minAge) {
        super(sku, name, price, availableCount);
        this.minAge = minAge;
    }

    // Getter and setter for minAge
    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    // Overriding getProductType method from Product class
    @Override
    public String getProductType() {
        return "BoardGame";
    }

    // Override toString method to provide custom string representation
    @Override
    public String toString() {
        return "BoardGame: " + super.toString() + ", Minimum Age: " + minAge;
    }

    @Override
    public void decrementStock() {
        if (getAvailableCount() > 0) {
            setAvailableCount(getAvailableCount() - 1);
        } else {
            System.out.println("Error: Attempted to decrement stock of " + getName() + ", but stock is already zero.");
        }
    }
}
