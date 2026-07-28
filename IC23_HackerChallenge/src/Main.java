import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        //Setup and show window
        Main frame = new Main();
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        //Set size and initial location
        int x = 50, y = 50, width = 70, height = 70;

        //For each row
        for (int i = 0; i < 10; i++) {
            //Draw each column
            for (int j = 0; j < 10; j++) {
                //Draw outline
                g.setColor(Color.BLACK);
                g.fillOval(x, y, width, height);

                //Pick color and draw face
                if (i % 2 == 0)
                    g.setColor(Color.YELLOW);
                else
                    g.setColor(Color.BLUE);
                g.fillOval(x + 2, y + 2, width - 4, height - 4);

                //Draw rest of face
                g.setColor(Color.BLACK);
                g.fillOval(x + 22, y + 20, 5, 10);
                g.fillOval(x + 46, y + 20, 5, 10);
                g.fillRect(x + 33, y + 33, 5, 5);
                g.drawArc(x + 15, y + 40, 40, 20, 180, 180);

                //Update draw location
                y += 70;
            }
            //Reset row
            y = 50;
            //Update draw column
            x += 70;
        }
    }
}