package com.ymd.businesshousegame.exception;

import java.io.Serial;

public class PlayerCannotBeAddedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2567644624636745111L;

    public PlayerCannotBeAddedException(String message) {
        super(message);
    }

}
