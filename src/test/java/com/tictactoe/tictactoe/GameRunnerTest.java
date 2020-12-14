package com.tictactoe.tictactoe;

import com.tictactoe.tictactoe.exceptions.NonExistingField;
import com.tictactoe.tictactoe.exceptions.OccupiedFieldException;
import com.tictactoe.tictactoe.exceptions.OngoingGameException;
import com.tictactoe.tictactoe.model.GameState;
import com.tictactoe.tictactoe.model.Symbol;
import com.tictactoe.tictactoe.services.GameRunner;
import com.tictactoe.tictactoe.services.GameRunnerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameRunnerTest {

    GameRunner gameRunner = new GameRunnerImpl();

    @Test
    public void initializingTest() {
        assertEquals(gameRunner.getState(), null);
        assertEquals(gameRunner.getCurrentSymbol(), null);


        assertDoesNotThrow(()-> gameRunner.parseCommand("new game"));
        assertThrows(OngoingGameException.class, () -> gameRunner.parseCommand("new game"));
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertTrue(gameRunner.getCurrentSymbol() == Symbol.X);
    }

    @Test
    public void checkAlternatingSymbol() {
        assertDoesNotThrow(() -> gameRunner.parseCommand("new game"));
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 1"));
        assertTrue(gameRunner.getCurrentSymbol() == Symbol.O);
        assertDoesNotThrow(() -> gameRunner.parseCommand("2 1"));
        assertTrue(gameRunner.getCurrentSymbol() == Symbol.X);

    }

    @Test
    public void checkNoAlternatingSymbolAfterOccupiedField() {
        assertDoesNotThrow(() -> gameRunner.parseCommand("new game"));
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 1"));
        assertTrue(gameRunner.getCurrentSymbol() == Symbol.O);
        // O tries to play in the same field which fails
        assertThrows(OccupiedFieldException.class,() -> gameRunner.parseCommand("1 1"));
        assertTrue(gameRunner.getCurrentSymbol() == Symbol.O);
    }

    @Test
    public void checkNoAlternatingSymbolAfterUnreachableField() {
        assertDoesNotThrow(() -> gameRunner.parseCommand("new game"));
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 1"));
        assertTrue(gameRunner.getCurrentSymbol() == Symbol.O);
        // O tries to play in the same field which fails
        assertThrows(NonExistingField.class,() -> gameRunner.parseCommand("5 5"));
        assertTrue(gameRunner.getCurrentSymbol() == Symbol.O);
    }

    @Test
    public void testGameStateChangeOnWinX() {
        assertDoesNotThrow(() -> gameRunner.parseCommand("new game"));
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 1"));
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 2"));
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("2 2"));
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 3"));
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("3 3"));
        assertTrue(gameRunner.getState() == GameState.WIN_X);
    }

    @Test
    public void testGameStateChangeOnWinO() {
        assertDoesNotThrow(() -> gameRunner.parseCommand("new game"));
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 2")); //X
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("2 2")); //O
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 3")); //X
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("3 3")); //O
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("2 1")); //X
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 1")); // O
        assertTrue(gameRunner.getState() == GameState.WIN_O);
    }

    @Test
    public void testRowWinDetection() {
        assertDoesNotThrow(() -> gameRunner.parseCommand("new game"));
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 1")); //X
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("2 2")); //O
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 2")); //X
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("3 3")); //O
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 3")); //X
        assertTrue(gameRunner.getState() == GameState.WIN_X);
    }

    @Test
    public void testColumnWinDetection() {
        assertDoesNotThrow(() -> gameRunner.parseCommand("new game"));
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 1")); //X
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("2 2")); //O
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("2 1")); //X
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("3 3")); //O
        assertTrue(gameRunner.getState() == GameState.PLAYING);
        assertDoesNotThrow(() -> gameRunner.parseCommand("3 1")); //X
        assertTrue(gameRunner.getState() == GameState.WIN_X);
    }

    @Test
    public void testDetectDraw() {
        assertDoesNotThrow(() -> gameRunner.parseCommand("new game"));
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 1")); //X
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 2")); //O
        assertDoesNotThrow(() -> gameRunner.parseCommand("1 3")); //X
        assertTrue(gameRunner.getState() == GameState.PLAYING);

        assertDoesNotThrow(() -> gameRunner.parseCommand("2 2")); //O
        assertDoesNotThrow(() -> gameRunner.parseCommand("3 2")); //X
        assertDoesNotThrow(() -> gameRunner.parseCommand("2 1")); //O
        assertTrue(gameRunner.getState() == GameState.PLAYING);

        assertDoesNotThrow(() -> gameRunner.parseCommand("2 3")); //X
        assertDoesNotThrow(() -> gameRunner.parseCommand("3 3")); //O
        assertDoesNotThrow(() -> gameRunner.parseCommand("3 1")); //X

        assertTrue(gameRunner.getState() == GameState.DRAW);
    }

}
