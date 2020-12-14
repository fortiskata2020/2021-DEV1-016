package com.tictactoe.tictactoe.model;

import com.tictactoe.tictactoe.exceptions.NonExistingField;
import com.tictactoe.tictactoe.exceptions.OccupiedFieldException;

import java.util.List;

public class Board {

    public Board() {

    }

    public void initializeNewBoard() {
    }

    public boolean isDraw() {
        return false;
    }

    public int getSize() {
        return 0;
    }

    public List<List<Symbol>> getGrid() {
        return null;
    }

    public Symbol getSymbol(int row, int column) {
        return null;
    }

    public void setSymbol(Symbol x, int row, int column) throws NonExistingField, OccupiedFieldException {
    }

    public boolean hasWon(Symbol o, int i, int i1) {
        return false;
    }
}
