package com.ymd.businesshousegame.exception;

import java.io.Serial;

public class WrongPlayerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6508019324027725220L;

    public WrongPlayerException(String message) {
        super(message);
    }

}
