package com.example.movies_and_ratings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MoviesAndRatingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesAndRatingsApplication.class, args);
	}


}
