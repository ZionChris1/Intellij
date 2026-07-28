import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ThreadedMainArray {

    public static final int ITERATIONS = 100_000;
    public static final int ARRAY_SIZE = 1000;
    public static final int NUM_THREADS = 6;

    public static void main(String[] args) {
        long startTimeSingleThread, endTimeSingleThread, startTimeMultiThread, endTimeMultiThread;
        ArrayList<Thread> sorters = new ArrayList<Thread>();


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


        for (int i = 0; i < NUM_THREADS; i++) {
            sorters.add(new ArraySorter(ITERATIONS / NUM_THREADS));
        }

        startTimeMultiThread = System.currentTimeMillis();

        for (Thread sorter : sorters) {
            sorter.start();
        }

        for (Thread sorter : sorters) {
            try {
                sorter.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }


        endTimeMultiThread = System.currentTimeMillis();

        System.out.println("Time taken for a single thread: " + (endTimeSingleThread - startTimeSingleThread) + "ms");
        System.out.println("Time taken for multiple threads: " + (endTimeMultiThread - startTimeMultiThread) + "ms");
        System.out.println("Multithreading saved: " + ((endTimeSingleThread - startTimeSingleThread) - (endTimeMultiThread - startTimeMultiThread)) + " ms.");
        System.out.printf("Multithreading saved: %.2f %%.", (((float)((endTimeSingleThread - startTimeSingleThread) - (endTimeMultiThread- startTimeMultiThread)) / (endTimeSingleThread - startTimeSingleThread)) * 100));
    }

    private static class ArraySorter extends Thread {
    private int iterations;

    public ArraySorter(int iterations) {
        this.iterations = iterations;
    }

        public void run() {
            int[] array = new int[ARRAY_SIZE];
            Random r = new Random();
            for(int i = 0; i < iterations; i++) {

                for(int j = 0; j < array.length; j++) {
                    array[j] = r.nextInt();
                }
                Arrays.sort(array);
            }
        }
    }
}
