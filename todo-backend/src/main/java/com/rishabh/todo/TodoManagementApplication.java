package com.rishabh.todo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*

	@SpringBootApplication annotation combines several other annotations
	to bootstrap a Spring application.

	It is commonly used to mark the main class of a Spring Boot application.
	Here's what it does:

	1. @Configuration: Indicates that the class provides bean definitions
	   and other configuration to the Spring application context.

	2. @EnableAutoConfiguration: Enables Spring Boot's automatic configuration
	   feature, which automatically configures beans based on classpath settings,
	   property settings, and other conditions.

	3. @ComponentScan: Specifies the base packages to scan for Spring
	   components such as controllers, services, repositories, etc.
	   By default, it scans the package of the class with the
	   @SpringBootApplication annotation and its sub-packages.
 */

@SpringBootApplication
public class TodoManagementApplication {

	/*
		@Bean annotation tells spring IoC container to manage modelMapper object

		ModelMapper is a third party library to map JPA entity into DTO entity and vice-versa
	 */
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(TodoManagementApplication.class, args);
	}

}
