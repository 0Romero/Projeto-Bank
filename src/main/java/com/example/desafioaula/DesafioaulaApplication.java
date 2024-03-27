package com.example.desafioaula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DesafioaulaApplication {

	private final String projectName = "API de Gestão de Banco Digital";
	private final String[] teamMembers = { "Felipe Bernardes", "Guilherme Romero" };

	@GetMapping("/")
	public String index() {
		StringBuilder response = new StringBuilder();
		response.append("Projeto: ").append(projectName).append("\n");
		response.append("Equipe: ");
		for (String member : teamMembers) {
			response.append(member).append(", ");
		}

		response.deleteCharAt(response.length() - 2);
		return response.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(DesafioaulaApplication.class, args);
	}
}
