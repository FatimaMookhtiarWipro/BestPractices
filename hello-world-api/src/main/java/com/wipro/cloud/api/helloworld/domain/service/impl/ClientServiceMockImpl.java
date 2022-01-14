package com.wipro.cloud.api.helloworld.domain.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

// import com.lbg.cbo.HelloWorldApi.domain.AdministrativeStatus;
// import com.lbg.cbo.HelloWorldApi.domain.Client;
// import com.lbg.cbo.HelloWorldApi.domain.Status;
// import com.lbg.cbo.HelloWorldApi.domain.service.ClientApi;
// import com.lbg.cbo.HelloWorldApi.infrastructure.exception.ClientNotFoundException;
import com.wipro.cloud.api.helloworld.domain.*;
import com.wipro.cloud.api.helloworld.domain.service.*;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;
import com.wipro.cloud.api.helloworld.infrastructure.service.*;

/**
 * 
 * Mock implementation of ClientApi. Should be replaced by SOA implementation.
 *
 */
@Service
public class ClientServiceMockImpl implements ClientApi {

	@Override
	public Optional<Client> findClientDetailById(long id) {
		Client client = new Client.ClientBuilder().withPasswordExpiryPeriod(90)
				.withAdminStatus(AdministrativeStatus.DUAL_ADMIN).withClientID(1234).withFirstname("Johhny")
				.withLastname("Israel").withUsername("jIsrael").withStatus(Status.ACTIVE).build();
		if (id == 1234) {
			return Optional.ofNullable(client);
		}else if(id==56789) {
			//emulating behavior to test Default Handler
			Client clientTemp = null;
			return Optional.of(clientTemp);
		}
		else {
			throw new ClientNotFoundException("No client found for id: " + id);
		}
	}

}
