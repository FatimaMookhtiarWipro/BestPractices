package com.wipro.cloud.api.helloworld.infrastructure.service.security;


/**
 * 
 * @author @ET
 *
 * The class handles authorization for HelloWorldApi access/invocation by a user.
 */
public interface HelloWorldApiAuthorizationService {
	
	/**
	 * Evaluates whether the user invoking the API is authorized for the operation in question
	 * 
	 * @param loggedInUserId
	 * @param clientId
	 */
	boolean isUserAuthorizedForOperation(long loggedInUserId, long clientId);

}
