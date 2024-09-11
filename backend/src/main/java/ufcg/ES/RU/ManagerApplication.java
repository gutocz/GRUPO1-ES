package ufcg.ES.RU;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "TCC Manager", version = "1", description = "API desenvolvida para a cadeira Projeto de Software para facilitar a definição de temas e orientações de TCC a partir de interesses comuns entre docentes e discentes"))
public class ManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}

}
