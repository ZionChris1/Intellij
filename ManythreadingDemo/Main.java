import java.io.PrintStream;
import java.lang.reflect.Method;

public class Main {
    public static int[] a, b, c;
    public static void main(String[] args) throws InterruptedException, NoSuchMethodException {
        a = new int[10000000];
        b = new int[10000000];
        c = new int[10000000];
        for (int i = 0; i < a.length; i++) {
            a[i] = 1;
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = 2;
        }
        Thread[] threads = new Thread[8];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Worker(i * (a.length / threads.length), (i + 1) * (a.length / threads.length));
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("The set of operations took " + end + " milliseconds");
    }

    static class Worker implements Runnable extends Application {
        private int start, end;
        public Worker(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }
        public void run() {
            for (int i = start; i < end; i++) {
                c[i] = a[i] + b[i];
                System.out.println("Thread " + Thread.currentThread().threadId() + " completed operation " + i);
            }
        }
    }
}