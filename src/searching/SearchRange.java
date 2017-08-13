package searching;

public class SearchRange {
    public static void main(String[] args) {
        int[] input = new int[] {5, 7, 7, 8, 8, 10};
        System.out.println(findLeft(input, 8));
    }
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1; result[1] = -1;
        if(nums.length < 2) return result;
        if(nums[0] == target) {
            result[0] = 0;
            result[1] = findRight(nums, target);
            return result;
        }
        else if(nums[nums.length - 1] == target) {
            result[1] = nums.length - 1;
            result[0] = findLeft(nums, target);
            return result;
        } else if(nums[0] == target && nums[nums.length - 1] == target) {
            result[1] = nums.length - 1;
            result[0] = 0;
            return result;
        }

        result[0] = findLeft(nums, target);
        result[1] = findRight(nums, target);
        return result;
    }
    private static int findLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target && nums[mid - 1] < target) {
                return mid;
            } else if(nums[mid] > target || nums[mid] == target) {
                right = mid - 1;
            } else if(nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    private int findRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target && nums[mid + 1] > target) {
                return mid;
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] < target || nums[mid] == target) {
                left = mid + 1;
            }
        }
        return -1;
    }
}

