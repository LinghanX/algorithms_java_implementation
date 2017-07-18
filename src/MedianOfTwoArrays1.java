import java.util.Arrays;

/**
 * Given two equal length sorted arrays, find the median
 *
 * Note: This is a WIP algorithm, since the corner cases when n is odd/even is
 * not addressed.
 * Created by Linghan on 7/17/17.
 */
public class MedianOfTwoArrays1 {
    public static void main(String[] args) {
        int[] array1 = new int[] { 1, 3, 5, 7, 9, 12};
        int[] array2 = new int[] { 0, 8, 13, 24, 56, 60 };

        int median = findMedian(array1, array2, array1.length);
        System.out.println(median);
    }

    public static int findMedian(int[] array1, int[] array2, int n) {
        if(n <= 2) {
            int[] candidate = merge(array1, array2);
            return candidate[candidate.length/2];
        }
        if(array1[n/2] == array2[n/2])
            return array1[n/2];

        if(array1[n/2] < array2[n/2]) {
            return
                    findMedian(Arrays.copyOfRange(array1, n/2, n),
                            Arrays.copyOfRange(array2, 0, n/2),
                            n/2 );
        }
        else {
            return findMedian(Arrays.copyOfRange(array1, 0, n/2),
                    Arrays.copyOfRange(array2, n/2, n),
                    n/2);
        }
    }

    private static int[] merge(int[] array1, int[] array2) {
        int[] newArray = new int[array1.length + array2.length];
        int i = 0, j = 0;
        while(i+j < newArray.length ) {
            if(i == array1.length) {
                newArray[i+j] = array2[j];
                j++;
            }
            else if( j == array2.length) {
                newArray[i+j] = array1[i];
                i++;
            }
            else if(array1[i] > array2[j]) {
                newArray[i+j] = array2[j];
                j++;
            } else {
                newArray[i+j] = array1[i];
                i++;
            }
        }

        return newArray;
    }
}
