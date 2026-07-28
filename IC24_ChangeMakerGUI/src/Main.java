import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        String input, message;
        int cents, quarters, dimes, nickels, pennies;

        do {
            //Show input window
             input = JOptionPane.showInputDialog(null,
                    "Please enter a number of cents between 1 and 99:",
                    "Change Maker",
                    JOptionPane.QUESTION_MESSAGE);
            if (input == null)
                //Quit if close button is pushed
                System.exit(0);
            else if ("".equals(input)) {
                //Show error
                JOptionPane.showMessageDialog(null,
                        "Error: Cents must be between 1 and 99",
                        "Change Maker",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                //Convert input to integer and parse into coins
                cents = Integer.parseInt(input);
                quarters = cents / 25;
                cents %= 25;

                dimes = cents / 10;
                cents %= 10;

                nickels = cents / 5;
                cents %= 5;

                pennies = cents;

                //Compose message and show dialog
                message = input + " cents in coins can be given as:\n" +
                        quarters + "quarter(s)\n" +
                        dimes + "dime(s)\n" +
                        nickels + "nickel(s) and\n" +
                        pennies + "penny(ies)";

                JOptionPane.showMessageDialog(null,
                        message,
                        "Change Maker",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            //Repeat as long as no input is provided
        } while("".equals(input));
    }
}