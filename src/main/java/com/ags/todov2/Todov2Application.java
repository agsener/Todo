package com.ags.todov2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport
@SpringBootApplication
@EnableMongoAuditing
public class Todov2Application {

	public static void main(String[] args) {
		SpringApplication.run(Todov2Application.class, args);
	}

}
