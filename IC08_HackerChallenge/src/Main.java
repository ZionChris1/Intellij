public class Main {
    public static void main(String[] args) {
        printNumsAscending(5);
        printNumsAscending(12);
    }

    public static void printNumsAscending(int n) {
        printNumsAscendingRec(1, n);
    }

    private static void printNumsAscendingRec(int n, int max) {
        //Error case
        if(n < 1)
            throw new IllegalArgumentException("Error: n must be >= 1");

        //Base case
        else if(n == max)
            System.out.println(n);

        //Recursive case
        else {
            System.out.print(n + ", ");
            printNumsAscendingRec(n + 1, max);
        }
    }
}