import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            double sum = 0.0, low = Double.MAX_VALUE, high = Double.MIN_VALUE, price;
            int count = 0;
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            NumberFormat number = NumberFormat.getInstance();
            Scanner fileScanner = new Scanner(new File("bitcoin_prices_full.txt"));

            //Skip header
            fileScanner.nextLine();

            //Read all values
            while(fileScanner.hasNextDouble()) {
                //get next price
                price = fileScanner.nextDouble();

                //Update count, sum, high, and low
                sum += price;
                count++;
                if(price > high)
                    high = price;
                if(price < low)
                    low = price;
            }

            //Close file when done
            fileScanner.close();

            // Display statistics
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Bitcoin Statistics: Dec 2014 - Jun 2018");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Number of price quotes: " + number.format(count));
            System.out.println("Average price: " + currency.format((sum/count)));
            System.out.println("Lowest  price: " + currency.format(low));
            System.out.println("Highest price: " + currency.format(high));

        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}