package com.wipro.cloud.api.helloworld.infrastructure.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;


public final class JsonUtils {
	private static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);

	private JsonUtils() {
	}

	/**
	 * Serialize a JavaBeans object into JSON string.
	 * 
	 * @param p_value JavaBeans object
	 * @return JSON string
	 */
	public static String serialize(final Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (final JsonProcessingException e) {
			throw new InfrastructureServiceException(e,"Exception while processing");
		}
	}

	/**
	 *  Deserializes content into a Map
	 */
	public static <T> Map<String, T> deserializeIntoMap(String content, TypeReference<Map<String, T>> t) {
		try {

			return mapper.readValue(content, t);
		} catch (JsonProcessingException e) {
			throw new InfrastructureServiceException(e, "Exception while deserializing");
		}
	}

}
