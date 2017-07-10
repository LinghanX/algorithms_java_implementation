import java.util.Arrays;

/**
 * Two sum implementation
 * input an array of numbers, note that they could be duplicates; and a target
 *        number that is the sum of two numbers in the given array.
 * output the indices of the two numbers
 * Created by Linghan on 7/8/17.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] targetArray = new int[] { 1, 4, 2, 7, 10, 11 };
        int target = 18;

        System.out.println(Arrays.toString(targetArray)
                + "looking for " + target);

        int[] index = new int[] { -1, -1 };
        twoSum(targetArray, target, index);

        if(index[0] == -1) {
            System.out.println("Not found!");
            return;
        }

        System.out.println(Arrays.toString(index));
    }

    private static void twoSum(int[] targetArray, int target, int[] result) {
        int[] array = targetArray.clone();
        Arrays.sort(array);
        System.out.println("Array target after sort is "
                + Arrays.toString(array));

        int i = 0, j = array.length - 1;

        while(i != j) {
            int sum = array[i] + array[j];
            if(sum == target) {
                result[0] = i;
                result[1] = j;
                return;
            }
            else if (sum < target) { i++; }
            else { j++; }
        }

        System.out.println("Not found!");
    }
}
