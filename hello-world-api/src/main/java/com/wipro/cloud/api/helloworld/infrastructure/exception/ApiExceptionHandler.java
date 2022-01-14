/**********************************************************************
 * This source code is the property of Lloyds TSB Group PLC.
 * All Rights Reserved.
 * Class Name: ApiExceptionHandler.java
 * Date: 
 ***********************************************************************/

package com.wipro.cloud.api.helloworld.infrastructure.exception;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.wipro.cloud.api.helloworld.domain.service.constants.CustomerDetailsConstant;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;








/**
 * Handler for exceptions thrown by Services. Currently handles
 * <ul>
 * <li>ResourceNotFoundException</li>
 * <li>Global error handler</li>
 * <li>NoHandlerFoundException</li>
 * <li>ClientNotFoundException</li>
 * <li>ClientServiceException</li>
 * <li>ClientSummaryAPISecurityException</li>
 * </ul>
 * by wrapping exception details into an {@link ErrorModel} object.
 *
 */
@ControllerAdvice
public class ApiExceptionHandler {

	@Resource
	private Map<String, ErrorDetail> errorMap;

	/**
	 * Handles {@link ResourceNotFoundException}.
	 * 
	 * @param ResourceNotFoundException
	 * @param Generic                   web request {@link WebRequest}
	 * @return Wrapped {@link ResponseEntity} with {@link ErrorModel} and
	 *         {@link HttpStatus}
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorModel> resourceNotFoundException(final ResourceNotFoundException exception,
			final WebRequest request) {
		final ErrorModel errorDetails = new ErrorModel.ErrorModelBuilder().withHttpMessage(exception.getMessage())
				.withHttpCode(HttpStatus.NOT_FOUND.value()).build();
		return new ResponseEntity<>(errorDetails, getResponseHeader(request), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handles {@link MissingServletRequestParameterException} - uri request with
	 * missing query params.
	 * 
	 * @param MissingServletRequestParameterException
	 * @param Generic                                 web request {@link WebRequest}
	 * @return Wrapped {@link ResponseEntity} with {@link ErrorModel} and
	 *         {@link HttpStatus}
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorModel> resourceNotFoundException(final MissingServletRequestParameterException exception,
			final WebRequest request) {
		final ErrorModel errorDetails = new ErrorModel.ErrorModelBuilder().withHttpMessage(exception.getMessage())
				.withHttpCode(HttpStatus.BAD_REQUEST.value()).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles {@link IllegalArgumentException}.
	 * 
	 * @param ResourceNotFoundException
	 * @param Generic                   web request {@link WebRequest}
	 * @return Wrapped {@link ResponseEntity} with {@link ErrorModel} and
	 *         {@link HttpStatus}
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorModel> illegalArgumentException(final IllegalArgumentException exception,
			final WebRequest request) {
		final ErrorModel errorDetails = new ErrorModel.ErrorModelBuilder().withHttpMessage(exception.getMessage())
				.withHttpCode(HttpStatus.BAD_REQUEST.value()).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

/**
	 * Handles {@link Exception}.
	 * 
	 * @param Exception
	 * @param Generic                   web request {@link WebRequest}
	 * @return Wrapped {@link ResponseEntity} with {@link ErrorModel} and
	 *         {@link HttpStatus}
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorModel> defaultErrorHandler(final Exception exception, final WebRequest request) {
		
		final ErrorModel errorDetails = new ErrorModel.ErrorModelBuilder()
				.withHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).withHttpMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.withErrors(Arrays.asList(errorMap.get(ErrorConstants.ERR193))).build();
		return new ResponseEntity<>(errorDetails, getResponseHeader(request), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handles {@link NoHandlerFoundException}.
	 * 
	 * @param NoHandlerFoundException
	 * @param Generic                 web request {@link WebRequest}
	 * @return Wrapped {@link ResponseEntity} with {@link ErrorModel} and
	 *         {@link HttpStatus}
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorModel> resolveNoHandlerException(final NoHandlerFoundException exception,
			final WebRequest request) {
		final ErrorModel errorDetails = new ErrorModel.ErrorModelBuilder()
				.withHttpMessage(String.format("Could not find %s method for the URL %s", exception.getHttpMethod(),
						exception.getRequestURL()))
				.withHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
		return new ResponseEntity<>(errorDetails, getResponseHeader(request), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handles {@link ClientServiceException}.
	 * 
	 * @param ClientServiceException
	 * @param Generic                web request {@link WebRequest}
	 * @return Wrapped {@link ResponseEntity} with {@link ErrorModel} and
	 *         {@link HttpStatus}
	 */
	@ExceptionHandler(ClientServiceException.class)
	public ResponseEntity<ErrorModel> handleApplicationRuntimeException(final ClientServiceException csException,
			final WebRequest request) {
		final ErrorModel errorDetails = new ErrorModel.ErrorModelBuilder().withHttpMessage(csException.getMessage())
				.withHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();

		return new ResponseEntity<>(errorDetails, getResponseHeader(request), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Returns the response header for service response
	 * 
	 * @param request
	 * @return
	 */
	private HttpHeaders getResponseHeader(final WebRequest request) {
		/*
		 * Populating response header attributes 
		 */
		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(CustomerDetailsConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		//responseHeaders.set(CustomerDetailsConstant.CORREL_ID, request.getHeader(CustomerDetailsConstant.CORREL_ID));
		return responseHeaders;
	}
	

	@ExceptionHandler(InfrastructureServiceException.class)
	public ResponseEntity<ErrorModel> handleInfrastructureServiceException(
			final InfrastructureServiceException infrastructureServiceException, final WebRequest request) {
		final ErrorModel errorDetails = new ErrorModel.ErrorModelBuilder()
				.withHttpCode(HttpStatus.SERVICE_UNAVAILABLE.value())
				.withHttpMessage(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())
				.withErrors(infrastructureServiceException.getconfig()).build();
		return new ResponseEntity<>(errorDetails, getResponseHeader(request), HttpStatus.SERVICE_UNAVAILABLE);
	}
	

	
	/**
	 * Handles {@link HelloWorldApiApiSecurityException }.
	 * @param HelloWorldApiApiSecurityException 
	 * @param Generic web request {@link WebRequest}
	 * @return Wrapped {@link ResponseEntity} with {@link ErrorModel} and
	 *         {@link HttpStatus}
	 */
	@ExceptionHandler(HelloWorldApiApiSecurityException .class)
	public ResponseEntity<ErrorModel> handleSecurityException(final HelloWorldApiApiSecurityException  securityException, final WebRequest request) {
		final ErrorModel errorDetails = new ErrorModel.ErrorModelBuilder()
				.withHttpMessage(securityException.getMessage()).withHttpCode(securityException.getStatusCode())
				.build();

		return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(securityException.getStatusCode()));
	
	}
}
