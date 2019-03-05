package com.tddjava.ch03tictactoe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TicTacToeSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private TicTacToe ticTacToe;

    private TicTacToeCollection collection;

    @Before
    public final void before() throws UnknownHostException {
        collection = mock(TicTacToeCollection.class);
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
}
