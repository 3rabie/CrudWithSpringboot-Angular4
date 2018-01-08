package com.mondiamedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(basePackages = "com.mondiamedia.controller")
@PropertySource("classpath:swagger.properties")
@Configuration
public class SwaggerConfig {

	private static final String VERSION = "1.0";
	private static final String LICENSE = "This app is Open Source software released under the Apache 2.0 license.";
	private static final String title = "MondiaMedia Senior Java Developer Assignment";
	private static final String description = "Springboot CRUD Back-end RESTful JSON APIs and It’s Front-end";
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(title)
				.description(description)
				.license(LICENSE)
				.version(VERSION)
				.build();
	}
	
	
	@Bean
	public Docket mondiaMediaTask() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.pathMapping("/")
				.select()
				.paths(PathSelectors.regex("/api.*"))
				.build();
	}
}
