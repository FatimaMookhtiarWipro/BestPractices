package com.wipro.cloud.api.helloworld.infrastructure.exception;

public class ClientNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4410970481525563553L;
	
	public ClientNotFoundException(final String message) {
		super(message);
	}

}
