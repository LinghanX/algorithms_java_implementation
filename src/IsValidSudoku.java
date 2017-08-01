/**
 * Created by Linghan on 7/29/17.
 */
public class IsValidSudoku {
    public static void main(String[] args) {
        char[][] board = new char[9][9];
        char[][] board2 = new char[9][9];

        board[0] = ".872..3..".toCharArray();
        board[1] = "654......".toCharArray();
        board[2] = "321......".toCharArray();
        board[3] = "4..5..6..".toCharArray();
        board[4] = ".........".toCharArray();
        board[5] = ".........".toCharArray();
        board[6] = "7..8..9..".toCharArray();
        board[7] = board[8] = board[5];

        board2[0] = "..5......".toCharArray();
        board2[1] = "....14...".toCharArray();
        board2[2] = "..6......".toCharArray();
        board2[3] = "...5.....".toCharArray();
        board2[4] = "..9..2...".toCharArray();
        board2[5] = "2......3.".toCharArray();
        board2[6] = "...3.....".toCharArray();
        board2[7] = "54....27.".toCharArray();
        board2[8] = "...42.6..".toCharArray();
        //System.out.println(isValidSudoku(board));
        System.out.println(isValidSudoku(board2));
    }
    public static boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!isValidBox(board, i*3, j*3)) return false;
            }
        }

        for(int i = 0; i < 9; i++) {
            if(!isValidRow(board, i)) return false;
            if(!isValidColumn(board, i)) return false;
        }

        return true;
    }

    private static boolean isValidColumn(char[][] board, int i) {
        boolean[] check = new boolean[9];
        for(int n = 0; n < 9; n++) {
            if(board[n][i] == '.') continue;
            int boardValue = Character.getNumericValue(board[n][i]);
            if(!validValue(boardValue)) return false;
            if(check[boardValue - 1]) {
                return false;
            } else {
                check[boardValue - 1] = true;
            }
        }
        return true;
    }

    private static boolean isValidRow(char[][] board, int i) {
        boolean[] check = new boolean[9];

        for(int n = 0; n < 9; n++) {
            if(board[i][n] == '.') continue;
            int boardValue = Character.getNumericValue(board[i][n]);
            if(!validValue(boardValue)) return false;
            if(check[boardValue - 1]) {
                return false;
            } else {
                check[boardValue - 1] = true;
            }
        }

        return true;
    }

    private static boolean validValue(int i) {
        if(i > 9 || i < 1) return false;
        return true;
    }

    private static boolean isValidBox(char[][] board, int i, int j) {
        boolean[] check = new boolean[9];
        for(int n = 0; n < 3; n++) {
            for(int m = 0; m < 3; m++) {
                if(board[i+n][j+m] == '.') continue;
                int boardValue = board[i+n][j+m] - '0';
                if(!validValue(boardValue)) return false;
                if(check[boardValue - 1]) {
                    return false ;
                } else {
                    check[boardValue - 1] = true;
                }
            }
        }
        return true;
    }
}
