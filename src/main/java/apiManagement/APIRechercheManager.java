package apiManagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gameModel.Game;
import result.Result;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class APIRechercheManager {

    public List<Game> getInfoGame(String textSearch){
        List<Game> gamesList = new ArrayList<>();
//https://api.rawg.io/api/games?key=03aefea4690c4af5828591dca83a3c8f&search=sonic&page_size=10&ordering=-added
        HttpRequest newRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.rawg.io/api/games?key=03aefea4690c4af5828591dca83a3c8f"
                + "&search=" + textSearch + "&page_size=10&ordering=-added"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> newResponse;
        try {
            newResponse = HttpClient.newHttpClient().send(newRequest, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
            return gamesList;
        }

        ObjectMapper detailedObjectMapper = new ObjectMapper();
        detailedObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            Result gameResult = detailedObjectMapper.readValue(newResponse.body(), Result.class);
            Game game = new Game();
            game.setId(gameResult.getId());
            game.setName(gameResult.getName());
            game.setImageURL(gameResult.getBackgroundImage());

            System.out.println(game.getName());



            gamesList.add(game);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return gamesList;
    }

}
