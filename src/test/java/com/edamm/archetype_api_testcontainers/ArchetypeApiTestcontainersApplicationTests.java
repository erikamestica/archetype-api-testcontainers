package com.edamm.archetype_api_testcontainers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArchetypeApiTestcontainersApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testCreateEmployee() {
		// Datos del empleado que vamos a crear
		String requestJson = """
            {
                "name":"erik",
                "email":"erikamesticalarrinaga@gmail.es"
            }
        """;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON); // Establecemos el Content-Type a application/json
		HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

		// Realizamos la solicitud con el encabezado correcto
		ResponseEntity<String> response = restTemplate.exchange("/employees", HttpMethod.POST, entity, String.class);

		// Verificamos que la respuesta tenga el estado 201 (CREATED)
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		// También puedes hacer una comprobación más detallada si lo deseas, como verificar el contenido de la respuesta.
	}

}
