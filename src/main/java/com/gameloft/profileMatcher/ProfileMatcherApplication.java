package com.gameloft.profileMatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("com.gameloft.profileMatcher.repository")
public class ProfileMatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileMatcherApplication.class, args);
	}

}
