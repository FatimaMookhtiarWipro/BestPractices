package com.wipro.cloud.api.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wipro.cloud.api.helloworld"})
public class HelloWorldApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApiApplication.class, args);
    }
}
