public class Main {
    public static void main(String[] args) {
        funkyHeader(1);
        System.out.println();
        funkyHeader(2);
        System.out.println();
        funkyHeader(3);
        System.out.println();
        funkyHeader(4);
        System.out.println();
        funkyHeader(5);
        System.out.println();
        funkyHeader(6);
        System.out.println();
        funkyHeader(7);
        System.out.println();
        funkyHeader(8);
        System.out.println();
    }

    public static void funkyHeader(int n) {
        //Error Case
        if(n < 1)
            throw new IllegalArgumentException("Error: n must be >= 1");

        //Base case
        else if(n == 1)  //odd numbers
            System.out.print("*");
        else if(n == 2) //even numbers
            System.out.print("**");

        //Recursive case
        else {
            System.out.print("<");
            funkyHeader(n - 2);
            System.out.print(">");
        }
    }
}