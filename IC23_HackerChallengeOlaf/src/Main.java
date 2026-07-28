import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

public class Main extends JFrame {
    static int mX, mY, mWidth, mHeight, mStartAngle, mArcAngle;
    public static void main(String[] args) {
        //Setup location variables
        mX = 0;
        mY = 0;
        mWidth = 0;
        mHeight = 0;
        mStartAngle = 0;
        mArcAngle = 0;

        //Setup and show window
        Main frame = new Main();
        frame.setSize(300, 500);
        //Change close operation
        //Dispose method overwritten below to print location variables on exit
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        //Cast to Graphics2D to get more drawing methods
        Graphics2D gtd = (Graphics2D) g;

        //Call super.paint to clear the window
        super.paint(g);

        //Step 1
        //Draw eye outline
        gtd.drawOval(100, 100, 20, 20);
        gtd.drawOval(120, 100, 20, 20);

        //Step 2
        //Draw top of nose
        gtd.drawArc(100, 120, 40, 20, 90, -90);

        //Step 3
        //Draw eyes
        gtd.fillOval(102, 102, 16, 16);
        gtd.fillOval(122, 102, 16, 16);

        //Draw front of nose
        gtd.drawArc(133, 130, 14, 15, 90, -100);

        //Step 4
        //Draw bottom of nose
        gtd.drawArc(119, 109, 70, 29, -100, -90);

        //Draw eyebrows
        gtd.setStroke(new BasicStroke(3));
        gtd.drawArc(108, 90, 34, 40, 19, 63);
        gtd.drawArc(96, 90, 60, 38, 110, 61);
        gtd.setStroke(new BasicStroke(1));

        //Step 5
        //Draw smile
        gtd.drawArc(96, 124, 86, 22, -180, 99);
        gtd.drawArc(124, 128, 32, 18, -73, 110);
        gtd.drawArc(84, 132, 38, 38, 99, 32);

        //Step 6
        //Draw top of head
        gtd.drawArc(84, 118, 34, 34, 85, 114);
        gtd.drawArc(94, 84, 50, 28, 0, 182);
        gtd.drawArc(84, 78, 66, 126, 135, 35);

        //Draw tooth
        gtd.drawLine(144, 98, 146, 116);
        gtd.drawLine(148, 146, 150, 158);
        gtd.drawArc(76, 122, 194, 38, -125, 22);
        gtd.drawArc(112, 86, 82, 104, 187, 15);

        //Step 7
        //Draw sides of face and neck
        gtd.drawArc(84, 36, 150, 196, 184, 34);
        gtd.drawArc(94, 108, 62, 58, 4, 45);
        gtd.drawArc(66, -144, 98, 360, -64, 32);

        //Step 8
        //Draw neck bottom
        gtd.drawArc(88, 166, 92, 34, -139, 50);

        //Draw nose details
        gtd.drawArc(122, 120, 14, 14, 112, 108);
        gtd.drawArc(130, 124, 10, 12, 83, 91);
        gtd.drawArc(124, 126, 6, 46, 94, 42);
        gtd.drawArc(130, 126, 18, 38, 120, 27);

        //Step 9
        //Draw upper body
        gtd.drawArc(80, 152, 62, 122, -26, 47);
        gtd.drawArc(92, 170, 40, 88, 144, 64);
        gtd.drawArc(78, 190, 98, 52, -132, 57);

        //Step 10
        //Draw lower body
        gtd.drawArc(74, 228, 82, 86, 122, 67);
        gtd.drawArc(74, 240, 48, 76, 178, 100);
        gtd.drawArc(106, 234, 50, 76, -12, 83);
        gtd.drawArc(92, 306, 42, 12, -125, 71);
        gtd.drawArc(84, 248, 72, 70, -78, 84);

        //Step 11
        //Draw legs
        gtd.drawArc(130, 278, 10, 62, -50, 47);
        gtd.drawArc(112, 316, 6, 22, 135, 97);
        gtd.drawArc(100, 310, 48, 26, -116, 62);
        gtd.drawArc(88, 306, 18, 44, 142, 59);
        gtd.drawArc(84, 324, 32, 12, -125, 85);

        //Draw arms
        gtd.setStroke(new BasicStroke(5));
        gtd.drawLine(74, 170, 94, 196);
        gtd.drawLine(46, 146, 72, 168);
        gtd.drawArc(124, 222, 14, 68, -41, 117);
        gtd.setStroke(new BasicStroke(1));

        //Step 12
        //Draw buttons
        gtd.fillOval(102, 210, 20, 20);
        gtd.fillOval(98, 254, 20, 20);
        gtd.fillOval(98, 284, 20, 20);

        //Draw fingers
        gtd.setStroke(new BasicStroke(3));
        gtd.drawLine(54, 138, 50, 146);
        gtd.drawLine(34, 138, 44, 146);
        gtd.drawLine(42, 132, 44, 144);
        gtd.drawLine(32, 148, 42, 146);
        gtd.drawLine(126, 274, 136, 274);
        gtd.drawLine(128, 284, 136, 276);
        gtd.drawLine(136, 286, 136, 278);
        gtd.drawLine(146, 282, 136, 272);
        gtd.setStroke(new BasicStroke(1));

        //Step 13
        //Draw hair
        gtd.drawArc(88, 64, 26, 42, 0, 53);
        gtd.drawArc(96, 70, 18, 22, 30, 121);
        gtd.drawArc(94, 48, 26, 52, -23, 72);
        gtd.drawArc(124, 62, 24, 40, 118, 75);
        gtd.drawArc(120, 66, 26, 48, 60, 63);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        //Reject all key events except KEY_TYPED
        if(e.getID() != KeyEvent.KEY_TYPED)
            return;

        //Adjust variables based on which key was pressed
        switch(e.getKeyCode()) {
        case KeyEvent.VK_W -> {mY--;}
        case KeyEvent.VK_S -> {mY++;}
        case KeyEvent.VK_A -> {mX--;}
        case KeyEvent.VK_D -> {mX++;}
        case KeyEvent.VK_I -> {mHeight++;}
        case KeyEvent.VK_K -> {mHeight--;}
        case KeyEvent.VK_J -> {mWidth--;}
        case KeyEvent.VK_L -> {mWidth++;}
        case KeyEvent.VK_UP -> {mStartAngle++;}
        case KeyEvent.VK_DOWN -> {mStartAngle--;}
        case KeyEvent.VK_LEFT -> {mArcAngle--;}
        case KeyEvent.VK_RIGHT -> {mArcAngle++;}
        }

        //Repaint the window
        paint(getGraphics());
    }

    @Override
    public void dispose() {
        super.dispose();

        //Print position variables and exit
        System.out.println("" + mX + ", " + mY + ", " + mWidth + ", " + mHeight + ", " + mStartAngle + ", " + mArcAngle);
        System.exit(0);
    }
}