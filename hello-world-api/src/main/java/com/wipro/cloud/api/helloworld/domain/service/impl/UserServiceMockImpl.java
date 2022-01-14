package com.wipro.cloud.api.helloworld.domain.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;


// import com.lbg.cbo.HelloWorldApi.domain.User;
// import com.lbg.cbo.HelloWorldApi.domain.service.UserService;
// import com.lbg.cbo.HelloWorldApi.infrastructure.exception.UserNotFoundException;
import com.wipro.cloud.api.helloworld.domain.*;
import com.wipro.cloud.api.helloworld.domain.service.*;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;
import com.wipro.cloud.api.helloworld.infrastructure.service.*;

/**
 * 
 * Mock implementation of UserApi. Should be replaced by SOA implementation.
 *
 */
@Service
public class UserServiceMockImpl implements UserService {

	@Override
	public Optional<User> findUserDetailById(long id) {
		User client = new User(id);
		if (id == 1234) {
			return Optional.ofNullable(client);
		}else if(id==56789) {
			//emulating behavior to test Default Handler
			User clientTemp = null;
			return Optional.of(clientTemp);
		}
		else {
			throw new UserNotFoundException("No client found for id: " + id);
		}
	}
}