/**
 * Created by Linghan on 7/29/17.
 */
public class IsValidSudoku {
    public boolean isValidSudoku(char[][] board) {
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

    private boolean isValidColumn(char[][] board, int i) {
        int[] check = new int[9];
        for(int n = 0; n < 9; n++) {
            if(board[n][i] == '.') continue;
            int boardValue = Character.getNumericValue(board[n][i]);
            if(!validValue(boardValue)) return false;
            if(check[boardValue - 1] != 0) return false;
            if(check[boardValue - 1] == 0) check[boardValue - 1]++;
        }

        for(int m : check) {
            if(m > 1) return false;
        }
        return true;
    }

    private boolean isValidRow(char[][] board, int i) {
        int[] check = new int[9];

        for(int n = 0; n < 9; n++) {
            if(board[n][i] == '.') continue;
            int boardValue = Character.getNumericValue(board[i][n]);
            if(!validValue(boardValue)) return false;
            if(check[boardValue - 1] != 0) return false;
            if(check[boardValue - 1] == 0) check[boardValue - 1]++;
        }

        for(int m : check) {
            if(m > 1) return false;
        }
        return true;
    }

    private boolean validValue(int i) {
        if(i > 9 || i < 1) return false;
        return true;
    }

    private boolean isValidBox(char[][] board, int i, int j) {
        int[] check = new int[9];
        for(int n = 0; n < 3; n++) {
            for(int m = 0; m < 3; m++) {
                if(board[n][i] == '.') continue;
                int boardValue = Character.getNumericValue(board[i+n][j+m]);
                if(!validValue(boardValue)) return false;
                if(check[boardValue - 1] != 0) return false;
                if(check[boardValue - 1] == 0) check[boardValue - 1]++;
            }
        }

        for(int m : check) {
            if(m > 1) return false;
        }

        return true;
    }
}
