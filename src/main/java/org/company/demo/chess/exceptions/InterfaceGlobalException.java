package org.company.demo.chess.exceptions;

import org.springframework.http.HttpStatus;

public interface InterfaceGlobalException {
	public String reason = "";
	public String message = "";
	public boolean success = false;
	public HttpStatus status = null;
	void setStatus(HttpStatus status);
}
