public class Main {
    public static void main(String[] args) {
        tooManyTwos(80);
        System.out.println();
        tooManyTwos(68);
        System.out.println();
        tooManyTwos(96);
        System.out.println();
        tooManyTwos(32);
        System.out.println();
        tooManyTwos(1);
        System.out.println();
        tooManyTwos(2);
        System.out.println();
    }

    public static void tooManyTwos(int n) {
        tooManyTwosRec(n, 0);
    }

    public static void tooManyTwosRec(int n, int count) {
        //Error case
        if(n < 1)
            throw new IllegalArgumentException("Error: n must be >= 1");

        //Base case
        else if (n % 2 != 0)
            System.out.print(n);

        //Recursive case
        else
            if(count % 2 == 0) {
                System.out.print(2 + " * ");
                tooManyTwosRec(n / 2, count + 1);
            } else {
                tooManyTwosRec(n / 2,count + 1);
                System.out.print(" * " + 2);
            }
    }
}