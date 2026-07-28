import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Security[] portfolio = new Security[6];


        portfolio[0] = new CommonStock("1234567", "Apple, Inc.", 134.89, 100, 100);
        portfolio[1] = new Bond("7654321", "U.S. Govt. 2 Year", 0.0113, 2, 10_000.0);
        portfolio[2] = new CommonStock("1234566", "Amazon, Inc.", 1116.5, 1100, 50);
        portfolio[3] = new Bond("7654320", "U.S. Govt. 5 Year", 0.022, 4, 5_000.0);
        portfolio[4] = new CommonStock("1234567", "Apple, Inc.", 134.89, 135, 25);
        portfolio[5] = new Bond("7654321", "U.S. Govt. 2 Year", 0.01, 1, 10_000);

        System.out.println("\n~~~ Unsorted securities ~~~");
        for (int i = 0; i < portfolio.length; i++) {
            System.out.println(portfolio[i]);
        }

        Arrays.sort(portfolio);

        System.out.println("\n~~~ Sorted securities ~~~");
        for (int i = 0; i < portfolio.length; i++) {
            System.out.println(portfolio[i]);
        }
    }
}