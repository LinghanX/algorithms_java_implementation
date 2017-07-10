package sorting;

import java.util.Arrays;

public class InsertionSort {
    /**
     * Insertion sort implementation
     *
     * @param unsorted an array to be sorted
     */
    public static void insertion_sort(int[] unsorted) {
        int length = unsorted.length;

        for(int i = 1; i < length; i++) {
            int j = i - 1;
            while(j >= 0) {
                if( unsorted[i] < unsorted[j] ) {
                    int temp = unsorted[i];

                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                    i = j;
                }
                j--;
            }
        }
    }
}

