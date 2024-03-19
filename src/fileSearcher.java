import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class fileSearcher {
    private static final String INVENTORYFILE = "toyData.txt";
    private static final String USER_DIRECTORY = System.getProperty("user.dir");
    public static final String  INVENTORY = USER_DIRECTORY + "/res/" + INVENTORYFILE;
    File inventory = new File(INVENTORY); 
    
    

    public String removeToy(String searchSku, boolean remove, Integer amountRemoved) throws IOException{
        File inventoryTemp = new File(INVENTORY);
        BufferedReader br = new BufferedReader(new FileReader(inventory));
        BufferedWriter fw = new BufferedWriter(new FileWriter(inventoryTemp));
        
        String line;
        while ((line = br.readLine()) != null){
            
            String[] parts = line.split(";");
            String sku = parts[0];

            if(sku != searchSku){
                fw.write(line + "\n");
            } else if (sku == searchSku && amountRemoved > 0)
            {
                if((sku.charAt(0)=='0' || sku.charAt(0)=='1'))
                {
                return ("Video Games do not have physical inventory and cannot be detracted");
                } else {

                }
            }
           
                
        }
        br.close();
        fw.close();
        return null;
    }
     
    public ArrayList<String> searchToy(String searchName) throws IOException{
        ArrayList<String> itemsMatched = new ArrayList<>();
        Integer count = 0;
        BufferedReader br = new BufferedReader(new FileReader(inventory));
        String line;                                                                            
        

        while ((line = br.readLine()) != null){
            
            String[] parts = line.split(";");
            String sku = parts[0];
            String name = parts[1];
            String price = parts[2];

            //convert here to free space
            searchName = searchName.toLowerCase();
            String testName = name.toLowerCase();
            
            if(testName.contains(searchName)){
                itemsMatched.add(formatToyName(parts));
                count += 1;
            }
            if(itemsMatched.size() == 0){
                itemsMatched.add("No toy matches that name");
            }else{
            count ++;
            }
        itemsMatched.add("\n" + "["+count+"]" + " Back to main menu\n");
    }
    br.close();
    return itemsMatched;
}
        public String formatToyName(String[] parts){
            String sku = parts[0];
            String name = parts[1];
            String price = parts[2];
            toy formattedName = null;

            if(sku.charAt(0)=='0' || sku.charAt(0)=='1'){
                //figure
            String availableCount = parts[3];
            String classification = parts[4];
            formattedName = new figure(sku, name, price, availableCount, classification);
            
            }
            else if(sku.charAt(0)=='2' || (sku.charAt(0)=='3')){
                //video game 
            String team = parts[3];
            formattedName = new videoGame(sku, name, price, team);
             

            }
            else if(sku.charAt(0)=='4' || sku.charAt(0)=='5' || sku.charAt(0)=='6'){
                //puzzle
            String availableCount = parts[3];
            String numpieces = parts[4];
             formattedName = new puzzle(sku, name, price, availableCount, numpieces);
             
            }
            else if(sku.charAt(0)=='7' || sku.charAt(0)=='8' || sku.charAt(0)=='9'){
                //boardGame
            String availableCount = parts[3];
            String minAge = parts[4];
            formattedName = new boardGame(sku, name, price, availableCount, minAge);
            }
        String FNameFin = formattedName.toString();
        return FNameFin;

        }
        
        
    

    public fileSearcher() {
    }
        
    
}
