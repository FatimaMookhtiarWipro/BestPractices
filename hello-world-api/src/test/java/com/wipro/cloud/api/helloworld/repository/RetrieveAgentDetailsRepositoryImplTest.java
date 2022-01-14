package com.wipro.cloud.api.helloworld.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;


import com.wipro.cloud.api.helloworld.config.HelloWorldApiApiConfig;
import com.wipro.cloud.api.helloworld.controller.RequestHeaderBean;
import com.wipro.cloud.api.helloworld.domain.service.constants.RetrieveAgentConstants;
import com.wipro.cloud.api.helloworld.infrastructure.exception.HelloWorldApiApiSecurityException;
import com.wipro.cloud.api.helloworld.infrastructure.service.SimpleJdbcCallFactory;


@PrepareForTest({ RetrieveAgentDetailsRepositoryImplTest.class })
public class RetrieveAgentDetailsRepositoryImplTest {
	@InjectMocks
	private RetrieveAgentDetailsRepositoryImpl retrieveAgentDetailsRepoImpl;

	@Mock
	private RetrieveAgentDetailsRepository retrieveAgentDetailsRepo;
	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	SimpleJdbcCallFactory factory;

	@Mock
	private SimpleJdbcCall simpleJdbcCall;

	@Mock
	private RetrieveAgentSqlParameterSourceFactory sqlParamsFactory;

	@Mock
	private HelloWorldApiApiConfig appConfig;
	@Mock
	private RequestHeaderBean requestHeaderBean;
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		retrieveAgentDetailsRepo = retrieveAgentDetailsRepoImpl;
		when(appConfig.getChannelSchemaName()).thenReturn("dummySchema");
	}

	@Test
	public void testRetrieveAgentDetails() {
		final Map<String, Object> resultSet = mockResultSet();
		final SqlParameterSource  source    = PowerMockito.mock(SqlParameterSource.class);
		setProcParamsForTest(resultSet, source);
		final RequestHeaderBean requestHeaderBean = retrieveAgentDetailsRepo.retrieveAgentDetails(6006343);
		assertThat(requestHeaderBean.getClientId()).isEqualTo(this.requestHeaderBean.getClientId());
	}
	
	@Test(expected = HelloWorldApiApiSecurityException.class) 
	public void testRetrieveAgentDetails_should_catch_storedProc_err() {
		final Map<String, Object> resultSet = mockErrorResultSet();
		final SqlParameterSource  source    = PowerMockito.mock(SqlParameterSource.class);
		setProcParamsForTest(resultSet, source);
		retrieveAgentDetailsRepo.retrieveAgentDetails(6006343);
	}
	@Test
	public void testRetrieveAgentDetails_should_catch_if_rs_dont_have_err_code() {
		final Map<String, Object> resultSet =mockResultSet();
		resultSet.put(RetrieveAgentConstants.O_ERR_CODE, null);
		final SqlParameterSource  source    = PowerMockito.mock(SqlParameterSource.class);
		setProcParamsForTest(resultSet, source);
		final RequestHeaderBean requestHeaderBean = retrieveAgentDetailsRepo.retrieveAgentDetails(6006343);
		assertThat(requestHeaderBean.getUserRole()).isEqualTo(this.requestHeaderBean.getUserRole());
	}
	
	private void setProcParamsForTest(final Map<String, Object> resultSet, final SqlParameterSource source) {
		when(factory.create(jdbcTemplate)).thenReturn(simpleJdbcCall);
		when(simpleJdbcCall.withProcedureName(RetrieveAgentConstants.STORED_PROCEDURE_NAME)).thenReturn(simpleJdbcCall);
		when(sqlParamsFactory.withParamsReturnSQLParamSource(6006343L)).thenReturn(source);

		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(final InvocationOnMock invocation) throws Throwable {
				return resultSet;
			}

		}).when(simpleJdbcCall).execute(source);
	}
	
	/**
	 *
	 */
	private Map<String, Object> mockErrorResultSet() {
		final Map<String, Object> resultSet = new HashMap<>();
		final List<Map<String, Object>> cursor = new ArrayList<>();
		resultSet.put(RetrieveAgentConstants.O_C_WORKFLOW_RECORD, cursor);
		resultSet.put(RetrieveAgentConstants.O_ERR_CODE, 2);
		resultSet.put(RetrieveAgentConstants.O_ERR_MSG, "Oracle Exception");
		return resultSet;
	}
	/**
	 * 
	 */
	private Map<String, Object> mockResultSet() {
		final Map<String, Object> resultSet = new HashMap<>();
		final List<Map<String, Object>> cursor = new ArrayList<>();
		resultSet.put(RetrieveAgentConstants.O_C_WORKFLOW_RECORD, cursor);
		resultSet.put(RetrieveAgentConstants.O_ERR_CODE, 0);
		Map<String, Object> rowMapper = new HashMap<>();
		rowMapper.put(RetrieveAgentConstants.USER_ID, 6006343);
		rowMapper.put(RetrieveAgentConstants.CLIENT_ID, 28207086);
		rowMapper.put(RetrieveAgentConstants.ROLE_NAME, "SSA");
		rowMapper.put(RetrieveAgentConstants.USER_NAME, "XXXXTEST01");
		rowMapper.put(RetrieveAgentConstants.INV_SORT_CODE, 112233);
		rowMapper.put(RetrieveAgentConstants.INV_ACCT_NUM,98765432);
		rowMapper.put(RetrieveAgentConstants.WORKFLOW_ID, -1);
		rowMapper.put(RetrieveAgentConstants.WORKFLOW_IND, "N");
		cursor.add(rowMapper);
		return resultSet;
	}
	/**
	 * 
	 */
	private RequestHeaderBean mockRequestHeaderBean() {
		requestHeaderBean = new RequestHeaderBean();
		requestHeaderBean.setLoginId(6006343);
		requestHeaderBean.setClientId(28207086);
		requestHeaderBean.setUserRole("SSA");
		requestHeaderBean.setUserName("XXXXTEST01");
		return requestHeaderBean;
	}
}
