abstract class toy {
    String skuNmbr;
    String name;
    String price;

}
class videoGame extends toy{
    private String team;
    /**
     * 
     * @param skuString
     * @param nameString
     * @param priceString
     * @param teamString
     */
    public videoGame(String skuString, String nameString, String priceString, String teamString){

        team = teamString;
        skuNmbr = skuString;
        name = nameString;
        price = priceString;
        
    }
    @Override
    public String toString() {
        
        return (skuNmbr + ": " + name + ", " + team + ", $" + price);
    }
        
    
    
}

class figure extends toy{
    String availCount;
    String classification;
/**
 * 
 * @param skuString
 * @param nameString
 * @param priceString
 * @param availableCount
 * @param classString
 */
    public figure(String skuString, String nameString, String priceString, String availableCount, String classString){
        skuNmbr = skuString;
        name = nameString;
        price = priceString;
        availCount = availableCount;
        if (classString.charAt(0)=='A') {
            classification = "Action";
        }
        else if(classString.charAt(0)=='D'){
            classification = "Doll";
        }
        else{
            classification = "Historic";
        }
    }
    @Override
    public String toString() {
        
        return (skuNmbr + ": " + name + ", " + availCount + ", " + classification + "," + " $" + price);
    }
}
class puzzle extends toy{
    String availCount;
    String nmbrPieces;
    /**
     * 
     * @param skuString
     * @param nameString
     * @param priceString
     * @param availableCount
     * @param numpieces
     */
    public puzzle(String skuString, String nameString, String priceString, String availableCount, String numpieces) 
    {
        skuNmbr = skuString;
        name = nameString;
        price = priceString;
        availCount = availableCount;
        nmbrPieces = numpieces;
    }
    @Override
    public String toString() {
        
        return (skuNmbr + ": " + name + ", " + availCount + ", " + nmbrPieces + "," + " $" + price);
    }
}
class boardGame extends toy{
    String availCount;
    String minAge;
    /**
     * 
     * @param skuString
     * @param nameString
     * @param priceString
     * @param availableCount
     * @param minAgeString
     */
    public boardGame(String skuString, String nameString, String priceString, String availableCount, String minAgeString){
        skuNmbr = skuString;
        name = nameString;
        price = priceString;
        availCount = availableCount;
        minAge = minAgeString;
   }
   @Override
   public String toString() {
       
       return (skuNmbr + ": " + name + ", " + availCount + "left, " + minAge + "," + " $" + price);
   }

}



