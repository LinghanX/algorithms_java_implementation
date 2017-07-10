package searching;

/**
 * This class implements linear search for integers
 *
 * @author Linghan Xing
 */
public class LinearSearch {
    public static void main(String[] args) {
        int[] unsortedArray = new int[] { 3, 12, 1, 54, 2, 1, 6, 23, 11};

        int index = linearSearch(unsortedArray, 23);

        if(index == -1) {
            System.out.println("item not found");
        } else {
            System.out.println("item\'s index is " + index);
        }
    }

    /**
     * This linear search assumes finds the First occurrence of the target
     * in the input array
     *
     * @param inputArray the array to be searched
     * @param target the item to search for
     * @return the index of first occurrence, -1 if the item is not found
     */
    public static int linearSearch(int[] inputArray, int target) {
        int length = inputArray.length;

        for(int i = 0; i < length; i++) {
            if(inputArray[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
