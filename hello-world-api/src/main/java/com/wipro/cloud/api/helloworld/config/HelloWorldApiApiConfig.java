package com.wipro.cloud.api.helloworld.config; 

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource(name = "appConfig", value = {"classpath:config/HelloWorldApiApiConfig.xml"}
,
factory = XmlPropertySourceFactory.class
)
public class HelloWorldApiApiConfig {
	@Value("${environment}")
	private String environment;
	
	@Value("${channelSchemaName}")
	private String channelSchemaName;
	
	@Value("${datasourceJNDI}")
	private String datasourceJNDI;
	
	@Value("${cwaAuditApiEndPointUrl}")
	private String cwaAuditApiEndPointUrl;

	/**
	 * @return the environment
	 */
	public String getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	/**
	 * @return the channelSchemaName
	 */
	public String getChannelSchemaName() {
		return channelSchemaName;
	}

	/**
	 * @param channelSchemaName the channelSchemaName to set
	 */
	public void setChannelSchemaName(String channelSchemaName) {
		this.channelSchemaName = channelSchemaName;
	}

	/**
	 * @return the cwaAuditApiEndPointUrl
	 */
	public String getCwaAuditApiEndPointUrl() {
		return cwaAuditApiEndPointUrl;
	}

	/**
	 * @param cwaAuditApiEndPointUrl the cwaAuditApiEndPointUrl to set
	 */
	public void setCwaAuditApiEndPointUrl(String cwaAuditApiEndPointUrl) {
		this.cwaAuditApiEndPointUrl = cwaAuditApiEndPointUrl;
	}
	
	/**
	 * @return the datasourceJNDI
	 */
	public String getDatasourceJNDI() {
		return datasourceJNDI;
	}

	/**
	 * @param datasourceJNDI the datasourceJNDI to set
	 */
	public void setDatasourceJNDI(String datasourceJNDI) {
		this.datasourceJNDI = datasourceJNDI;
	}
}

