import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, ArrayList<Double>> candidates = new HashMap<String, ArrayList<Double>>();
            Scanner file = new Scanner(new File("PresidentialDonations2020.csv"));
            String line, name;
            String[] parts;
            double amount;

            //Clear CSV header
            file.nextLine();

            while (file.hasNextLine()) {
                //Get the next line and split the columns
                line = file.nextLine();
                parts = line.split(",");

                //Extract useful columns of data
                name = parts[1];
                amount = Double.parseDouble(parts[8]);

                //Skip donations of 0 or less
                if(amount <= 0)
                    continue;

                //If a candidate is found that is not in the map add a new arraylist of prices
                if(!candidates.containsKey(name))
                    candidates.put(name, new ArrayList<Double>());

                //Add the donation to the list for the candidate
                candidates.get(name).add(amount);
            }

            //For each candidate
            for(String candidate : candidates.keySet()) {
                System.out.println();
                CalculateCandidateStats(candidate, candidates.get(candidate));

            }

            //Close the file
            file.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Calculates the donation stats for a candidate and prints them.
     *
     * @param candidate the candidate's name.
     * @param donations an ArrayList containing all the candidates donations.
     */
    public static void CalculateCandidateStats(String candidate, ArrayList<Double> donations) {
        double min=Double.MAX_VALUE, max=Double.MIN_VALUE, sum=0.0;
        int count = 0;

        //For each donation
        for(double amount : donations) {
            //Update the stats for the candidate
            count++;
            sum += amount;
            if(amount > max) {
                max = amount;
            }
            if(amount < min) {
                min = amount;
            }
        }

        PrintCandidateStats(candidate, min, max, sum, count);
    }

    /**
     *Takes a candidate name and statistics and prints them.
     *
     * @param candidate the name of the candidate.
     * @param min the minimum donation size.
     * @param max the maximum donation size.
     * @param sum the total donation amount.
     * @param count the number of donations.
     */
    public static void PrintCandidateStats(String candidate, double min, double max, double sum, int count) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat number = NumberFormat.getInstance();

        System.out.println("************************************************");
        System.out.println("*     2020 Presidential Campaign Donations     *");
        System.out.println("*                 For Candidate                *");
        System.out.println("*" + center(candidate, 46) + "*");
        System.out.println("************************************************");
        System.out.println("The minimum contribution was: " + currency.format(min));
        System.out.println("The maximum contribution was: " + currency.format(max));
        System.out.println("The average contribution was: " + currency.format(sum / count));
        System.out.println("The number of contributions was: " + number.format(count));
        System.out.println("The total amount contributed was: " + currency.format(sum));
    }


    /**
     * Centers the given text in a string of size length.
     *
     * @param text the text to center.
     * @param length the length of the desired String.
     */
    public static String center(String text, int length) {
        String centeredString = "";

        //Add prefix spaces
        for(int i = 0; i < length/2 - text.length()/2; i++)
            centeredString += " ";

        //Add the text
        centeredString += text;

        //Add postfix spaces accounting for if the length is odd
        if(text.length()%2 == 0)
            for(int i = 0; i < length/2 - text.length()/2; i++)
                centeredString += " ";
        else
            for(int i = 0; i < length/2 - text.length()/2 - 1; i++)
                centeredString += " ";
        return centeredString;
    }
}