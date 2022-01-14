package com.wipro.cloud.api.helloworld.infrastructure.exception;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class InfrastructureServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private transient List<ErrorDetail> config;

	public InfrastructureServiceException(String message) {
		super(message);
	}

	public InfrastructureServiceException(Exception e) {
	}

	public InfrastructureServiceException(Exception e, String string) {
	}

	public InfrastructureServiceException(String message, ErrorDetail... config) {
		super(message);
		this.config = Arrays.asList(config);
	}

	public List<ErrorDetail> getconfig() {
		return Collections.unmodifiableList(this.config);
	}

}
