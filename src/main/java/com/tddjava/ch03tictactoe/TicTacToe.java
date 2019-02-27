package com.tddjava.ch03tictactoe;

public class TicTacToe {
    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
    // table 3x3 with empty places

    private char lastPlayer = '\0';

    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        setBox(x, y);
        lastPlayer = nextPlayer();

        for(int index = 0; index < 3; index++){
            if (board[0][index] == lastPlayer && board[1][index] == lastPlayer && board[2][index] == lastPlayer){
                return "Wygral " + lastPlayer;
            }
        }
        return "Brak zwyciezcy";
    }
    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw
                    new RuntimeException("Wartosc X wykracza poza plansze");
        }
    }

    private void setBox(int x, int y) {
        if (board[x - 1][y - 1] != '\0'){
            throw
                    new RuntimeException("Pole jest juz zajete!");
    } else {
            board[x - 1][y - 1] = 'X';
        }
    }

    public char nextPlayer(){
        if(lastPlayer == 'X') {
            return '0';
        }
        return 'X';
    }
}
