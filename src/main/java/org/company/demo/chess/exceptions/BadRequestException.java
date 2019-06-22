package org.company.demo.chess.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BadRequestException extends RuntimeException {

	HttpStatus status;
	public String debugMessage = "";
	public String message = "";
	private static final long serialVersionUID = 6277611247490025632L;

	public BadRequestException(String message, String debugMessage) {
		this.message = message;
		this.debugMessage = debugMessage;
	}

	public ResponseEntity<Object> throwBadRequestException() {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, this.message, this.debugMessage);
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
