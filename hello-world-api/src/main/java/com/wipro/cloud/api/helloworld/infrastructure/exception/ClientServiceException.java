/**********************************************************************
 * This source code is the property of Lloyds TSB Group PLC.
 * All Rights Reserved.
 * Class Name: ClientServiceException
 * Date: 18 Sep 2019
 ***********************************************************************/
package com.wipro.cloud.api.helloworld.infrastructure.exception;

/**
 * @author 8734818
 *
 */
public class ClientServiceException extends ClientAdministrationException {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = 7022494011548446682L;

	/**
	 * @param detailMessage
	 */
	public ClientServiceException(final String detailMessage) {
		super(detailMessage);
	}

	/**
	 * @param cause
	 */
	public ClientServiceException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param detailMessage
	 * @param cause
	 */
	public ClientServiceException(final String detailMessage, final Throwable cause) {
		super(detailMessage, cause);
	}

}
