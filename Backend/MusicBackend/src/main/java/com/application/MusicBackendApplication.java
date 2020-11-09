package com.application;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * This class is the entrypoint for the Spring Boot application
 * @author Tim Schommer
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableEncryptableProperties
@EntityScan("com.application.*")
public class MusicBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicBackendApplication.class, args);
	}

}
