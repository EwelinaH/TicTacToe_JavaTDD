package com.tddjava.ch03tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class TicTacToeCollectionSpec {

    TicTacToeCollection collection;

    @Before
    public void before() throws UnknownHostException{
        collection = new TicTacToeCollection();
    }

    @Test
    public void whenInstantiatedThenMongoHasDBNameTicTacToe() {
        assertEquals("tic-tac-toe", collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() {
        assertEquals("game", collection.getMongoCollection().getName());
    }
}
