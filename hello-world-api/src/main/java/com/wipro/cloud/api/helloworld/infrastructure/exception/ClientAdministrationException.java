/**********************************************************************
 * This source code is the property of Lloyds TSB Group PLC.
 *
 * All Rights Reserved.
 *
 * Class Name: ClientAdministrationException
 *
 *
 ***********************************************************************/
package com.wipro.cloud.api.helloworld.infrastructure.exception;

/**
 * Parent exception for all other user defined exception in application
 *  
 * @author 8734818
 */
public class ClientAdministrationException extends Exception {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = 5590871362003985716L;

	/**
	 * Constructor with message as a parameter
	 * 
	 * @param detailMessage
	 */
	public ClientAdministrationException(final String detailMessage) {
		super(detailMessage);
	}

	/**
	 * Constructor with cause as a parameter
	 * 
	 * @param cause
	 */
	public ClientAdministrationException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor with message and cause as parameters
	 * 
	 * @param detailMessage
	 * @param cause
	 */
	public ClientAdministrationException(final String detailMessage, final Throwable cause) {
		super(detailMessage, cause);
	}
	
}
