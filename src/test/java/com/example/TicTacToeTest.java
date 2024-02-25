package com.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import org.junit.Rule;

/**
 * Unit test for simple App.
 */
public class TicTacToeTest {
    TicTacToe ticTacToe;

    @Before
    public void setUp() {
        ticTacToe = new TicTacToe();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenXOutsideBoardThanRuntimeException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("X is outside board");
        ticTacToe.play(5, 2);
    }

    @Test
    public void whenYOutsideBoardThanRuntimeException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Y is outside board");
        ticTacToe.play(2, 5);
    }

    @Test
    public void whenXYInsideBoardThanNothing() {
        ticTacToe.play(1, 1);
    }

    @Test
    public void whenOccupiedThanRuntimeException() {
        ticTacToe.play(2, 1);
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 1);
    }

    @Test
    public void checkFirstPlayerIsX() {
        assertEquals((Character) 'X', ticTacToe.getCurrentPlayer());
    }

    @Test
    public void checkNextPlayerIsO() {
        ticTacToe.play(1, 1);
        assertEquals((Character) 'O', ticTacToe.getCurrentPlayer());
    }

    @Test
    public void checkNextPlayerIsX() {
        ticTacToe.play(1, 1);
        ticTacToe.play(2, 1);
        assertEquals((Character) 'X', ticTacToe.getCurrentPlayer());
    }

    @Test
    public void checkIsBoardFull() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        ticTacToe.play(3, 2);
        assertTrue(ticTacToe.isBoardFull());
    }

    @Test
    public void checkIsGameOver() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        assertTrue(ticTacToe.isGameOver());
    }

    @Test
    public void checkHorizontalWin() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 2);
        assertEquals((Character) 'X', ticTacToe.play(3, 1)[0][0]);
        assertEquals((Character) 'X', ticTacToe.getWinner());
    }

    @Test
    public void checkVerticalWin() {
        ticTacToe.play(1, 1);
        ticTacToe.play(2, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 2);
        assertEquals((Character) 'X', ticTacToe.play(1, 3)[0][0]);
        assertEquals((Character) 'X', ticTacToe.getWinner());
    }

    @Test
    public void checkDiagonalWin() {
        ticTacToe.play(1, 1);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        assertEquals((Character) 'X', ticTacToe.play(3, 3)[0][0]);
        assertEquals((Character) 'X', ticTacToe.getWinner());
    }

    @Test
    public void checkNoWinner() {
        ticTacToe.play(1, 1);
        ticTacToe.play(2, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        assertFalse(ticTacToe.isGameOver());
        assertNull(ticTacToe.getWinner());
    }

    @Test
    public void checkNoWinnerFullBoard() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        ticTacToe.play(3, 2);
        assertTrue(ticTacToe.isBoardFull());
        assertTrue(ticTacToe.isGameOver());
        assertEquals((Character) '0', ticTacToe.getWinner());
    }

    @Test
    public void checkResetGame() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        ticTacToe.play(3, 2);
        ticTacToe.resetGame();
        assertFalse(ticTacToe.isGameOver());
        assertFalse(ticTacToe.isBoardFull());
        assertNull(ticTacToe.getWinner());
        assertEquals((Character) 'X', ticTacToe.getCurrentPlayer());

    }

    @Test
    public void checkResetGameBoard() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        ticTacToe.play(3, 2);
        ticTacToe.resetGame();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals((Character) '0', ticTacToe.getBoard()[i][j]);
            }
        }
    }

}
