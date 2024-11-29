package com.ymd.businesshousegame.exception;

public class GameAlreadyCompletedException extends RuntimeException {

    private static final long serialVersionUID = -4116466897323893791L;

    public GameAlreadyCompletedException(String message) {
        super(message);
    }

}
