package br.com.ostrowskijr.apimovies.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ostrowskijr.apimovies.data.CollectionMovies;
import br.com.ostrowskijr.apimovies.model.Movie;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final CollectionMovies movies;

    @Autowired
    public MoviesController(CollectionMovies movies) {
        this.movies = movies;
    }

    @GetMapping("/list")
    public List<Movie> listMovies() {
        return movies.list().stream().filter((Movie m) -> m.getProducers().contains("Kevin"))
                .collect(Collectors.toList());
    }

}
