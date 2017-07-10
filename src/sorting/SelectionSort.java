package sorting;

import java.util.Arrays;

/**
 * Created by Linghan on 7/2/17.
 * Implementation of selection sort
 */
public class SelectionSort {
    /**
     * This function sort array in place, using selection sort
     * The running time is O(n^2) for all cases
     *
     * @param array the array to be sorted
     */
    public static void selectionSort(int[] array) {
        int length = array.length;

        for(int i = 0; i < length - 1; i++) {
            int minIndex = i;

            for(int j = i+1; j < length; j++) {
                if(array[j] <= array[minIndex]) {
                    minIndex = j;
                }
            }

            swapValue(array, i, minIndex);
        }
    }

    /**
     *
     * @param array the input array
     * @param aIndex index of the value to be swapped
     * @param bIndex index of the value swapping
     */
    private static void swapValue(int[] array, int aIndex, int bIndex) {
        int temp = array[aIndex];

        array[aIndex] = array[bIndex];
        array[bIndex] = temp;
    }
}
