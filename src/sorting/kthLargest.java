package sorting;

/**
 * Given an array of Integers and an index k, return the Kth minimum element
 * Created by Linghan on 7/15/17.
 */
public class kthLargest {
    public static void main(String[] args) {
        int[] input = new int[] { 2, 5, 12, 3, 1, 5, 2, 64, 6, 8, 9};

        System.out.print("The 4th biggest input is" +
                findKthLargest(input,0, input.length - 1, 4));
    }

    public static int findKthLargest(int[] nums,int low, int high, int k) {
        int position = partition(nums, low, high);
        if(position + low == nums.length - k) return nums[position];
        if(position + low < nums.length - k) findKthLargest(nums, low, position + low - 1, k );
        if(position + low > nums.length - k) findKthLargest(nums, position+ low + 1, high, k);

        return -1;
    }

    private static int partition(int[] array, int low, int high) {
        int highIndex = high;
        int pivot = array[low];

        while(low < high) {
            if(array[low] > pivot) {
                low++;
                continue;
            }
            if(array[high] < pivot) {
                high--;
                continue;
            }
            if(array[low] < pivot && array[high] > pivot) {
                swap(array, low, high);
            }
        }

        swap(array, highIndex, high);

        return high;
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
