public class Figure extends Product {
    private String classification;

    // Constructor
    public Figure(String sku, String name, double price, int availableCount, String classification) {
        super(sku, name, price, availableCount);
        this.classification = classification;
    }

    // Getter and setter for classification
    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    // Overriding getProductType method from Product class
    @Override
    public String getProductType() {
        return "Figure";
    }

    // Override toString method to provide custom string representation
    @Override
    public String toString() {
        return "Figure: " + super.toString() + ", Classification: " + classification;
    }

    @Override
    public void decrementStock() {
        if (getAvailableCount() > 0) {
            setAvailableCount(getAvailableCount() - 1); // Decrement the available count by 1
            System.out.println("Stock decremented for figure: " + getName() + " (SKU: " + getSku() + ")");
        } else {
            System.out.println("No stock available for figure: " + getName() + " (SKU: " + getSku() + ")");
        }
    }
}
