package sorting;

/**
 * Implementation of QuickSort for sorting Integers
 *
 * Created by Linghan on 7/6/17.
 */
public class QuickSort {
    public static void quickSort(int[] array) {
        int low = 0;
        int high = array.length - 1;

        sort(array, low, high);
    }

    private static void sort(int[] array, int low, int high) {
        if(high <= low) return;

        int j = partition(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }

    private static int partition(int[] array, int low, int high) {
        int i = low;
        int j = high + 1;

        int partitionValue = array[low];

        while(true) {
            while(array[++i] < partitionValue) if(i == high) break;
            while(partitionValue < array[--j]) if(j == low) break;
            if(i >= j) break;
            exchange(array, i, j);
        }

        exchange(array, low, j);
        return j;
    }

    private static void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
