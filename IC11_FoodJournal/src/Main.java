import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File binaryFile = new File("FoodJournal.dat");
        DecimalFormat oneDP = new DecimalFormat("0.0");
        Scanner keyboard = new Scanner(System.in);
        PaleoFood[] journal = new PaleoFood[100];
        int choice, calories, carbs, type, cookingTemp, count = 0;
        boolean organic;
        String name;

        System.out.println("~~~~~~~~~~~Welcome to the Paleo Food Journal~~~~~~~~~~~\n");
        //Load array from journal file
        if(binaryFile.exists() && binaryFile.length() > 0)
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));

                //Read file into temp array
                PaleoFood[] temp = (PaleoFood[]) fileReader.readObject();

                //Copy objects from temp array to main array and print them
                System.out.println("~~~Food Recorded in Journal~~~");
                for (int i = 0; i < temp.length; i++) {
                    journal[i] = temp[i];
                    System.out.println(journal[i]);
                }

                //Update the count
                count = temp.length;

                //Print stats of foods read from file
                System.out.println("\nTotal calories consumed         = " + totalCalories(journal, count));
                System.out.println("Number of organic produce eaten = " + numOrganicEaten(journal, count));

                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            }
        else
            System.out.println("[No food eaten,  You must be hungry.]");

        do {
            //Main menu
            System.out.println("\n******* Options Menu *******");
            System.out.println("Enter (1) to record a meat");
            System.out.println("Enter (2) to record a produce");
            System.out.println("Enter (3) to quit");
            choice = keyboard.nextInt();
            //Clear the keyboard buffer
            keyboard.nextLine();

            switch (choice) {
                case 1:
                    try {
                        //Enter a new Meat
                        System.out.print("What is the name of the meat eaten? ");
                        name = keyboard.nextLine();
                        System.out.print("How many calories was it? ");
                        calories = keyboard.nextInt();
                        System.out.print("Enter (1) for animal or (2) for seafood: ");
                        type = keyboard.nextInt();
                        //Make sure type is valid
                        if(type != 1 && type != 2)
                            throw new MysteryMeatException();
                        System.out.print("Enter the cooking temperature: ");
                        cookingTemp = keyboard.nextInt();

                        journal[count++] = new Meat(name, calories, type, cookingTemp);
                    } catch(MysteryMeatException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    //Enter a new Produce
                    System.out.print("What is the name of the produce eaten? ");
                    name = keyboard.nextLine();
                    System.out.print("How many calories was it? ");
                    calories = keyboard.nextInt();
                    System.out.print("How many carbohydrates? ");
                    carbs = keyboard.nextInt();
                    System.out.print("Enter (1) for organic or (2) for non-organic: ");
                    organic = keyboard.nextInt() == 1;
                    journal[count++] = new Produce(name, calories, carbs, organic);
                    break;
            }
        } while(choice != 3);

        System.out.println("~~~Food Recorded in Journal~~~");

        //Save array into journal file if there are items to save and print them
        if(count > 0)
            try {
                ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));

                //Make temp array
                PaleoFood[] temp = new PaleoFood[count];

                //Copy items from main array into temp array and print them
                for (int i = 0; i < temp.length; i++) {
                    System.out.println(journal[i]);
                    temp[i] = journal[i];
                }

                //Write temp array to file
                fileWriter.writeObject(temp);

                fileWriter.close();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }

        //Print stats
        System.out.println("\nTotal calories consumed   = " + totalCalories(journal, count));
        System.out.println("Average calories consumed = " + oneDP.format((double)totalCalories(journal, count) / count));
        System.out.println("Food with most calories   = " + foodWithMostCalories(journal, count));
        System.out.println("Eat healthy and and enjoy your weekend!");

        keyboard.close();
    }

    public static int totalCalories(PaleoFood[] food, int count) {
        int sum  = 0;
        for (int i = 0; i < count; i++) {
            sum += food[i].getCalories();
        }
        return sum;
    }

    public static PaleoFood foodWithMostCalories(PaleoFood[] food, int count) {
        int highestCaloriesIndex = 0;
        //Skip first index since it was selected above
        for (int i = 1; i < count; i++) {
            if(food[i].getCalories() > food[highestCaloriesIndex].getCalories())
                highestCaloriesIndex = i;
        }
        return food[highestCaloriesIndex];
    }

    public static int numOrganicEaten(PaleoFood[] food, int count) {
        int num = 0;
        for (int i = 0; i < count; i++) {
            if(food[i] instanceof Produce && ((Produce) food[i]).isOrganic())
                num++;
        }
        return num;
    }
}