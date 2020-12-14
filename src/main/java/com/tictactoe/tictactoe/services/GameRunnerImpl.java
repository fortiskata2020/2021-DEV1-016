package com.tictactoe.tictactoe.services;

import com.tictactoe.tictactoe.exceptions.NonExistingField;
import com.tictactoe.tictactoe.exceptions.OccupiedFieldException;
import com.tictactoe.tictactoe.exceptions.OngoingGameException;
import com.tictactoe.tictactoe.model.GameState;
import com.tictactoe.tictactoe.model.Symbol;

public class GameRunnerImpl implements GameRunner {


    @Override
    public Symbol getCurrentSymbol() {
        return null;
    }

    @Override
    public GameState getState() {
        return null;
    }

    @Override
    public void parseCommand(String command) throws OccupiedFieldException, NonExistingField, OngoingGameException {

    }
}
