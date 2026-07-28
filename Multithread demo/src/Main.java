import java.util.Arrays;
import java.util.Random;

public class Main {

    public static final int ITERATIONS = 100_000;
    public static final int ARRAY_SIZE = 1000;

    public static void main(String[] args) {
        long startTimeSingleThread, endTimeSingleThread, startTimeMultiThread, endTimeMultiThread;

        startTimeSingleThread = System.currentTimeMillis();
        int[] array = new int[ARRAY_SIZE];
        Random r = new Random();
        for(int i = 0; i < ITERATIONS; i++) {

            for(int j = 0; j < array.length; j++) {
                array[j] = r.nextInt();
            }
            Arrays.sort(array);
        }

        endTimeSingleThread = System.currentTimeMillis();

        //Create new threads here


        startTimeMultiThread = System.currentTimeMillis();
        //Start and join the threads here

        endTimeMultiThread = System.currentTimeMillis();

        System.out.println("Time taken for a single thread: " + (endTimeSingleThread - startTimeSingleThread) + "ms");
        //Uncomment the lines below after adding the multithreading code
        //System.out.println("Time taken for multiple threads: " + (endTimeMultiThread - startTimeMultiThread) + "ms");
        //System.out.println("Multithreading saved: " + ((endTimeSingleThread - startTimeSingleThread) - (endTimeMultiThread - startTimeMultiThread)) + " ms.");
        //System.out.printf("Multithreading saved: %.2f %%.", (((float)((endTimeSingleThread - startTimeSingleThread) - (endTimeMultiThread- startTimeMultiThread)) / (endTimeSingleThread - startTimeSingleThread)) * 100));
    }
}