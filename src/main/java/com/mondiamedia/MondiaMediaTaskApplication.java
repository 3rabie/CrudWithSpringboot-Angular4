package com.mondiamedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.mondiamedia","com.mondiamedia.controller"})
public class MondiaMediaTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MondiaMediaTaskApplication.class, args);
	}
}
