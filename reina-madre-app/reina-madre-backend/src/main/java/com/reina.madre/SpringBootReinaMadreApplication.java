package com.reina.madre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class SpringBootReinaMadreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReinaMadreApplication.class, args);
	}

}
