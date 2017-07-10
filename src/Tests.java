import java.util.Arrays;

import static sorting.InsertionSort.insertion_sort;
import static sorting.MergeSort.mergeSort;
import static sorting.QuickSort.quickSort;
import static sorting.SelectionSort.selectionSort;

/**
 * This is the test class for algorithms
 *
 * Created by Linghan on 7/2/17.
 */
public class Tests {
    public static void main(String[] args) {
        testSortings();
        testSearchings();
    }

    private static void testSortings() {
        int[] unsorted = new int[] { 21, 2, 32, 1, 4, 6, 7, 23, 1, 55, 21 };

        System.out.println("The original array is: "
                + Arrays.toString(unsorted));
        System.out.println("------------------------------------------------");

        testInsertion(unsorted);
        testSelectionArray(unsorted);
        testMerge(unsorted);
        testQuick(unsorted);
    }

    private static void testSearchings() {
        // place holder
    }

    private static void testQuick(int[] array) {
        System.out.println("Testing quick sort");
        int[] localCopy = array.clone();
        quickSort(localCopy);

        System.out.println("The output array is: " + Arrays.toString(localCopy));
    }

    private static void testMerge(int[] array) {
        System.out.println("Testing merge sort");
        int[] localCopy = array.clone();
        mergeSort(localCopy);

        System.out.println("The output array is: " + Arrays.toString(localCopy));

    }

    private static void testInsertion(int[] array) {
        System.out.println("Testing insertion sort");
        int[] localCopy = array.clone();
        insertion_sort(localCopy);

        System.out.println("The output array is: " + Arrays.toString(localCopy));
    }

    private static void testSelectionArray(int[] array) {
        System.out.println("Testing insertion sort");
        int[] localCopy = array.clone();
        selectionSort(localCopy);

        System.out.println("The output array is: " + Arrays.toString(localCopy));
    }
}
