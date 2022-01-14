package com.wipro.cloud.api.helloworld.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.cloud.api.helloworld.domain.*;
import com.wipro.cloud.api.helloworld.domain.service.*;
import com.wipro.cloud.api.helloworld.domain.service.constants.*;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;



/**
 * 
 * @author LBG_ET
 *
 */

@RequestMapping("/helloworldapi/v1/")
@RestController
public class HelloWorldController {

	@Autowired
	ClientApi clientApi;

	@Autowired
	RequestHeaderBean requestHeaderBean;


	@Override
	public String toString() {
		return "ClientController [currently serving api's /helloworldapi/v1/client/{clientid} ]";
	}
	
	/**
	 * 
	 * @param headerMap Map RequestHeaders containing User-Login-Id, User-Role,
	 *                  Correlation-Id.
	 * @param clientId  Long retrieves Client details associated with this clientId
	 * @return ResponseEntity Client returns Client details.
	 * @throws ClientServiceException throws ClientServiceException if there are any
	 *                                failures while retrieving Client Details.
	 */
	@GetMapping(value = "client/{clientid}", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(maxAge = 6000)
	@ResponseBody
	public ResponseEntity<Client> clientDetail(@RequestHeader final Map<String, String> headerMap,
			@PathVariable(CustomerDetailsConstant.CLIENT_ID) final long clientId) throws ClientServiceException {
		//setRequestHeaderInfo(Optional.of(headerMap));
		final Client client = clientApi.findClientDetailById(clientId).orElseThrow(
				() -> new ResourceNotFoundException(CustomerDetailsConstant.CLIENT_ID_NOT_FOUND + clientId));
		return ResponseEntity.ok().body(client);
	}

	/**
	 * @param HeaderMap
	 * @return
	 */
	private void setRequestHeaderInfo(final Optional<Map<String, String>> headers) {
		if (headers.isPresent()) {
			Map<String, String> headerMap = headers.get();
			requestHeaderBean.setLoginId(Long.parseLong(headerMap.get(CustomerDetailsConstant.USER_LOGIN_ID)));
			requestHeaderBean.setCorrelationId(headerMap.get(CustomerDetailsConstant.CORREL_ID));
			requestHeaderBean.setUserRole(headerMap.get(CustomerDetailsConstant.USER_ROLE));
		}
	}

}
