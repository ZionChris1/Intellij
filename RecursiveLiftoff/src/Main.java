public class Main {
    public static void main(String[] args) {
        countDown(60);
    }

    public static void countDown(int number) {
        if(number < 1)
            throw new IllegalArgumentException("Error: Number must be >= 1.");

        System.out.println(number + "...");
        if(number == 1)
            System.out.println("Liftoff!");
        else
            countDown(number - 1);
    }
}