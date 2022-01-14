package com.wipro.cloud.api.helloworld.domain.service;

import java.util.Optional;

import javax.validation.constraints.NotNull;



import com.wipro.cloud.api.helloworld.domain.*;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;

public interface UserService {
	
	/**
	 *  User domain service 
	 * @param idForUser
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	public Optional<User> findUserDetailById(@NotNull long idForUser);
	
	

}
