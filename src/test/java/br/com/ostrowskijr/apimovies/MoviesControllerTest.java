package br.com.ostrowskijr.apimovies;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.ostrowskijr.apimovies.controllers.MoviesController;
import br.com.ostrowskijr.apimovies.data.CollectionMovies;
import br.com.ostrowskijr.apimovies.model.Movie;
import br.com.ostrowskijr.apimovies.utils.JsonUtils;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MoviesController.class)
public class MoviesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CollectionMovies collectionMovies;

    @Test
    public void getMoviesIsOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/movies")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getMoviesNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/movies/list"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getMoviesList() throws Exception {
        List<Movie> movies = new ArrayList<Movie>();        
        //
        when(this.collectionMovies.getMovies()).thenReturn(movies);
        //
        this.mockMvc.perform(MockMvcRequestBuilders.get("/movies").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
                        .json(JsonUtils.parseToJson(movies)));

    }
}
