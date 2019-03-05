package com.tddjava.ch03tictactoe;

import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TicTacToeCollectionSpec {

    TicTacToeCollection collection;
    TickTackToeBean bean;
    MongoCollection mongoCollection;

    @Before
    public void before() throws UnknownHostException{
        collection = spy(new TicTacToeCollection());
        bean = new TickTackToeBean(3,2,1,'Y');
        mongoCollection = mock(MongoCollection.class);

    }

    @Test
    public void whenInstantiatedThenMongoHasDBNameTicTacToe() {
        assertEquals("tic-tac-toe", collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() {
        assertEquals("game", collection.getMongoCollection().getName());
    }

    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave(){
        MongoCollection mongoCollection = mock(MongoCollection.class);
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.saveMove(bean);
        verify(mongoCollection, times(1)).save(bean);
    }


}
