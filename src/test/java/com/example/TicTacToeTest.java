package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Unit test for simple App.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class TicTacToeTest {

    private TicTacToe ticTacToe;

    @BeforeEach
    public void setUp() {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXOutsideBoardThanRuntimeException() {
        Exception exception = assertThrows(RuntimeException.class, () -> ticTacToe.play(5, 2));
        assertEquals("X is outside board", exception.getMessage());
    }

    @Test
    public void whenYOutsideBoardThanRuntimeException() {
        Exception exception = assertThrows(RuntimeException.class, () -> ticTacToe.play(2, 5));
        assertEquals("Y is outside board", exception.getMessage());
    }

    @Test
    public void whenXYInsideBoardThanNothing() {
        assertDoesNotThrow(() -> ticTacToe.play(1, 1));
    }

    @Test
    public void whenOccupiedThanRuntimeException() {
        ticTacToe.play(2, 1);
        assertThrows(RuntimeException.class, () -> ticTacToe.play(2, 1));
    }

    @Test
    public void checkFirstPlayerIsX() {
        assertEquals('X', ticTacToe.getCurrentPlayer());
    }

    @Test
    public void checkNextPlayerIsO() {
        ticTacToe.play(1, 1);
        assertEquals('O', ticTacToe.getCurrentPlayer());
    }

    @Test
    public void checkNextPlayerIsX() {
        ticTacToe.play(1, 1);
        ticTacToe.play(2, 1);
        assertEquals('X', ticTacToe.getCurrentPlayer());
    }

    @Test
    public void checkIsBoardFull() {
        playMoves(ticTacToe, 1, 1, 1, 2, 1, 3, 2, 1, 2, 3, 2, 2, 3, 1, 3, 3, 3, 2);
        assertTrue(ticTacToe.isBoardFull());
    }

    @Test
    public void checkIsGameOver() {
        playMoves(ticTacToe, 1, 1, 1, 2, 2, 1, 2, 2, 3, 1);
        assertTrue(ticTacToe.isGameOver());
    }

    @Test
    public void checkHorizontalWin() {
        playMoves(ticTacToe, 1, 1, 1, 2, 2, 1, 2, 2, 3, 1);
        assertEquals('X', ticTacToe.getWinner());
    }

    @Test
    public void checkVerticalWin() {
        playMoves(ticTacToe, 1, 1, 2, 1, 1, 2, 2, 2, 1, 3);
        assertEquals('X', ticTacToe.getWinner());
    }

    @Test
    public void checkDiagonalWin() {
        playMoves(ticTacToe, 1, 1, 2, 1, 2, 2, 3, 1, 3, 3);
        assertEquals('X', ticTacToe.getWinner());
    }

    @Test
    public void checkNoWinner() {
        playMoves(ticTacToe, 1, 1, 2, 1, 1, 2, 2, 2, 3, 1);
        assertFalse(ticTacToe.isGameOver());
        assertNull(ticTacToe.getWinner());
    }

    @Test
    public void checkNoWinnerFullBoard() {
        playMoves(ticTacToe, 1, 1, 1, 2, 1, 3, 2, 1, 2, 3, 2, 2, 3, 1, 3, 3, 3, 2);
        assertTrue(ticTacToe.isBoardFull());
        assertTrue(ticTacToe.isGameOver());
        assertEquals('0', ticTacToe.getWinner());
    }

    @Test
    public void checkResetGame() {
        playMoves(ticTacToe, 1, 1, 1, 2, 1, 3, 2, 1, 2, 3, 2, 2, 3, 1, 3, 3, 3, 2);
        ticTacToe.resetGame();
        assertFalse(ticTacToe.isGameOver());
        assertFalse(ticTacToe.isBoardFull());
        assertNull(ticTacToe.getWinner());
        assertEquals('X', ticTacToe.getCurrentPlayer());
    }

    @Test
    public void checkResetGameBoard() {
        playMoves(ticTacToe, 1, 1, 1, 2, 1, 3, 2, 1, 2, 3, 2, 2, 3, 1, 3, 3, 3, 2);
        ticTacToe.resetGame();
        Character[][] board = ticTacToe.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals('0', board[i][j]);
            }
        }
    }

    private void playMoves(TicTacToe ticTacToe, int... moves) {
        for (int i = 0; i < moves.length; i += 2) {
            ticTacToe.play(moves[i], moves[i + 1]);
        }
    }
}
