package com.wipro.cloud.api.helloworld.infrastructure.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6456230504189881702L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
