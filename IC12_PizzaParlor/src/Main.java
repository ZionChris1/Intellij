import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice;
        char size;
        boolean pepperoni, sausage, mushrooms, vegan;
        Scanner keyboard = new Scanner(System.in);
        PizzaOrder currentOrder = new PizzaOrder();

        do {
            //Main menu
            System.out.println("~~~Welcome to MiraCosta's Pizza Parlor~~~");
            System.out.println("Enter (1) to order a Pizza");
            System.out.println("Enter (2) to view order");
            System.out.println("Enter (3) to checkout");
            choice = keyboard.nextInt();
            System.out.println();
            //Clear keyboard buffer
            keyboard.nextLine();

            switch (choice) {
                case 1: //Add pizza to order
                    System.out.println("What size pizza would you like (S, M, L)?");
                    try {
                        //Get size
                        size = keyboard.nextLine().toUpperCase().charAt(0);

                        //Get toppings
                        System.out.println("Please enter your toppings:");
                        System.out.print("Pepperoni (Y/N)? ");
                        pepperoni = keyboard.nextLine().toUpperCase().startsWith("Y");
                        System.out.print("Sausage   (Y/N)? ");
                        sausage = keyboard.nextLine().toUpperCase().startsWith("Y");
                        System.out.print("Mushrooms (Y/N)? ");
                        mushrooms = keyboard.nextLine().toUpperCase().startsWith("Y");
                        System.out.print("Vegan     (Y/N)? ");
                        vegan = keyboard.nextLine().toUpperCase().startsWith("Y");

                        //Make sure size is valid
                        if (size != 'S' && size != 'M' && size != 'L')
                            throw new UnknownSizeException("Pizza size must be S, M, or L");

                        //Add pizza to order
                        currentOrder.addPizzaToOrder(size, pepperoni, sausage, mushrooms, vegan);
                    } catch(UnknownSizeException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 2: //Print current order
                    System.out.println(currentOrder);
                    break;

                case 3: //Finalize order
                    NumberFormat currency = NumberFormat.getCurrencyInstance();
                    System.out.println("Please pay " + currency.format(currentOrder.calcCost()) + " at the counter.");
                    System.out.println("Thanks for visiting MiraCosta's Pizza Parlor!");
                    break;
            }
            System.out.println();
        } while (choice != 3);

        keyboard.close();
    }
}
