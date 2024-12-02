package com.ymd.businesshousegame.exception;

import java.io.Serial;

public class GameAlreadyCompletedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4116466897323893791L;

    public GameAlreadyCompletedException(String message) {
        super(message);
    }

}
