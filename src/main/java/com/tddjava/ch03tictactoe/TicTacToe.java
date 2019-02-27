package com.tddjava.ch03tictactoe;

public class TicTacToe {
    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
    // table 3x3 with empty places

    private char lastPlayer = '\0';
    private static final int SIZE = 3;

    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(x, y, lastPlayer);

        if (isWin()){
            return "Wygral " + lastPlayer;
        }
        return "Brak zwyciezcy";
    }

    private boolean isWin(){
        for (int i = 0; i < SIZE; i++){
            if(board[0][i] + board[1][i] + board[2][i] == (lastPlayer * SIZE)){
                return true;
            }
        }
        return false;
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw
                    new RuntimeException("Wartosc X wykracza poza plansze");
        }
    }

    private void setBox(int x, int y, char lastPlayer) {
        if (board[x - 1][y - 1] != '\0'){
            throw
                    new RuntimeException("Pole jest juz zajete!");
    } else {
            board[x - 1][y - 1] = lastPlayer;
        }
    }

    public char nextPlayer(){
        if(lastPlayer == 'X') {
            return '0';
        }
        return 'X';
    }
}
