package br.com.drwars;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@OpenAPIDefinition(
		info=@Info(title = "Biometano Trade", description = "Biometano")
)
@ServletComponentScan
@SpringBootApplication
@EntityScan(basePackages = {"br.com.drwars.entites"})
@EnableJpaRepositories(basePackages = {"br.com.drwars.repositories"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
