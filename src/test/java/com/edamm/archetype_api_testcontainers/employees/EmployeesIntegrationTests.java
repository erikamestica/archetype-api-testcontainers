package com.edamm.archetype_api_testcontainers.employees;

import com.edamm.archetype_api_testcontainers.Employee;
import com.edamm.archetype_api_testcontainers.config.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeesIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/sql/employees/setup.sql")
	public void testFindAllEmployees(){
		// Llamar al endpoint GET /employees
		ResponseEntity<Employee[]> response = restTemplate.getForEntity("/employees", Employee[].class);

		assertTrue(response.getStatusCode().is2xxSuccessful());
		List<Employee> employees = Arrays.asList(response.getBody());

		assertFalse(employees.isEmpty());
		assertEquals(1, employees.size());
		assertEquals("Alice", employees.get(0).getName());
		assertEquals("alice@example.com", employees.get(0).getEmail());
	}

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
