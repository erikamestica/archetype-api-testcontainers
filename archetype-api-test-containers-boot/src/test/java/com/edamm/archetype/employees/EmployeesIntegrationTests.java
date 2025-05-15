package com.edamm.archetype.employees;

import com.edamm.archetype.driving.controller.dtos.EmployeeDto;
import com.edamm.archetype.config.TestcontainersConfiguration;
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
		ResponseEntity<EmployeeDto[]> response = restTemplate.getForEntity("/employees", EmployeeDto[].class);

		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertNotNull(response);
		assertNotNull(response.getBody());

		List<EmployeeDto> employeeMOS = Arrays.asList(response.getBody());

		assertFalse(employeeMOS.isEmpty());
		assertEquals(1, employeeMOS.size());
		assertEquals("Alice", employeeMOS.get(0).getName());
		assertEquals("alice@example.com", employeeMOS.get(0).getEmail());
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
