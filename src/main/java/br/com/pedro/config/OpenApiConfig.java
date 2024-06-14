package br.com.pedro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI custonOpemAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTfull API with Java 18 and Spring Boot 3")
						.version("v1")
						.description("Some description about my API")
						.termsOfService("https://www.udemy.com/course/restful-apis-do-0-a-nuvem-com-springboot-e-docker/learn/lecture/32598654#content")
						.license(new License().name("Apache 2.0")
								.url("https://www.udemy.com/course/restful-apis-do-0-a-nuvem-com-springboot-e-docker/learn/lecture/32598654#content")));
	}

}
