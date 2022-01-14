package com.wipro.cloud.api.helloworld.domain;

public enum AdministrativeStatus {
	
	SINGLE("Single Admin"),
	DUAL_ADMIN("Dual Admin");
	
	private final String value;
	 AdministrativeStatus(final String value) {
		 this.value = value;
	}
	 
	 String description() {
		 return this.value;
	 }
}
