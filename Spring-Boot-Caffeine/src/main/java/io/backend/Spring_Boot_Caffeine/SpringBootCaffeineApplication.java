package io.backend.Spring_Boot_Caffeine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootCaffeineApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootCaffeineApplication.class, args);
	}

}
