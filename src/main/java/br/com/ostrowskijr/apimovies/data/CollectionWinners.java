package br.com.ostrowskijr.apimovies.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ostrowskijr.apimovies.model.Movie;
import br.com.ostrowskijr.apimovies.model.Winner;

@Repository
public class CollectionWinners {

    private final List<Winner> winners;
    private final CollectionMovies collectionMovies;

    @Autowired
    public CollectionWinners(CollectionMovies collectionMovies) {
        this.winners = new ArrayList<Winner>();
        this.collectionMovies = collectionMovies;
        //
        // Filtrar apenas os Filmes vencedores
        List<Movie> filterMovies = this.collectionMovies.getMovies().stream().filter(m -> m.getWinner().equals("yes"))
                .collect(Collectors.toList());
        for (Movie movie : filterMovies) {
            // Separar o nome dos produtores
            String[] producersName = movie.getProducers().replace(" and ", ",").split(",");
            for (int i = 0; i < producersName.length; i++) {
                String producer = producersName[i].trim();
                if (!producer.isEmpty()) {
                    // Verificar se o produtor já existe em Winners
                    Optional<Winner> optional = winners.stream().filter(winner -> winner.getProducer().equals(producer))
                            .findAny();
                    if (!optional.isPresent()) {
                        // Filtrar o movies que o produtor consta
                        List<Movie> producers = filterMovies.stream().filter(m -> m.getProducers().contains(producer))
                                .collect(Collectors.toList());
                        // Pegar o menor ano e maior ano de premio do produtor.
                        Movie min = producers.stream().min(Comparator.comparing(Movie::getYear)).get();
                        Movie max = producers.stream().max(Comparator.comparing(Movie::getYear)).get();
                        int interval = max.getYear() - min.getYear();
                        //
                        Winner winner = new Winner(producer, interval, min.getYear(), max.getYear());
                        winners.add(winner);
                    }
                }

            }
        }
    }

    public void persist(Winner winner) {
        winners.add(winner);
    }

    public List<Winner> getWinners() {
        List<Winner> winnerSort = winners.stream().filter(w -> w.getInterval() > 0).collect(Collectors.toList());
        winnerSort.sort(Comparator.comparing(Winner::getInterval));
        return winnerSort;
    }

}
