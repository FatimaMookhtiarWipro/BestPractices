package com.wipro.cloud.api.helloworld.infrastructure.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4410970481525563553L;
	
	public UserNotFoundException(final String message) {
		super(message);
	}

}
