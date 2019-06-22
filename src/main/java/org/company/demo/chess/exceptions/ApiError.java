package org.company.demo.chess.exceptions;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private String message;
	private String debugMessage;
	private final boolean success = false;

	ApiError(HttpStatus status) {
		this.status = status;
	}

	ApiError(HttpStatus status, Throwable ex) {
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public ApiError(HttpStatus status, String message, Throwable ex) {
		super();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}
	
	public ApiError(HttpStatus status, String message, String debugMessage) {
		super();
		this.status = status;
		this.message = message;
		this.debugMessage = debugMessage;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public boolean isSuccess() {
		return success;
	}

	
}
