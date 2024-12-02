package com.ymd.businesshousegame.exception;

import java.io.Serial;

public class GameDoesNotExistException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2342471389855884124L;

    public GameDoesNotExistException(String message) {
        super(message);

    }

}
