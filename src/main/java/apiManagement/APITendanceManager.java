package apiManagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gameModel.Game;
import result.Result;
import result.ResultGame;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class APITendanceManager {

    public List<Game> getMultipleGames() throws GameNotFoundException {
        List<Game> gamesList = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.rawg.io/api/games"
                        + "?key=03aefea4690c4af5828591dca83a3c8f"
                        + "&ordering=-added"
                        + "&page_size=16"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
            return gamesList;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        ResultGame result;
        try {
            if ((objectMapper.readValue(response.body(), ResultGame.class)) == null) {
                System.out.println("Erreur");
            }
            result = objectMapper.readValue(response.body(), ResultGame.class);
            if (result.getResults().length == 0) {
                throw new GameNotFoundException();
            }

            for (Result gameResult : result.getResults()) {
                Game game = new Game();
                game.setId(gameResult.getId());
                game.setName(gameResult.getName());
                game.setImageURL(gameResult.getBackgroundImage());

                gamesList.add(game);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return gamesList;
    }
}