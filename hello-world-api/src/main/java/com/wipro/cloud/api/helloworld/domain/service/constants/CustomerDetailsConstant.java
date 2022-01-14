package com.wipro.cloud.api.helloworld.domain.service.constants;

/**
 * 
 *
 */
public class CustomerDetailsConstant { 
	
	public static final String CITY = "City";
	public static final String POST_CODE = "Postal Code";
	public static final String COUNTRY = "Country";
	public static final String ADDRESS_LINES = "Address Lines";
	public static final String BLANK = "";
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final String PORTAL_CLIENT_IDENTIFIER = "PORTAL_CLIENT_IDENTIFIER";
	public static final String ADMINISTRATOR_APPROVAL_REQUIRED = "ADMINISTRATOR_APPROVAL_REQUIRED";
	public static final String INVOICE_RECEIVER = "INVOICE_RECEIVER";
	public static final String INVOICE_DETAILS_LAST_UPDATED_DATE = "INVOICE_DETAILS_LAST_UPDATED_DATE";
	public static final String USER_NAME = "USER_NAME";
	public static final String ELECTRONIC_ADDRESS = "ELECTRONIC_ADDRESS";
	public static final String INVOICE_SORT_CODE = "INVOICE_SORT_CODE";
	public static final String INVOICE_ACCOUNT_NUMBER = "INVOICE_ACCOUNT_NUMBER";
	public static final String INVOICE_ACCOUNT_CURRENCY = "INVOICE_CURRENCY";
	public static final String INVOICE_ACCOUNT_STATUS = "INVOICE_ACCOUNT_STATUS";
	public static final String WORKFLOW_EXISTS = "WORKFLOW_EXISTS";
	public static final String USERS = "USERS";
	public static final String ALPHABET_P = "P";
	public static final String ALPHABET_D = "D";
	/**
     * Constant for Registration Type.
     */
    public static final String CUSTOMER_REGISTRATION_TYPE = "WHOLESALE_INTERNET_BANKING";
    public static final String RETRIEVE_CUSTOMER_REGISTRATION_DETAILS = "retrieveCustomerRegistrationDetails";
    public static final String INTERNETBANKINGSERVICE = "InternetBankingRegistrationManagementService";
    public static final String BASIC_DETAIL = "Basic_Details";
    public static final String INVOICE_DETAILS = "INVOICE_DETAILS";
    
	
	/**
	 * boolean true.
	 */
	public static final boolean TRUE = true;

	public static final String CONTACT_POINT_ELEMENT = "ContactPoint";

	public static final String CONTACT_POINT_NS_PREFIX = "soap";

	public static final String NAMESPACE = "http://www.lloydstsb.com/Schema/Infrastructure/SOAP";

	public static final String MUST_RETURN = "mustReturn";

	public static final String CONTACT_POINT_ID = "ContactPointId";

	public static final String APPLICATION_ID = "Internet Banking";

	public static final String INITIAL_ORIGINATOR_TYPE = "InitialOriginatorType";

	public static final String INITIAL_ORIGINATOR_ID = "InitialOriginatorId";

	public static final String OPERATOR_TYPE = "OperatorType";

	/**
	 * SOAP Header Element.
	 */
	public static final String BAPI_INFORMATION_ELEMENT = "bapiInformation";

	public static final String SECURITY_ELEMENT = "Security";

	public static final String SERVICE_REQUEST_ELEMENT = "ServiceRequest";

	/**
	 * ServiceRequest SOAP Header Child Element.
	 */
	public static final String SERVICE_NAME = "ServiceName";

	public static final String ACTION = "Action";

	public static final String FROM = "From";

	public static final String MESSAGE_ID = "MessageId";

	/**
	 * SOAPHandlerHelper namespace prefix and URI.
	 */
	public static final String BAPI_INFORMATION_NS_PREFIX = "lcsm";

	public static final String BAPI_INFORMATION_NAMESPACE = "http://www.lloydstsb.com/Schema/Enterprise/LCSM";

	public static final String SECURITY_NS_PREFIX = "oas";

	public static final String SECURITY_NAMESPACE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

	public static final String SERVICE_REQUEST_NS_PREFIX = "soap";

	public static final String FALSE_STRING = "false";
	public static final String USER_LOGIN_ID = "UserId";
	public static final String USER_ROLE = "user-role";
	public static final String CORREL_ID = "ct-correlation-id";
	public static final String BRAND = "brand";
	public static final String CLIENT_ID = "clientid";
	public static final String SESSION_ID = "session id";
	public static final String BOS_BRAND = "BOS";
	public static final String CLIENT_ID_NOT_FOUND = "Client detail cannot be fetched";
	public static final String WORKFLOW_ID_NOT_FOUND = "Requested Workflow detail not found";
	
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String COREL_ID_KEY = "correlation-id=";
	public static final String GENERIC_UI_ERROR_MSG = " Exception occured while processing Client Administration request.";
	
	public static final String SOA_RESPONSE_NULL = "NULL response received from SOA for retrieveCustomerRegistrationDetails service call";
	public static final String EXTERNAL_APPLICATION_ID = "271";
	/**
	 * Holds the Brand.
	 *
	 */
	public static final String HEADERS_NOT_SET = "ClientSummary HTTP headers not set";
	/**
	 * 
	 */
	public static final String WORFKFLOW_ITEM_ID = "workflowitemid";
	/**
	 * 
	 */
	public static final String HOST_IP_ADDRESS = "host-ip";
	/**
	 * 
	 */
	public static final int CONTACT_POINT_ID_LTB = 304939;
	/**
	 * 
	 */
	public static final int CONTACT_POINT_ID_BOS = 804940;
	/**
	 * 
	 */
	public static final String LLOYDS_BRAND = "LLOYDS";
	/**
	 * 
	 */
	public static final String LLOYDS_BANK_CODE = "LTB";
	/**
	 * 
	 */
	public static final String X_FORWARDED_HOST = "X-Forwarded-Host";
	/**
	 * 
	 */
	public static final String RETRIEVE_WF_API_MTD_NAME = "workflowDetail";
	/**
	 * 
	 */
	public static final String SUBMIT_WF_API_MTD_NAME = "submitClientInvoiceDetails";
	/**
	 * 
	 */
	public static final String SSA_ROLE = "SSA";
	/**
	 * 
	 */
	private CustomerDetailsConstant() {
		
	}
		
	
}
