package com.tictactoe.tictactoe.services;

import com.tictactoe.tictactoe.model.GameState;
import com.tictactoe.tictactoe.model.Symbol;

public interface GameRunner {

    Symbol getCurrentSymbol();

    GameState getState();

    /**
     * A method that parses user commands to advance the game.
     * @param command The next user command that the application has to parse.
     * @throws Throwable parseCommand has a set of exceptions it can throw based on the input
     */
    void parseCommand(String command) throws Throwable;
}
