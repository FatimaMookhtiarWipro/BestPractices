package com.wipro.cloud.api.helloworld.domain.service;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.wipro.cloud.api.helloworld.domain.*;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;



public interface ClientApi {
	
	/**
	 *  User domain service 
	 * @param clientid
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	public Optional<Client> findClientDetailById(@NotNull long clientid);
	
	

}
