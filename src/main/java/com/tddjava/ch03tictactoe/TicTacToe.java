package com.tddjava.ch03tictactoe;

import java.net.UnknownHostException;

public class TicTacToe {
    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
    // table 3x3 with empty places

    private char lastPlayer = '\0';
    private static final int SIZE = 3;

    private TicTacToeCollection ticTacToeCollection;

    private int turn = 0;

    protected TicTacToeCollection getTicTacToeCollection(){
        return ticTacToeCollection;
    }

    public TicTacToe() throws UnknownHostException {
        this (new TicTacToeCollection());
    }

    protected TicTacToe(TicTacToeCollection collection){
        ticTacToeCollection = collection;
        if (!ticTacToeCollection.drop()){
            throw new RuntimeException("Nieudane usuniecie z bazy");
        }

    }

    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(new TickTackToeBean(++turn, x, y, lastPlayer));

        if (isWin(x, y)){
            return "Wygral " + lastPlayer;
        } else if (isDraw()){
            return "Wynik remisowy";
        }else {
            return "Brak zwyciezcy";
        }
    }

    private boolean isDraw(){
        for (int x = 0; x < SIZE; x++ ){
            for (int y = 0; y < SIZE; y++){
                if (board[x][y] == '\0'){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWin(int x, int y){
        int playerTotal = lastPlayer * 3;
        char horizontal, vertical, diagonal1, diagonal2;
        horizontal = vertical= diagonal1 = diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            horizontal += board[i][y - 1];
            vertical += board[x - 1][i];
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
        }
        if (horizontal == playerTotal || vertical ==playerTotal
                || diagonal1 ==playerTotal || diagonal2 == playerTotal){
            return true;
        }
        return false;
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw
                    new RuntimeException("Wartosc X wykracza poza plansze");
        }
    }

    private void setBox(TickTackToeBean bean) {
        if (board[bean.getX() - 1][bean.getY() - 1] != '\0'){
            throw
                    new RuntimeException("Pole jest juz zajete!");
    } else {
            board[bean.getX() - 1][bean.getY() - 1] = lastPlayer;
            if (!getTicTacToeCollection().saveMove(bean)){
                throw new RuntimeException(
                        "Nieudany zapis w bazie danych");
            }
        }
    }

    public char nextPlayer(){
        if(lastPlayer == 'X') {
            return '0';
        }
        return 'X';
    }
}
