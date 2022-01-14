package com.wipro.cloud.api.helloworld.repository;


import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.wipro.cloud.api.helloworld.domain.service.constants.RetrieveAgentConstants;

@Component
public class RetrieveAgentSqlParameterSourceFactory {

	public SqlParameterSource withParamsReturnSQLParamSource(Long partyId) {
		Map<String, Object> inParamMap = new HashMap<>();
		inParamMap.put(RetrieveAgentConstants.I_USER_NAME, null);
		inParamMap.put(RetrieveAgentConstants.I_USER_ID, partyId);
		inParamMap.put(RetrieveAgentConstants.I_ACTOR_USER_ID, null);
		inParamMap.put(RetrieveAgentConstants.I_ACTION_TYPE, "Login");
		return new MapSqlParameterSource(inParamMap);
	}

}
