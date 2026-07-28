import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        //Create, setup, and show window
        Main frame = new Main();
        frame.setSize(450, 450);
        frame.setTitle("BullsEye");
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics canvas) {
        int x = 50, y = 50, width = 350, height = 350;

        //For each ring
        for (int i = 0; i < 7; i++) {
            //Set draw color
            if(i % 2 == 0)
                canvas.setColor(Color.RED);
            else
                canvas.setColor(Color.WHITE);

            //Draw circle
            canvas.fillOval(x, y, width, height);

            //Update location and size
            x += 25;
            y += 25;
            width -= 50;
            height -= 50;
        }
    }
}