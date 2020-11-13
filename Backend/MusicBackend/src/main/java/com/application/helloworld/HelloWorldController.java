package com.application.helloworld;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloWorldController 
{
	@RequestMapping("/hello")
	public String sayHello()
	{
		return "Goodbye from Spring Boot";
	}
}
