package com.ymd.businesshousegame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = PlayerCannotBeAddedException.class)
	public ErrorResponse handlePlayerCannotBeAddedException(PlayerCannotBeAddedException ex) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler(value = GameDoesNotExistException.class)
	public ErrorResponse handleGameDoesNotExistException(GameDoesNotExistException ex) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler(value = WrongPlayerException.class)
	public ErrorResponse handleWrongPlayer(WrongPlayerException ex) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler(value = NoSuchPlayerException.class)
	public ErrorResponse handleNoSuchPlayerException(NoSuchPlayerException ex) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ExceptionHandler(value = GameAlreadyCompletedException.class)
	public ErrorResponse handleGameAlreadyCompletedException(GameAlreadyCompletedException ex) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

}
