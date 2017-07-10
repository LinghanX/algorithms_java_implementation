import java.util.Arrays;

/**
 * AN implementation of LCS
 * Created by Linghan on 7/10/17.
 */
public class LongestCommonSubsequence {
    // avoid instantiation
    private LongestCommonSubsequence(){};

    public static void main(String[] args) {
        String S1 = "ABBACDBABBABDDDCBDDDBCCCBAADDBCADBACBADDBBBDDACADBBBDAD";
        String S2 = "BADDABDADDDBAAABDBABDBDDBDBDBBBBACCBCBCCACDCBADCBCADDCC";

        int LCS = findLCS(S1, S2);

        System.out.println("The LCS is " + LCS);
    }

    public static int findLCS(String S1, String S2) {
        int l1 = S1.length(), l2 = S2.length();
        int[][] table =  new int[l1+1][l2+1];

        for(int i = 0; i <= l1; i++) {
            for(int j = 0; j <= l2; j++) {
                if(i == 0 || j == 0) {
                    table[i][j] = 0;
                } else if (S1.charAt(i-1) == S2.charAt(j-1)) {
                    table[i][j] = table[i-1][j-1] + 1;
                } else {
                    table[i][j] = max(table[i-1][j], table[i][j-1]);
                }
            }
        }

        return table[l1][l2];
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }
}
