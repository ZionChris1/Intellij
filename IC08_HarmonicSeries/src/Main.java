public class Main {
    public static void main(String[] args) {
        System.out.println(harmonicSeries(2));
        System.out.println(harmonicSeries(16));
    }

    public static double harmonicSeries(int n) {
        //Error case
        if(n < 0)
            throw new IllegalArgumentException("Error: n must be >= 0");

        //Base case
        else if(n == 0)
            return 0.0;

        //Recursive case
        else
            return (1.0/n) + harmonicSeries(n - 1);
    }
}