public class Main {
    public static void main(String[] args) {
        printNums(5);
        printNums(12);
    }

    public static void printNums(int n) {
        //Error case
        if(n < 1)
            throw new IllegalArgumentException("Error: n must be >= 1");

        //Base case
        else if(n == 1)
            System.out.println(n);

        //Recursive case
        else {
            System.out.print(n + ", ");
            printNums(n - 1);
        }
    }
}