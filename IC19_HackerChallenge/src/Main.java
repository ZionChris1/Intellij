import java.util.Random;

public class Main {
    public static void main(String[] args) {
        HugeNumber largestPrime = new HugeNumber();
        Random r = new Random();

        //Add 24.8 thousand digits to the number
        for (int i = 0; i < 24_862; i++) {
            largestPrime.addDigit(Integer.toString(r.nextInt(10)));
        }

        //Print the number
        System.out.println(largestPrime);
    }
}
