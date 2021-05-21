package br.com.ostrowskijr.apimovies;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.com.ostrowskijr.apimovies.controllers.WinnersController;

@SpringBootTest
@WebMvcTest(controllers = WinnersController.class)
class ApiMoviesApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testeWinners() throws Exception {
		
	}

	@Test
	public void testeMovies() throws Exception {

	}

}
