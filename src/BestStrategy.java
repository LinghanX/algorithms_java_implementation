/**
 * Best strategy problem
 * Created by Linghan on 7/18/17.
 */
public class BestStrategy {
    public static void main(String[] args) {
        int[] input = new int[] { 1, 3, 5, 2, 12, 3, 5, 7, 9, 4 };
        maxScore(input, 10);
    }

    public static int maxScore(int[] input, int n) {
        int length = input.length;

        int table[][] = new int[length+1][length+1];
        for(int i = 1; i < length+1; i++) {
            for(int j = 1; j< length+1; j++) {
                if(j < i) continue; // we only needs when i < j
                if(j - i == 1) {
                    table[i][j] = max(input[i-1], input[j-1]);
                }

                if(i==j) {
                    table[i][j] = input[i-1];
                }
            }
        }

        int counter = 2;
        while(counter < 10) {
            for(int l = 1; l < length+1; l++) {
                for(int k = 1; k < length+1; k++) {
                    if(k - l <= 1) continue;
                    if(k - l == counter) {
                        table[l][k] = max(
                                input[l-1] + sum(input, l, k) - table[l+1][k],
                                input[k-1] + sum(input, l-1, k-1) - table[l][k-1]);
                    }
                }
            }

            counter++;
        }

        print2DTable(table);

        return table[0][n-1];
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }

    private static int sum(int[] array, int a, int b) {
        int sum = 0;
        for(int i = a; i < b; i++) {
            sum += array[i];
        }
        return sum;
    }

    private static void print2DTable(int[][] input) {
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length; j++) {
                System.out.printf("%d ", input[i][j]);
            }

            System.out.println("");
        }
    }
}
