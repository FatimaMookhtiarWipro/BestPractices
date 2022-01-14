package com.wipro.cloud.api.helloworld.repository;
import org.springframework.stereotype.Component;

import com.wipro.cloud.api.helloworld.controller.RequestHeaderBean;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;

/**
 * Retreives list of active accounts registered to the client identified by
 * clientID
 *
 */
@Component
public interface RetrieveAgentDetailsRepository {

	/**
	 * 
	 * @param userLoginId
	 * @return
	 * @throws InfrastructureServiceException
	 */
	RequestHeaderBean retrieveAgentDetails(long userLoginId);

}

