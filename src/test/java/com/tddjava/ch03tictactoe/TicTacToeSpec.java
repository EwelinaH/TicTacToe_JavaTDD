package com.tddjava.ch03tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.UnknownHostException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TicTacToeSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private TicTacToe ticTacToe;

    private TicTacToeCollection collection;

    @Before
    public final void before() throws UnknownHostException {
        collection = mock(TicTacToeCollection.class);
        doReturn(true).when(collection).saveMove(any(TickTackToeBean.class));
        doReturn(true).when(collection).drop();
        ticTacToe = new TicTacToe(collection);
    }

    @Test
    public void  whenXOutsideBoardThenRuntimeException(){
        exception.expect(RuntimeException.class);
        ticTacToe.play(5,2);
    }

    @Test
    public void  whenYOutsideBoardThenRuntimeException(){
        exception.expect(RuntimeException.class);
        ticTacToe.play(2,5);
    }

    @Test
    public  void whenOccupiedThenRuntimeException(){
        ticTacToe.play(2, 1);
        exception.expect(RuntimeException.class);
        ticTacToe.play(2,1);
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX(){
        assertEquals('X', ticTacToe.nextPlayer());
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThen0(){
        ticTacToe.play(1,1);
        assertEquals('0', ticTacToe.nextPlayer());
    }

    @Test
    public void whenPlayThenNoWinner(){
        String actual = ticTacToe.play(1,1);
        assertEquals("Brak zwyciezcy", actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner(){
        ticTacToe.play(1,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,1);
        ticTacToe.play(2,2);
        String actual = ticTacToe.play(3,1);
        assertEquals("Wygral X", actual);
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner(){
        ticTacToe.play(2,1);
        ticTacToe.play(1,1);
        ticTacToe.play(3,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,2);
        String actual = ticTacToe.play(1,3);
        assertEquals("Wygral 0", actual);
    }


    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner(){
        ticTacToe.play(1,1);
        ticTacToe.play(1,2);
        ticTacToe.play(2,2);
        ticTacToe.play(1,3);
        String actual = ticTacToe.play(3,3);
        assertEquals("Wygral X", actual);
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner(){
        ticTacToe.play(1,3);
        ticTacToe.play(1,1);
        ticTacToe.play(2,2);
        ticTacToe.play(1,2);
        String actual = ticTacToe.play(3,1);
        assertEquals("Wygral X", actual);
    }

    @Test
    public void whenAllBoxesAreFilledThenDraw(){
        ticTacToe.play(1,1);
        ticTacToe.play(1,2);
        ticTacToe.play(1,3);
        ticTacToe.play(2,1);
        ticTacToe.play(2,3);
        ticTacToe.play(2,2);
        ticTacToe.play(3,1);
        ticTacToe.play(3,3);
        String actual = ticTacToe.play(3,2);
        assertEquals("Wynik remisowy", actual);
    }

    @Test
    public void whenInstantiatedThenSetCollection(){
        assertNotNull(ticTacToe.getTicTacToeCollection());
    }

    @Test
    public void whenPlayThenSaveMoveIsInvoked(){
        TickTackToeBean move = new TickTackToeBean(1,1,3,'X');
        ticTacToe.play(move.getX(), move.getY());
        verify(collection).saveMove(move);
    }

    @Test
    public void whenPlayAndReturnsFalseThenThrowException(){
        doReturn(false).when(collection).saveMove(any(TickTackToeBean.class));
        TickTackToeBean move = new TickTackToeBean(1,1,3,'X');
        exception.expect(RuntimeException.class);
        ticTacToe.play(move.getX(), move.getY());
    }

    @Test
    public void whenPlayInvokedMultipleTimesThenTurnIncreases(){
        TickTackToeBean move1 = new TickTackToeBean(1,1,1,'X');
        ticTacToe.play(move1.getX(), move1.getY());
        verify(collection).saveMove(move1);
        TickTackToeBean move2 = new TickTackToeBean(2,1,2,'0');
        ticTacToe.play(move2.getX(), move2.getY());
        verify(collection).saveMove(move2);
    }

    @Test
    public void whenInstantiatedThenCollectionDrop(){
        verify(collection).drop();
    }

    @Test
    public void whenDropReturnFalseThenRunTimeException(){
        doReturn(false).when(collection).drop();
        exception.expect(RuntimeException.class);
        verify(collection.drop());
    }
}
