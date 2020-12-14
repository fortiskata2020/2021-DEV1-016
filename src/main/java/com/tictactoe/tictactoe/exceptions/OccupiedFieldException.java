package com.tictactoe.tictactoe.exceptions;

public class OccupiedFieldException extends Throwable{

    public OccupiedFieldException(String message) {
        super(message);
    }
}
