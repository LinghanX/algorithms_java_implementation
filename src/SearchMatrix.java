public class SearchMatrix {
    public static void main(String[] args) {
        SearchMatrix test = new SearchMatrix();
        int[][] sample1 = new int[3][4];
        sample1[0] = new int[] { 1, 3, 5, 7};
        sample1[1] = new int[] {10, 11, 16, 20};
        sample1[2] = new int[] { 23, 30, 34, 50};
        System.out.println(test.searchMatrix(sample1, 3));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int length = matrix.length;
        if(length == 0) return false;
        int targetRow = -1, targetColumn = -1;
        int left = 0, right = length - 1;
        while(left <= right) {
            int mid = (right + left) / 2;
            if(withinArray(matrix[mid], target)) {
                targetRow = mid;
                break;
            } else if (target < matrix[mid][0]) {
                right = mid - 1;
            }  else {
                left = mid + 1;
            }
        }

        if(targetRow == -1) return false;
        targetColumn = binarySearch(matrix[targetRow], target);
        if(targetColumn == -1) return false;
        return true;
    }
    private boolean withinArray(int[] array, int target) {
        if(array.length < 1) return false;
        return array[0] <= target && array[array.length - 1] >= target;
    }

    private int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while(left <= right) {
            int mid = (right + left)/2;
            if(array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
