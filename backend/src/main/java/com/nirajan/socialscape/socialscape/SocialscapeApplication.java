package com.nirajan.socialscape.socialscape;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocialscapeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialscapeApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {
		return runner -> {
			System.out.println("HELLO WORLD!!!");
		};
	}

}
