package com.tddjava.ch03tictactoe;

public class TicTacToe {
    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
    // table 3x3 with empty places


    public void play(int x, int y){
        if(x < 1 || x > 3){
            throw
                    new RuntimeException("Wartosc X wykracza poza plansze");
        } else if (y < 1 || y > 3){
            throw
                    new RuntimeException("Wartosc Y wykracza poza plansze");
        }
        if (board[x - 1][y - 1] != '\0'){
            throw
                    new RuntimeException("Pole jest juz zajete!");
        } else {
            board[x - 1][y - 1] = 'X';
        }
    }
}
