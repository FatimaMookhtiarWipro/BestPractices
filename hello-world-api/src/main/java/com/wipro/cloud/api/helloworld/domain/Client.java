package com.wipro.cloud.api.helloworld.domain;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * 
 * @author LBG_ET 
 * Client refers to a commercial entity (or group of enities) registered with the bank.
 */

@Component
@JsonDeserialize(builder = Client.ClientBuilder.class)
public class Client {

	private long clientID;
	private String username;
	private String firstname;
	private String lastname;
	private Status status;
	private AdministrativeStatus adminStatus;
	private int passwordExpiryPeriod;

	public Client() {

	}

	public long getClientID() {
		return clientID;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Status getStatus() {
		return status;
	}

	public AdministrativeStatus getAdminStatus() {
		return adminStatus;
	}

	public int getPasswordExpiryPeriod() {
		return passwordExpiryPeriod;
	}

	@JsonPOJOBuilder
	public static class ClientBuilder {
		private long clientID;
		private String username;
		private String firstname;
		private String lastname;
		private Status status;
		private AdministrativeStatus adminStatus;
		private int passwordExpiryPeriod;

		public ClientBuilder withClientID(final long _clientID) {
			clientID = _clientID;
			return this;
		}

		public ClientBuilder withUsername(final String _username) {
			username = _username;
			return this;
		}

		public ClientBuilder withFirstname(final String _firstname) {
			firstname = _firstname;
			return this;
		}

		public ClientBuilder withLastname(final String _lastname) {
			lastname = _lastname;
			return this;
		}

		public ClientBuilder withStatus(final Status clientStatus) {
			status = clientStatus;
			return this;
		}

		public ClientBuilder withAdminStatus(final AdministrativeStatus clientAdminStatus) {
			adminStatus = clientAdminStatus;
			return this;
		}

		public ClientBuilder withPasswordExpiryPeriod(final int _passwordExpiryPeriod) {
			passwordExpiryPeriod = _passwordExpiryPeriod;
			return this;
		}

		public Client build() {
			return new Client(this);

		}

	}

	private Client(ClientBuilder builder) {
		this.clientID = builder.clientID;
		this.username = builder.username;
		this.firstname = builder.firstname;
		this.lastname = builder.lastname;
		this.status = builder.status;
		this.adminStatus = builder.adminStatus;
		this.passwordExpiryPeriod = builder.passwordExpiryPeriod;
	}

	@Override
	public String toString() {
		return "Client [clientID=" + clientID + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", status=" + status + ", adminStatus=" + adminStatus + ", passwordExpiryPeriod="
				+ passwordExpiryPeriod + "]";
	}



}
