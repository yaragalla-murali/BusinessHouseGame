package com.ymd.businesshousegame.exception;

public class NoSuchPlayerException extends RuntimeException {

    private static final long serialVersionUID = 3270420646666212336L;

    public NoSuchPlayerException(String msg) {
        super(msg);
    }

}
