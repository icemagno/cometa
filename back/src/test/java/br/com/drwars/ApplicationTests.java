package br.com.drwars;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class ApplicationTests {

	@BeforeAll
	void contextLoads() {



	}

}
