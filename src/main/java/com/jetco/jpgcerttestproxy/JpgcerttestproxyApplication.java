package com.jetco.jpgcerttestproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class JpgcerttestproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpgcerttestproxyApplication.class, args);
	}

}
