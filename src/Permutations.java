import java.util.*;

/**
 * Given a list of numbers, ask for all permutations
 * Created by Linghan on 7/13/17.
 */
public class Permutations {
    public static void main(String[] args) {
        int[] input = new int[] { 6, 2, 2, 1, 5, 4, 6, 8, 12, 5};
        int[] lightInput = new int[] { 1, 2, 2, 4};

        printAllPermutation(lightInput);
    }

    public static void printAllPermutation(int[] input) {
        Map<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
        for(int i: input) {
            if(frequencies.containsKey(i)) {
                int val = frequencies.get(i);
                val++;
                frequencies.put(i, val);
            } else {
                frequencies.put(i, 1);
            }
        }

        int[] items = new int[frequencies.size()];
        int[] count = new int[frequencies.size()];
        int index = 0;
        for(Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            items[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }

        int[] permutation = new int[input.length];
        List<int[]> result = new ArrayList<int[]>();

        backtrack(items, count, permutation, result, 0);

        for(int[] p : result) {
            System.out.println(Arrays.toString(p));
        }
    }

    private static void backtrack( int[] items,
                                   int[] count,
                                   int[] permutation,
                                   List<int[]> result,
                                   int level) {
        if(level == permutation.length) {
            int[] newCopy = new int[permutation.length];
            for(int s = 0; s< permutation.length; s++) {
                newCopy[s] = permutation[s];
            }
            result.add(newCopy);
            return;
        }

        for(int i = 0; i < items.length; i++) {
            if(count[i] == 0) continue;

            permutation[level] = items[i];
            count[i]--;
            backtrack(items, count, permutation, result, level+1);
            count[i]++;
        }
    }
}
