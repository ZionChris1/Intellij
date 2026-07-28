import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Scanner file = new Scanner(new File("USPizzaDataset.csv"));
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            NumberFormat number = NumberFormat.getInstance();
            String line, city, name, highestName="", lowestName="", highestOsideName="", lowestOsideName="";
            String[] parts;
            int count = 0, countOside = 0;
            double price, lowest=Double.MAX_VALUE,lowestOside=Double.MAX_VALUE,
                    highest=Double.MIN_VALUE, highestOside=Double.MIN_VALUE, sum=0.0, sumOside=0.0;


            //Clear CSV header
            file.nextLine();

            while (file.hasNextLine()) {
                //Get the next line and split the columns
                line = file.nextLine();
                parts = line.split(",");

                //Extract useful columns of data
                city = parts[2];
                price = Double.parseDouble(parts[6]);
                name = parts[9];

                //Update statistics
                count++;
                sum += price;
                if(price > highest) {
                    highest = price;
                    highestName = name;
                }
                if(price < lowest) {
                    lowest = price;
                    lowestName = name;
                }

                //Update the stats for Oceanside
                if("Oceanside".equalsIgnoreCase(city)) {
                    countOside++;
                    sumOside += price;
                    if(price > highestOside) {
                        highestOside = price;
                        highestOsideName = name;
                    }
                    if(price < lowestOside) {
                        lowestOside = price;
                        lowestOsideName = name;
                    }
                }
            }

            // Display the statistics!
            System.out.println("**************************************");
            System.out.println("*     US Pizza Dataset: National     *");
            System.out.println("**************************************");
            System.out.println("Number of entries reported: " + number.format(count));
            System.out.println("\nHighest priced pizzeria (nationally): " + highestName);
            System.out.println("Pizza Price : " + currency.format(highest));
            System.out.println("\nLowest priced pizzeria (nationally) : " + lowestName);
            System.out.println("Pizza Price : " + currency.format(lowest));
            System.out.println("\nAverage price of pizza (nationally)   : " + currency.format(sum / count));

            // Display the statistics for Oceanside
            System.out.println("\n**************************************");
            System.out.println("Number of entries reported: " + number.format(countOside));
            System.out.println("\nHighest priced pizzeria (Oceanside): " + highestOsideName);
            System.out.println("Pizza Price : " + currency.format(highestOside));
            System.out.println("\nLowest priced pizzeria (Oceanside) : " + lowestOsideName);
            System.out.println("Pizza Price : " + currency.format(lowestOside));
            System.out.println("\nAverage price of pizza (Oceanside)   : " + currency.format(sumOside / countOside));

            //Close the file
            file.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}