package com.ymd.businesshousegame.exception;

import java.io.Serial;

public class NoSuchPlayerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3270420646666212336L;

    public NoSuchPlayerException(String msg) {
        super(msg);
    }

}
