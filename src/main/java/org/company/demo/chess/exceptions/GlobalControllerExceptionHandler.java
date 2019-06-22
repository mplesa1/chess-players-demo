package org.company.demo.chess.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<Object> handleNoSuchElement(NoSuchElementException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Nije prona�eno!", ex);
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Id nije definiran!", ex);
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}

//	private HttpStatus status;
//	private boolean success;
//	@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND) // 404
//	@ExceptionHandler(NoSuchElementException.class)
//	public ResponseEntity<Object> handleConflict() {
//		this.setStatus(HttpStatus.NOT_FOUND);
//		System.out.println(this.status);
//		return new ResponseEntity<Object>(
//				"{'reason' => '"+ this.status + "_not_found', 'message' => 'Nije prona�eno!', 'success' => '" + this.success + "' }", new HttpHeaders(),
//				this.status);
//	}

//	@ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR) // 500
//	@ExceptionHandler(InternalServerErrorException)
//	public ResponseEntity<Object> internalServerError() {
//		this.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//		System.out.println(this.status);
//		return new ResponseEntity<Object>(
//				"{'reason' => '"+ this.status + "INTERNAL_SERVER_ERROR', 'message' => 'Server error!', 'success' => " + this.succes + " }", new HttpHeaders(),
//				this.status);
//	}

//	@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND) // 404
//	@ExceptionHandler(NoSuchElementException.class)
//	public ResponseEntity<Object> handleConflict() {
//		return new ResponseEntity<Object>("Not found!", new HttpHeaders(), HttpStatus.NOT_FOUND);
//	}

//	@ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST) // 404
//	@ExceptionHandler(BadRequestException.class)
//	public ResponseEntity<Object> badRequest(BadRequestException e) {
//		return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
//	}

//	@Override
//	public void setStatus(HttpStatus status) {
//		this.status = status;
//	}

}
