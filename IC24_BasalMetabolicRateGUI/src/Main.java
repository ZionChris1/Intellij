import javax.swing.*;
import java.text.DecimalFormat;

public class Main extends JFrame {
    public static void main(String[] args) {
        String input, message;
        double weight, height, age, bmr;

        //Get weight
        input = JOptionPane.showInputDialog(null,
                "Please enter your weight in pounds",
                "BMR Calculator",
                JOptionPane.QUESTION_MESSAGE);
        weight = Double.parseDouble(input);

        //Get height
        input = JOptionPane.showInputDialog(null,
                "Please enter your height in inches:",
                "BMR Calculator",
                JOptionPane.QUESTION_MESSAGE);
        height = Double.parseDouble(input);

        //Get age
        input = JOptionPane.showInputDialog(null,
                "Please enter your age in years",
                "BMR Calculator",
                JOptionPane.QUESTION_MESSAGE);
        age = Double.parseDouble(input);

        //Get gender
        String[] buttons = {"Female", "Male"};
        int response = JOptionPane.showOptionDialog(null,
                "Calculate BMR for female or male?",
                "BMR Calculator",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                buttons,
                buttons[0]);

        //Get activity level
        String[] levels = {"Sedentary", "Slightly Active", "Active", "Highly Active"};
        Object selectedValue = JOptionPane.showInputDialog(
                null,
                "Chosse one:",
                "BMR Calculator",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                levels,
                levels[0]);
        String activity = selectedValue.toString();

        //Calculate BMR for chosen gender
        if (response == 0)
            bmr = 655 + (4.35 * weight) + (4.7 * height) - (4.7 * age);
        else
            bmr = 66 + (6.23 * weight) + (12.7 * height) - (6.8 * age);

        //Adjust BMR according to activity level
        switch(activity) {
            case "Sedentary" -> {bmr *= 1.2;}
            case "Slightly Active" -> {bmr *= 1.3;}
            case "Active" -> {bmr *= 1.4;}
            case "Highly Active" -> {bmr *= 1.5;}
        }

        //Generate output message
        DecimalFormat noDP = new DecimalFormat("0");
        message = "As a " + buttons[response] + ", your BMR x Activity Factor is "
                + noDP.format(bmr) + " calories and you need to eat "
                + noDP.format(bmr / 230) + " chocolate bars to maintain this amount of calories.";

        //Show BMR
        JOptionPane.showMessageDialog(null,
                message,
                "BMR Calculator",
                JOptionPane.INFORMATION_MESSAGE);
    }
}