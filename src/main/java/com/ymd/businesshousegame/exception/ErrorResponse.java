package com.ymd.businesshousegame.exception;

public class ErrorResponse {

	private int statusCode;
	private String msg;

	public ErrorResponse(int statusCode, String msg) {
		this.msg = msg;
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
