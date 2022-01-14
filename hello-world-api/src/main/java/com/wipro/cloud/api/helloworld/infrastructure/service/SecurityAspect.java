package com.wipro.cloud.api.helloworld.infrastructure.service;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerMapping;

import com.wipro.cloud.api.helloworld.domain.service.constants.CustomerDetailsConstant;

import com.wipro.cloud.api.helloworld.controller.RequestHeaderBean;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;
import com.wipro.cloud.api.helloworld.infrastructure.service.CommonHelper;
import com.wipro.cloud.api.helloworld.infrastructure.service.security.*;

/**
 * 
 * @author SPARX/ET
 * 
 *         Handles security related aspects for authentication.
 *
 */
	@Aspect
	@Configuration
	public class SecurityAspect {

		@Autowired
		private HelloWorldApiAuthorizationService authService;

		@Autowired
		private RequestHeaderBean requestHeaderBean;

		@SuppressWarnings("unchecked")
		@Before("execution(public * com.wipro.cloud.api.helloworld.controller.*Controller.*(..)) && args(..,request)  ")
		public void authenticateApiCalls(JoinPoint joinPoint, HttpServletRequest request) {
			final String loggedInUserId = isaValidHeader(request.getHeader(CustomerDetailsConstant.USER_LOGIN_ID));
			final String brandFromHeader = isaValidHeader(request.getHeader(CustomerDetailsConstant.X_FORWARDED_HOST));
			final String correlationId = isaValidHeader(request.getHeader((CustomerDetailsConstant.CORREL_ID)));
			final Map<String, String> mapOfPathVar = (Map<String, String>) request
					.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			final String clientId = CommonHelper.decodeString(mapOfPathVar.get(CustomerDetailsConstant.CLIENT_ID));
			authorizeUser(joinPoint.getSignature().getName(), clientId, loggedInUserId);
			requestHeaderBean.setBrand(getBrandValueFromHeader(brandFromHeader));
			requestHeaderBean.setCorrelationId(correlationId);
		}

		/**
		 * 
		 */
		private void authorizeUser(String methodName, String clientId, String loggedInUserId) {
			switch (methodName) {
			case CustomerDetailsConstant.RETRIEVE_WF_API_MTD_NAME:
				isUserAuthorized(loggedInUserId, clientId);
				isUserAuthorizedToRetrieveWF();
				break;
			case CustomerDetailsConstant.SUBMIT_WF_API_MTD_NAME:
				isUserAuthorized(loggedInUserId, clientId);
				isUserAuthorizedToSubmitWF();
				break;
			default:
				isUserAuthorized(loggedInUserId, clientId);
				isUserHasSSARole();
			}
		}

		/**
		 * 
		 * @param loggedInUserId
		 * @param clientId
		 * @return
		 */
		private boolean isUserAuthorized(String loggedInUserId, String clientId) {
			return (authService.isUserAuthorizedForOperation(Long.valueOf(loggedInUserId), Long.valueOf(clientId)));
		}

		/**
		 * 
		 * @param workflowId
		 * @return
		 */
		private boolean isUserAuthorizedToRetrieveWF() {
			/*
			* Need to update this once stored procedure returns worflowId.
			*/
			return isUserHasSSARole();
		}

		/**
		 * 
		 */
		private boolean isUserAuthorizedToSubmitWF() {
			return isUserHasSSARole();
		}

		/**
		 * 
		 */
		private boolean isUserHasSSARole() {
			if (CustomerDetailsConstant.SSA_ROLE.equals(requestHeaderBean.getUserRole())) {
				return true;
			}
			throw new HelloWorldApiApiSecurityException ("Not Authorized for required operation", 401);
		}

		/**
		 * 
		 * @param hostString
		 * @return
		 */
		private String getBrandValueFromHeader(String hostString) {

			if (Pattern.compile(Pattern.quote(CustomerDetailsConstant.LLOYDS_BRAND), Pattern.CASE_INSENSITIVE)
					.matcher(hostString).find()) {
				return CustomerDetailsConstant.LLOYDS_BRAND;
			}
			return CustomerDetailsConstant.BOS_BRAND;
		}

		private String isaValidHeader(String headerVal) {
			return Optional.ofNullable(headerVal)
					.orElseThrow(() -> new HelloWorldApiApiSecurityException ("Insufficient authorization", 403));
		}
	}
