package com.wipro.cloud.api.helloworld.config; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.Resource;

import  com.wipro.cloud.api.helloworld.infrastructure.service.JsonUtils;
import com.wipro.cloud.api.helloworld.infrastructure.exception.ErrorDetail;
import com.wipro.cloud.api.helloworld.infrastructure.exception.InfrastructureServiceException;
import com.wipro.cloud.api.helloworld.infrastructure.exception.ErrorConstants;


@Configuration
public class ErrorDetailConfig {


    @Autowired
	private ResourceLoader resourceLoader;


 	/**
	 * @param fileResource
	 * @return the String of the ErrorDetail.
	 * @throws IOException
	 */
	public String readErrorFilesAsString(Resource fileResource) throws IOException {
		InputStream resource = fileResource.getInputStream();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8))) {
			return reader.lines().collect(Collectors.joining("\n"));
		}
	}

	/**
	 * @return the map of ErrorCodeDetails
	 */
	@Bean(name = "errorcodes")
	public Map<String, ErrorDetail> errorCodes() {
		Resource fileResource = resourceLoader.getResource("classpath:config/errors/ErrorCodeDesc.json");
		Map<String, ErrorDetail> error = new HashMap<>();

		try {
			error = JsonUtils.deserializeIntoMap(readErrorFilesAsString(fileResource),
					new TypeReference<Map<String, ErrorDetail>>() {
					});
		} catch (IOException e) {
			throw new InfrastructureServiceException("Exception while configuring AuthAuditApi ErrorCode Map");
		}
		return error;
	}

}
