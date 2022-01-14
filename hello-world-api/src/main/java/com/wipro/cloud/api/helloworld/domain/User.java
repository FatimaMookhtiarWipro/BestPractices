package com.wipro.cloud.api.helloworld.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


/**
 * 
 * @author LBG_ET 
 * User refers to a ....
 * < Generated-Code - Add attributes conforming to the the domain model. Use Builder pattern if the domain has many attributes.
 */
@Entity
@Component
public class User {

	@Id
	private long idForUser;
	private String name;

	public User() {

	}
	public User(long id) {
		this.idForUser = id;
	}

	public long getUserID() {
		return idForUser;
	}

	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		return "User [ifForUser=" + idForUser + ", name=" + name+"]";
	}



}
