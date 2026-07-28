import java.util.Random;
class Main {
    public static final int size = 100000;
    public static void main(String[] args) {
        Random random = new Random();
        int[] keys = new int[size];
        int[] values = new int[size];
        HashMapTree<Integer, Integer> map = new HashMapTree(1000);

        for(int i = 0; i < size; i++) {
            keys[i] = random.nextInt();
            values[i] = random.nextInt();
        }
        for(int i = 0; i < size; i++)
            map.add(keys[i], values[i]);
        long stime = System.nanoTime();
        for(int i = 0; i < size; i++) {
            if(map.get(keys[i]) != values[i])
                System.err.println("Data error.");
        }
        long etime = System.nanoTime();
        System.out.println("" + ((etime - stime) / 1_000_000) + " miliseconds taken");
        System.out.println("All values retrieved.");
    }
}