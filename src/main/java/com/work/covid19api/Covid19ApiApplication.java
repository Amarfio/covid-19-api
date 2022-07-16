package com.work.covid19api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Covid19ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19ApiApplication.class, args);
	}

	@Bean
	public OpenAPI openApiConfig(){
		return new OpenAPI().info(apiInfo());
	}

	public Info apiInfo(){
		Info info = new Info();

		info	.title("Covid API")
				.description("Covid 19 Tester Swagger Open API")
				.version("v1.0.0");
		return info;
	}

}
