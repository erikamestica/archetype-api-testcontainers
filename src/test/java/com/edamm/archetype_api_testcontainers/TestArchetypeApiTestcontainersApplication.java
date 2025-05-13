package com.edamm.archetype_api_testcontainers;

import org.springframework.boot.SpringApplication;

public class TestArchetypeApiTestcontainersApplication {

	public static void main(String[] args) {
		SpringApplication.from(ArchetypeApiTestcontainersApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
