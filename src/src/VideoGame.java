public class VideoGame extends Product {
    private String team;

    public VideoGame(String sku, String name, double price, String team, int availableCount) {
        super(sku, name, price, availableCount);
        this.team = team;
    }
    

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String getProductType() {
        return "VideoGame";
    }

    @Override
    public void decrementStock() {
        // Implement decrementStock logic here
        // For example:
        if (getAvailableCount() > 0) {
            setAvailableCount(getAvailableCount() - 1);
        } else {
            System.out.println("Product is out of stock.");
        }
    }
}
