import java.util.Random;
import java.util.Arrays;

public class Main {

    /**
     * This function takes last element as pivot, places the pivot element at its
     * correct position in sorted array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot.
     *
     * @param array The array of data to sort
     * @param first The first index of the array
     * @param last  The last index of the array
     * @return The index at which the pivot is placed
     */
    public static int partition(int array[], int first, int last) {
        // Initialize the pivot as the last element in the array
        int pivot = array[last];
        // Initialize i to start out as one less than first
        int i = first - 1;

        int temp;

        // Loop through array from low to high-1
        for(int j = first; j < last; j++) {
            // If current element is smaller than the pivot
            if(array[j] < pivot) {
                // a) Increment i
                i++;
                // b) Swap array[i] and array[j]
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                // Else, do nothing
            }

        }

        // After loop, swap the array[high] (pivot) with array[i+1]
        // swap array[i+1] and array[high] (or pivot)
        temp = array[i+1];
        array[i+1] = array[last];
        array[last] = temp;


        // Return the index of where the pivot was placed (i+1)
        return i + 1;
    }


    /**
     * The main function that implements QuickSort() array[] --> Array to be sorted,
     * first --> Starting index, last --> Ending index
     * @param array The array of data to be sorted
     * @param first The first index in the array
     * @param last The last index in the array
     */
    public static void quickSort(int array[], int first, int last) {
        //Error Case
        if(array.length == 0)
            throw new IllegalArgumentException("Array cannot be empty.");

        //Base case
        // If first >= last, return (done!)
        if(first >= last)
            return;

        //Recursive case
        // Otherwise, call partition to find the pivot
        int index = partition(array, first, last);
        // Call quickSort on left part of array (less than pivot)
        quickSort(array, first, index - 1);
        // Call quickSort on right part of array (greater than pivot
        quickSort(array, index + 1, last);
    }

    public static void main(String[] args) {
        //Create new array
        int array[] = new int[50];
        Random rng = new Random();

        //Fill array with random data
        for(int i = 0; i < array.length; i++) {
            array[i] = rng.nextInt(100);
        }

        //Print unsorted array
        System.out.println("~~~Unsorted Array~~~");
        System.out.println(Arrays.toString(array));

        //Use quickSort
        quickSort(array, 0, array.length - 1);

        //Print sorted array
        System.out.println("~~~Sorted Array~~~");
        System.out.println(Arrays.toString(array));
    }

}
