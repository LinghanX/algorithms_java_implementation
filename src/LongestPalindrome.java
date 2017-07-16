/**
 * Given a string, find the longest palindrome substring
 *
 * Created by Linghan on 7/16/17.
 */
public class LongestPalindrome {
    private int low, maxLength;

    public static void main(String[] args) {
        String input = "lskislaoijslahhaskhlheloowsolawsoaksl";
        LongestPalindrome palindrome = new LongestPalindrome();

        System.out.println(palindrome.longestPalindrome(input));
    }

    public String longestPalindrome(String s) {
        if(s.length() < 2) return s;

        low = 0; maxLength = 0;
        for(int i = 0; i < s.length() - 1; i++) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i+1);
        }

        return s.substring(low, low+maxLength);
    }

    public void extendPalindrome(String s, int left, int right) {
        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; right++;
        }

        if(right-left - 1 > maxLength) {
            low = left + 1;
            maxLength = right - left -1 ;
        }
    }
}
