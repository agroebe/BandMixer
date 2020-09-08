package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.helloworld.HelloWorldController;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.people")
public class MusicBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicBackendApplication.class, args);
	}

}
