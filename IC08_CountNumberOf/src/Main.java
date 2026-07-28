public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 6, 4, 3, 2};

        System.out.println(countNumberOf(2, array));
        System.out.println(countNumberOf(6, array));
        System.out.println(countNumberOf(5, array));
    }

    public static int countNumberOf(int value, int[] array) {
        return countNumberOfRec(value, array, 0);
    }

    private static int countNumberOfRec(int value, int[] array, int i) {
        //Error case
        if(array.length == 0)
            throw new IllegalArgumentException("Error: Array cannot be empty.");

        //Base case
        if(i == array.length)
            return 0;

        //Recursive case
        else {
            if(array[i] == value)
                return 1 + countNumberOfRec(value, array, i + 1);
            else
                return countNumberOfRec(value, array, i + 1);
        }
    }
}