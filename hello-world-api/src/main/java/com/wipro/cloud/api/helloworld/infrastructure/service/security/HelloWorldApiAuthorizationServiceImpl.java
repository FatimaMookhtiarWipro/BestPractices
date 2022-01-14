package com.wipro.cloud.api.helloworld.infrastructure.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.cloud.api.helloworld.controller.RequestHeaderBean;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;
import com.wipro.cloud.api.helloworld.repository.*;
    
@Service
public class HelloWorldApiAuthorizationServiceImpl implements HelloWorldApiAuthorizationService {

	private static final Logger logger = LoggerFactory.getLogger("logger.HelloWorldApi");

	@Autowired
    RetrieveAgentDetailsRepository retrieveAgentDetailsRepository;
    
	@Autowired
	RequestHeaderBean requestHeaderBean;

	@Override
	public boolean isUserAuthorizedForOperation(long loggedInUserId, long clientId) {
		logger.debug("Checking authorization");
		requestHeaderBean = retrieveAgentDetailsRepository.retrieveAgentDetails(loggedInUserId);
		long clientIdForLoggedInUser = requestHeaderBean.getClientId();
		boolean isAuthorizedForAccess = clientId == clientIdForLoggedInUser;
		if (!isAuthorizedForAccess) {
			throw new HelloWorldApiApiSecurityException("Not Authorized for required operation", 401);
		} else {
			return true;
		}
	}

}

