package com.remy.chatapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ChatApiApplication {

	public static void main(String[] args) {
		//Load .env file
		Dotenv dotenv = Dotenv.configure().load();

		// Set system properties for Spring to pick up
		System.setProperty("OPENAI_API_KEY", dotenv.get("OPENAI_API_KEY"));
		System.setProperty("SOLAR_API_KEY", dotenv.get("SOLAR_API_KEY"));

		SpringApplication.run(ChatApiApplication.class, args);
	}

}
