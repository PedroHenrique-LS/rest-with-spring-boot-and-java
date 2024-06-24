package br.com.pedro.integrationtests.swagger;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.pedro.configs.TesteConfigs;
import br.com.pedro.integrationtests.testcontainers.AbstractIntegrationTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntregrationTest extends AbstractIntegrationTest{

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content = 
			given()
				.basePath("/swagger-ui/index.html")
				.port(TesteConfigs.SERVER_PORT)
				.when()
					.get()
				.then()
					.statusCode(200)
				.extract()
					.body()
						.asString();
		assertTrue(content.contains("Swagger UI"));
	}

}
