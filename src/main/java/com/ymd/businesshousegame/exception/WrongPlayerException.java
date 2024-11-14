package com.ymd.businesshousegame.exception;

public class WrongPlayerException extends RuntimeException {

	private static final long serialVersionUID = 6508019324027725220L;

	public WrongPlayerException(String message) {
		super(message);
	}

}
