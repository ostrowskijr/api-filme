package br.com.ostrowskijr.apimovies.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.ostrowskijr.apimovies.model.Movie;

@Repository
public class CollectionMovies {

    private static String PATH = "src/main/resources/static/movielist.csv";
    private List<Movie> movies = new ArrayList<Movie>();

    public CollectionMovies() {
        //
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH)))) {
            //
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                //
                if (line != null) {
                    String[] lineFile = line.split(";");
                    //
                    Movie movie = new Movie();
                    movie.setYear(Integer.parseInt(getValueField(lineFile, 0)));
                    movie.setTitle(getValueField(lineFile, 1));
                    movie.setStudios(getValueField(lineFile, 2));
                    movie.setProducers(getValueField(lineFile, 3));
                    movie.setWinner(getValueField(lineFile, 4));
                    movies.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Movie> list() {
        return movies;
    }

    private String getValueField(String[] line, int position) {
        if (line.length < (position + 1)) {
            return "";
        }
        return line[position];
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
