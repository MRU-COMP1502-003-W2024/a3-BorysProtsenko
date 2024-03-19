import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class menu {
boolean exited = true;

    public menu() throws IOException{
    }

public void newMenu() throws IOException{
        while (exited == true) {
    

        String input;
        Scanner keyBoard = new Scanner(System.in);
        System.out.print("[1] Search inventory and purchase toy\r\n" + //
                "[2] Add new product\r\n" + //
                "[3] Remove product\r\n" + //
                "[4] Save & exit\r\n" + //
                "Enter an option: ");
        input = keyBoard.nextLine();
       
        if(input.equals("1")){
            System.out.print("Enter search term: ");
            input = keyBoard.nextLine();
            fileSearcher search = new fileSearcher();
            ArrayList<String> matchedToys = search.searchToy(input);
            System.out.println(matchedToys);
            System.out.print("Enter SKU number to purchase: ");
            input = keyBoard.nextLine();
            String sku = input;

                }
        if(input.equals("2")){
        
        }
        if(input.equals("3")){
        
        }
        if(input.equals("4")){
            System.out.println("saving file...");

            exited = true;
        }
    }
}
}

