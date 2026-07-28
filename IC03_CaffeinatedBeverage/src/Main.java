import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        Scanner keyboard = new Scanner(System.in);
        int choice, ounces, count = 0;
        String name, roastType, flavor;
        double price;

        CaffeinatedBeverage[] inventory = new CaffeinatedBeverage[7];

        do {
            System.out.println("\n~~~Welcome to MoonBucks~~~");
            System.out.println("Enter (1) for Coffee");
            System.out.println("Enter (2) for Energy Drink");
            System.out.println("Enter (3) to Exit");
            System.out.print(">> ");

            choice = keyboard.nextInt();
            keyboard.nextLine();

            switch (choice) {
                case 1:    //Order Coffee
                    System.out.print("Enter the name: ");
                    name = keyboard.nextLine();
                    System.out.print("Enter the ounces: ");
                    ounces = keyboard.nextInt();
                    System.out.print("Enter the price $ ");
                    price = keyboard.nextDouble();
                    keyboard.nextLine();
                    System.out.print("Enter the roast type: ");
                    roastType = keyboard.nextLine();
                    inventory[count++] = new Coffee(name, ounces, price, roastType);
                    break;
                case 2:    //Order Energy Drink
                    System.out.print("Enter the name: ");
                    name = keyboard.nextLine();
                    System.out.print("Enter the ounces: ");
                    ounces = keyboard.nextInt();
                    System.out.print("Enter the price $ ");
                    price = keyboard.nextDouble();
                    keyboard.nextLine();
                    System.out.print("Enter the flavor: ");
                    flavor = keyboard.nextLine();
                    inventory[count++] = new EnergyDrink(name, ounces, price, flavor);
                    break;
                case 3:    //Quit
                    //Print all beverages
                    System.out.println("\nAll drinks ordered:");
                    for(int i = 0; i < count; i++)
                        System.out.println(inventory[i]);

                    //Print highest cost energy drink and average of all drinks
                    System.out.println("\nHighest priced energy drink:");
                    System.out.println(findHighestPricedEnergyDrink(inventory, count));
                    System.out.println("\nAverage price of beverages ordered: " + currency.format(findAveragePrice(inventory, count)));
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while(choice != 3);

        keyboard.close();
    }

    public static double findAveragePrice(CaffeinatedBeverage[] inventory, int count) {
        double sum = 0;

        //Avoid division by 0
        if(count == 0)
            return 0.0;

        //Sum the prices
        for(int i = 0; i < count; i++) {
            sum += inventory[i].getPrice();
        }

        //divide by number of items
        return sum / count;
    }

    public static EnergyDrink findHighestPricedEnergyDrink(CaffeinatedBeverage[] inventory, int count) {
        EnergyDrink highestPriceDrink = null;
        double highestPrice = 0;

        //For each beverage
        for(int i = 0; i < count; i++) {
            //If inventory[i] is an EnergyDrink object compare it's price to highestPrice
            if(inventory[i] instanceof EnergyDrink && inventory[i].getPrice() > highestPrice)
                highestPriceDrink = (EnergyDrink)inventory[i];
        }

        return highestPriceDrink;
    }
}