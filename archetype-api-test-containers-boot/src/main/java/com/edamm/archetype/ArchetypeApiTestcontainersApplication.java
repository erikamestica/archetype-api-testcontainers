package com.edamm.archetype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.edamm.archetype")
public class ArchetypeApiTestcontainersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchetypeApiTestcontainersApplication.class, args);
	}

}
