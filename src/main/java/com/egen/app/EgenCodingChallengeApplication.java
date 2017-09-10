package com.egen.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Prasanna Kanago
 *
 * Egen coding challenge project using Spring Boot, MongoDb, 
 * Morphia and EasyRules
 * 
 */
@SpringBootApplication
public class EgenCodingChallengeApplication{

	private static final Logger LOGGER = LoggerFactory.getLogger(EgenCodingChallengeApplication.class);
	
	public static void main(String[] args) {
		LOGGER.debug("Starting the Egen Coding Challenge Application");
		SpringApplication.run(EgenCodingChallengeApplication.class, args);
	}
}
