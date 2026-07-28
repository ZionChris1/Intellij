import java.util.Scanner;
import java.text.NumberFormat;

public class Main {
    public static void main(String[] args) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        Scanner keyboard = new Scanner(System.in);
        TradingCard[] cards = new TradingCard[10];
        String name, rarity, cardClass, energyType;
        int choice, hitPoints, count = 0;
        boolean golden;
        double price;

        do {
            //Print menu
            System.out.println("\n~~~Welcome to Trading Card Central~~~");
            System.out.println("Enter (1) to enter a Pokemon card");
            System.out.println("Enter (2) to enter a Hearthstone card");
            System.out.println("Enter (3) exit");
            choice = keyboard.nextInt();

            //Break out of loop if user decides to exit
            if (choice == 3)
                break;

            //Clear keyboard buffer
            keyboard.nextLine();

            //Collect data common to both card types
            System.out.print("\nWhat is card's name? ");
            name = keyboard.nextLine();
            System.out.print("What is it's price? $");
            price = keyboard.nextDouble();
            System.out.print("What is it's rarity? ");
            keyboard.nextLine();
            rarity = keyboard.nextLine();

            //Collect card type specific info and create card
            switch (choice) {
                case 1: //Pokemon
                    System.out.print("Pokemon energy type? ");
                    energyType = keyboard.nextLine();
                    System.out.print("How many hit points? ");
                    hitPoints = keyboard.nextInt();

                    cards[count++] = new Pokemon(name, price, rarity, energyType, hitPoints);

                    break;
                case 2: //Hearthstone
                    System.out.print("Hearthstone class  ? ");
                    cardClass = keyboard.nextLine();
                    System.out.print("Is it a golden card? ");
                    golden = keyboard.nextBoolean();

                    cards[count++] = new Hearthstone(name, price, rarity, cardClass, golden);
                    break;
            }
        }while (true);

        //print all cards and the average price
        System.out.println("\n~~~All Trading Cards~~~");
        for (int i = 0; i < count; i++) {
            System.out.println(cards[i]);
        }
        System.out.println("\nAverage card price = " + currency.format(averagePrice(cards, count)));

        System.out.println("\nHope we \"trade\" again in a future CS course together soon!");

        keyboard.close();
    }

    public static double averagePrice(TradingCard[] cards, int count) {
        double sum = 0;

        //Avoid division by 0
        if (count == 0)
            return 0.0;

        //Sum all the card prices
        for (int i = 0; i < count; i++) {
            sum += cards[i].getPrice();
        }

        //Divide by number of cards to get average
        return sum / count;
    }
}