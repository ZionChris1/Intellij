public class Main {
    public static void main(String[] args) {
        System.out.println(productOfEvens(1));
        System.out.println(productOfEvens(2));
        System.out.println(productOfEvens(3));
        System.out.println(productOfEvens(4));
    }

    public static int productOfEvens(int n) {
        //Error case
        if(n < 1)
             throw new IllegalArgumentException("Error: n must be >= 1.");

        //Base case
        else if(n == 1)
            return 2;

        //Recursive case
        else
            return productOfEvens(n - 1) * 2 * n;
    }
}