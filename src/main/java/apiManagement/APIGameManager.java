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

public class APIGameManager {

    public List<Game> getInfoGame(int id){
        List<Game> gamesList = new ArrayList<>();

        HttpRequest newRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.rawg.io/api/games/" + id + "?key=03aefea4690c4af5828591dca83a3c8f"))
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
            Result detailedResult = detailedObjectMapper.readValue(newResponse.body(), Result.class);
            Game game = new Game();
            game.setDescription(detailedResult.getDescription());
            game.setRate(String.valueOf(detailedResult.getRating()));
            game.setName(String.valueOf(detailedResult.getName()));
            game.setImageURL(detailedResult.getBackgroundImage());
            //game.setPlatforms(detailedResult.getPlatforms().getPlatformName());
            System.out.println(game.getDescription());
            System.out.println(game.getRate());
            System.out.println(game.getName());

            gamesList.add(game);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return gamesList;
    }
}
