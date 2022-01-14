package com.wipro.cloud.api.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wipro.cloud.api.helloworld"})
public class ServletInitializer extends SpringBootServletInitializer {

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HelloWorldApiApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApiApplication.class, args);
	}

}
