import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int rating = 0, min = 1, max = 5;
        Scanner keyboard = new Scanner(System.in);
        boolean error;

        do {
            error = false;
            try {
                System.out.print("\nPlease enter a rating between " + min + " and " + max + ": ");
                rating = keyboard.nextInt();

                //Print error if rating is out of bounds
                if(rating < min || rating > max) {
                    System.out.println("Error: Rating must be an integer between " + min + " and " + max);
                    error = true;
                }

            } catch(InputMismatchException e) {
                //Clear newline from keyboard buffer and print error
                keyboard.nextLine();
                System.out.println("Error: Rating must be an integer between " + min + " and " + max);
                error = true;
            }
        } while(error);

        System.out.println("\nThe rating you entered is: " + rating);
        keyboard.close();
    }
}