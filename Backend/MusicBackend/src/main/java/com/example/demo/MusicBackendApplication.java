package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicBackendApplication {

	public static void main(String[] args) {
		System.out.println("Hello world 1");
		SpringApplication.run(MusicBackendApplication.class, args);
		System.out.println("Hello world 2");
	}

}
