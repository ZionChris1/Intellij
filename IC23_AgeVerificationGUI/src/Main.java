import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        int response;

        do {
            //Ask if age over 21
            response = JOptionPane.showConfirmDialog(null,
                    "Are you 21 years of age or older?",
                    "Age Verification",
                    JOptionPane.YES_NO_OPTION);

            switch (response) {
                //If answer yes allow through
                case JOptionPane.YES_OPTION -> JOptionPane.showMessageDialog(null,
                        "Proceed on, adult!",
                        "You are an Adult", JOptionPane.INFORMATION_MESSAGE);
                //If answer no block
                case JOptionPane.NO_OPTION -> JOptionPane.showMessageDialog(null,
                        "You shall not pass!",
                        "Minor Detected",
                        JOptionPane.ERROR_MESSAGE);
                //If close attempted show error
                case JOptionPane.CLOSED_OPTION -> JOptionPane.showMessageDialog(null,
                        "You must answer the question",
                        "Aversion Detected",
                        JOptionPane.ERROR_MESSAGE);
            }
            //Retry while close is clicked
        } while(response == JOptionPane.CLOSED_OPTION);
    }
}