package com.wipro.cloud.api.helloworld.domain;

public enum Status {
	ACTIVE(0,"Active"),
	INACTIVE(1,"InActive");
	
	private final int id;
	private final String description;

	Status(int id,String value) {
		this.id = id;
		this.description=value;
	}
	
	String description(){  
		return this.description;
	}
	
	int id() {
		return this.id;
	}

}
