import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Scanner file = new Scanner(new File("PresidentialDonations2020.csv"));
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            NumberFormat number = NumberFormat.getInstance();
            String line, name;
            String[] parts;
            int countCandidateA = 0, countCandidateB = 0;
            double price, minCandidateA=Double.MAX_VALUE,minCandidateB=Double.MAX_VALUE,
                    maxCandidateA=Double.MIN_VALUE, maxCandidateB=Double.MIN_VALUE, sumCandidateA=0.0, sumCandidateB=0.0;


            //Clear CSV header
            file.nextLine();

            while (file.hasNextLine()) {
                //Get the next line and split the columns
                line = file.nextLine();
                parts = line.split(",");

                //Extract useful columns of data
                name = parts[1];
                price = Double.parseDouble(parts[8]);

                //Skip if price is below 0
                if(price < 0)
                    continue;

                //Update the stats for candidate a
                if("Biden Joseph R Jr".equalsIgnoreCase(name)) {
                    countCandidateA++;
                    sumCandidateA += price;
                    if(price > maxCandidateA) {
                        maxCandidateA = price;
                    }
                    if(price < minCandidateA) {
                        minCandidateA = price;
                    }
                }

                //Update the stats for candidate b
                if("Yang Andrew".equalsIgnoreCase(name)) {
                    countCandidateB++;
                    sumCandidateB += price;
                    if(price > maxCandidateB) {
                        maxCandidateB = price;
                    }
                    if(price < minCandidateB) {
                        minCandidateB = price;
                    }
                }
            }

            //Display the stats for candidate a
            System.out.println("************************************************");
            System.out.println("*     2020 Presidential Campaign Donations     *");
            System.out.println("*                For Candidate                 *");
            System.out.println("*             Biden, Joseph R Jr               *");
            System.out.println("************************************************");
            System.out.println("The minimum contribution was: " + currency.format(minCandidateA));
            System.out.println("The maximum contribution was: " + currency.format(maxCandidateA));
            System.out.println("The average contribution was: " + currency.format(sumCandidateA / countCandidateA));
            System.out.println("The number of contributions was: " + number.format(countCandidateA));
            System.out.println("The total amount contributed was: " + currency.format(sumCandidateA));

            System.out.println();

            //Display the stats for candidate b
            System.out.println("************************************************");
            System.out.println("*     2020 Presidential Campaign Donations     *");
            System.out.println("*                For Candidate                 *");
            System.out.println("*                Yang, Andrew                  *");
            System.out.println("************************************************");
            System.out.println("The minimum contribution was: " + currency.format(minCandidateB));
            System.out.println("The maximum contribution was: " + currency.format(maxCandidateB));
            System.out.println("The average contribution was: " + currency.format(sumCandidateB / countCandidateB));
            System.out.println("The number of contributions was: " + number.format(countCandidateB));
            System.out.println("The total amount contributed was: " + currency.format(sumCandidateB));

            //Close the file
            file.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}