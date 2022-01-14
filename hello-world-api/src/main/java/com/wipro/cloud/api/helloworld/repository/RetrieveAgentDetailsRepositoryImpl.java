
package com.wipro.cloud.api.helloworld.repository;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.wipro.cloud.api.helloworld.config.*;
import com.wipro.cloud.api.helloworld.controller.RequestHeaderBean;
import com.wipro.cloud.api.helloworld.domain.service.constants.*;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;
import com.wipro.cloud.api.helloworld.infrastructure.service.SimpleJdbcCallFactory;



/**
 * @author 9508408
 *
 */
@Service
public class RetrieveAgentDetailsRepositoryImpl implements RetrieveAgentDetailsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SimpleJdbcCallFactory factory;

	@Autowired
	private RetrieveAgentSqlParameterSourceFactory sqlParmSourceFactory;

	@Autowired
	private HelloWorldApiApiConfig appConfig;

	@Autowired
	private RequestHeaderBean requestHeaderBean;

	/**w
	 * @param loginId
	 * @return resultSet
	 */
	private Map<String, Object> preparedProcAndExecute(final Long loginId) {
		final SimpleJdbcCall simpleJdbcCall = factory.create(jdbcTemplate);
		simpleJdbcCall.withProcedureName(RetrieveAgentConstants.STORED_PROCEDURE_NAME)
				.withSchemaName(appConfig.getChannelSchemaName());
		final SqlParameterSource paramSource = sqlParmSourceFactory.withParamsReturnSQLParamSource(loginId);
		return simpleJdbcCall.execute(paramSource);
	}

	@SuppressWarnings("unchecked")
	private RequestHeaderBean retrieveClientId(Map<String, Object> resultset) {
		if (!hasErrors(resultset)) {
			final List<Object> resultSetObjects = (List<Object>) resultset
					.get(RetrieveAgentConstants.O_C_WORKFLOW_RECORD.toUpperCase());
			return populateRequestHeaderBean((Map<String, Object>) resultSetObjects.get(0));
		} else {
			throw new HelloWorldApiApiSecurityException ("Not authrized to perform this operation", 401);
		}
	}

	private RequestHeaderBean populateRequestHeaderBean(Map<String, Object> rowMapper) {
		requestHeaderBean.setLoginId(Long.parseLong(rowMapper.get(RetrieveAgentConstants.USER_ID).toString()));
		requestHeaderBean.setUserName((String) rowMapper.get(RetrieveAgentConstants.USER_NAME));
		if (Pattern.compile(Pattern.quote(CustomerDetailsConstant.SSA_ROLE), Pattern.CASE_INSENSITIVE)
				.matcher((String)rowMapper.get(RetrieveAgentConstants.ROLE_NAME)).find()) {
			requestHeaderBean.setUserRole(CustomerDetailsConstant.SSA_ROLE);
		}
		requestHeaderBean.setClientId(Long.parseLong(rowMapper.get(RetrieveAgentConstants.CLIENT_ID).toString()));
		requestHeaderBean.setInvSrtCode(rowMapper.get(RetrieveAgentConstants.INV_SORT_CODE).toString());
		requestHeaderBean.setInvAcctNum(rowMapper.get(RetrieveAgentConstants.INV_ACCT_NUM).toString());
		requestHeaderBean.setWorkflowId(Long.parseLong(rowMapper.get(RetrieveAgentConstants.WORKFLOW_ID).toString()));
		requestHeaderBean
				.setWorkflowIndFlag(getWorkflowIndFlag(rowMapper.get(RetrieveAgentConstants.WORKFLOW_IND).toString()));
		return requestHeaderBean;
	}

	private boolean getWorkflowIndFlag(String workflowInd) {
		return ("Y".equalsIgnoreCase(workflowInd));
	}

	/**
	 * check if any DB Error.
	 * 
	 * @param resultset
	 * @return
	 */
	private boolean hasErrors(final Map<String, Object> resultset) {
		boolean hasErrorsInResultSet = false;
			if (null != resultset.get(RetrieveAgentConstants.O_ERR_CODE)
					&& RetrieveAgentConstants.INT_ZERO != ((Number) resultset.get(RetrieveAgentConstants.O_ERR_CODE))
							.intValue()) {
				hasErrorsInResultSet = true;
			}
		return hasErrorsInResultSet;

	}

	@Override
	public RequestHeaderBean retrieveAgentDetails(long partyId) {
		final Map<String, Object> simpleJdbcCallResult = preparedProcAndExecute(partyId);
		return retrieveClientId(simpleJdbcCallResult);
	}

}

