package br.com.ostrowskijr.apimovies.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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
                // Verificar se o nome do produtor é válido
                if (!producer.isEmpty()) {
                    // Verificar se o produtor já existe em Winners
                    Optional<Winner> optional = winners.stream().filter(winner -> winner.getProducer().equals(producer))
                            .findAny();
                    if (!optional.isPresent()) {
                        // Listar os anos em que o produtor obteve prêmio
                        List<Integer> dates = filterMovies.stream().filter(m -> m.getProducers().contains(producer))
                                .map(Movie::getYear).collect(Collectors.toList());
                        dates.sort(Comparator.naturalOrder());
                        if (dates.size() > 1) {
                            System.out.println("Dates Producers: " + dates.toString());
                            // Guardar todos os prêmios do produtor
                            try {
                                int firstYear = 0;
                                int lastYear = 0;
                                int interval = 0;
                                for (int index = 0; index < dates.size(); index++) {
                                    if (firstYear == 0) {
                                        firstYear = dates.get(index);
                                    } else {
                                        firstYear = lastYear;
                                    }
                                    lastYear = dates.get(index);
                                    // Calcular o Intervalo entre os prêmios
                                    interval = lastYear - firstYear;
                                    Winner winner = new Winner(producer, interval, firstYear, lastYear);
                                    winners.add(winner);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        }
    }

    public List<Winner> getWinners() {
        return winners;
    }

    public String getIntervalWinners() {
        JsonObject json = new JsonObject();
        JsonArray min = new JsonArray();
        JsonArray max = new JsonArray();
        // Filtrar apenas produtores com intervalado de prêmio maior que 0, para
        // descartar produtores com apenas 1 prêmio.
        List<Winner> winnerSort = winners.stream().filter(w -> w.getInterval() > 0).collect(Collectors.toList());
        // Ordernar do menor para o maior intevalo do prêmio
        winnerSort.sort(Comparator.comparing(Winner::getInterval));
        // Pegar os dois primeiros produtores que conseguiran conquistar 2 prêmios em
        // menor tempo.
        Winner w = winnerSort.get(0);
        min.add(createJsonObject(w.getProducer(), w.getInterval(), w.getPreviousWin(), w.getFollowingWin()));
        w = winnerSort.get(1);
        min.add(createJsonObject(w.getProducer(), w.getInterval(), w.getPreviousWin(), w.getFollowingWin()));
        json.add("min", min);
        // Pegar os dois últimos produtores que conseguiran conquistar 2 prêmios em
        // maior tempo.
        int lastWin = winnerSort.size() - 1;
        w = winnerSort.get(lastWin - 1);
        max.add(createJsonObject(w.getProducer(), w.getInterval(), w.getPreviousWin(), w.getFollowingWin()));
        w = winnerSort.get(lastWin);
        max.add(createJsonObject(w.getProducer(), w.getInterval(), w.getPreviousWin(), w.getFollowingWin()));
        json.add("max", max);
        //
        return json.toString();
    }

    private JsonObject createJsonObject(String productor, int interval, int previousWin, int folowingWin) {
        JsonObject json = new JsonObject();
        json.add("productor", new JsonPrimitive(productor));
        json.add("interval", new JsonPrimitive(interval));
        json.add("previousWin", new JsonPrimitive(previousWin));
        json.add("folowingWin", new JsonPrimitive(folowingWin));
        return json;
    }

}
