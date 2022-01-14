/**********************************************************************
 * This source code is the property of Lloyds TSB Group PLC.
 * All Rights Reserved.
 * Class Name: RequestHeaderBean
 * Date: 
 ***********************************************************************/
package com.wipro.cloud.api.helloworld.controller;

import org.springframework.stereotype.Component;

/**
 * Sets request header
 * UserID,User-Role,CorrelationId,Brand
 * 
 */
@Component
public class RequestHeaderBean {
	/**
	 * Default Constructor.
	 */
	public RequestHeaderBean() {
		//Default Constructor.
	}
	/**
	 * Holds User Login Id from request headers (User Login Id).
	 */
	private long loginId;
	
	/**
	 * Holds Co-relation Id from request headers.
	 */
	private String correlationId;
	
	/**
	 * Holds Role information from request headers.
	 */
	private String userRole;
	/**
	 * Holds Brand information from request headers
	 */
	private String brand;
	
	
	private String userName;
	/**
	 * Hold clientId
	 */
	private long clientId;
	/**
	 * 
	 */
	private String invSrtCode;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getInvSrtCode() {
		return invSrtCode;
	}
	public void setInvSrtCode(String invSrtCode) {
		this.invSrtCode = invSrtCode;
	}
	public String getInvAcctNum() {
		return invAcctNum;
	}
	public void setInvAcctNum(String invAcctNum) {
		this.invAcctNum = invAcctNum;
	}
	public long getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(long workflowId) {
		this.workflowId = workflowId;
	}
	public boolean isWorkflowIndFlag() {
		return workflowIndFlag;
	}
	public void setWorkflowIndFlag(boolean workflowIndFlag) {
		this.workflowIndFlag = workflowIndFlag;
	}
	/**
	 * 
	 */
	private String invAcctNum;
	/**
	 * 
	 */
	private long workflowId;
	/**
	 * 
	 */
	private boolean workflowIndFlag;
	
	
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	/**
	 * 
	 * 
	 * 
	 * @return long user login ID retrieved from Request Header.
	 */
	public final long getLoginId() {
		return loginId;
	}
	/**
	 * set the user login Id from Request Header. 
	 * @param loginId long
	 */
	public final void setLoginId(long loginId) {
		this.loginId = loginId;
	}
	/**
	 * 
	 * @return String correlation Id retrieved from Request Header.
	 */
	public final String getCorrelationId() {
		return correlationId;
	}
	/**
	 * set the correlation Id from Request Header. 
	 * @param correlationId String
	 */
	public final void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	/**
	 * 
	 * @return String user role retrieved from Request Header.
	 */
	public final String getUserRole() {
		return userRole;
	}
	/**
	 * set the user role from Request Header. 
	 * @param userRole String
	 */
	public final void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	/**
	 * 
	 * @return String brand value retrieved from Request Header.
	 */
	public final String getBrand() {
		return brand;
	}
	/**
	 * set the brand from Request Header. 
	 * @param brand String
	 */
	public final void setBrand(String brand) {
		this.brand = brand;
	}
}