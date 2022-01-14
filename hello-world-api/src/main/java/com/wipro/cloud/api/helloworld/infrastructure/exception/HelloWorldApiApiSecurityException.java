package com.wipro.cloud.api.helloworld.infrastructure.exception;

/**
 * 
 * @author @ET 
 * Wraps Authentication/Authorization events. This should
 *         translate to 403/401 depending if th event it Authentication Failure
 *         and Authorization Failure
 *
 */
public class HelloWorldApiApiSecurityException extends RuntimeException {

	private final int errorStatusCode;

	private static final long serialVersionUID = 1L;

	/**
	 * Overloaded method for passing
	 * <ul>
	 * <li>401 - UnAuthorized Access</li>
	 * <li>403 - Forbidden Access</li>
	 * </ul>
	 * 
	 * @param e
	 * @param string
	 * @param statusCode
	 */
	public HelloWorldApiApiSecurityException(String message, int errorStatuscode) {
		super(message);
		this.errorStatusCode = errorStatuscode;
	}

	public HelloWorldApiApiSecurityException(Throwable cause, int errorStatuscode) {
		super(cause);
		this.errorStatusCode = errorStatuscode;
	}

	public HelloWorldApiApiSecurityException(String message, Throwable cause, int errorStatusCode) {
		super(message, cause);
		this.errorStatusCode = errorStatusCode;
	}

	public int getStatusCode() {
		return this.errorStatusCode;
	}

}
