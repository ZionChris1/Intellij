public class Main {
    public static void main(String[] args) {
        System.out.println(howManyDigitsMatch(38, 34));
        System.out.println(howManyDigitsMatch(5, 5552));
        System.out.println(howManyDigitsMatch(892, 892));
        System.out.println(howManyDigitsMatch(298892, 7892));
        System.out.println(howManyDigitsMatch(380, 0));
        System.out.println(howManyDigitsMatch(123456, 654321));
        System.out.println(howManyDigitsMatch(1234567, 67));
    }

    public static int howManyDigitsMatch(int a, int b) {
        //Error case
        if(a < 0 || b < 0)
            throw new IllegalArgumentException("Error: both numbers must be >= 0");

        //Base case
        else if(a < 10 || b < 10)
            if(a % 10 == b % 10)
                return 1;
            else
                return 0;

        //Recursive case
        else
            if(a % 10 == b % 10)
                return 1 + howManyDigitsMatch(a / 10, b / 10);
            else
                return howManyDigitsMatch(a / 10, b / 10);
    }
}