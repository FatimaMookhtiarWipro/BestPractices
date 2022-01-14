
package com.wipro.cloud.api.helloworld.infrastructure.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import com.wipro.cloud.api.helloworld.infrastructure.exception.*;

//import com.lbg.cbo.HelloWorldApi.infrastructure.exception.InfrastructureServiceException;

// Toggle the profile back to !test
@Profile("prod")
@Configuration
public class DataSourceConfiguration {

	/**
	 * Datasource JNDI
	 */
	@Value("${datasourceJNDI}")
	private String datasourceJNDI;

	/**
	 * @return the datasourceJNDI
	 */
	public String getDatasourceJNDI() {
		return datasourceJNDI;
	}

	/**
	 * @param datasourceJNDI : String the datasourceJNDI to set
	 */
	public void setDatasourceJNDI(String datasourceJNDI) {
		this.datasourceJNDI = datasourceJNDI;
	}

	/**
	 * Method to create the data source.
	 * 
	 * @throws: IllegalArgumentException
	 * @return Datasource
	 */
	@Bean(name = "DataSource")
	DataSource dataSource() {
		DataSource datasource;
		try {
			JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
			datasource = dataSourceLookup.getDataSource(getDatasourceJNDI());

		} catch (DataSourceLookupFailureException lookupFailure) {

			throw new InfrastructureServiceException(lookupFailure, "Exception while instanciating Datasource");
		} catch (Exception exception) {
			throw new InfrastructureServiceException(exception, "Exception while instanciating Datasource");

		}

		return datasource;
	}
}
