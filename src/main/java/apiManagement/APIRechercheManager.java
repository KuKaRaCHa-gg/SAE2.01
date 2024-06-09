package apiManagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gameModel.Game;
import result.Result;
import result.ResultGame;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class APIRechercheManager {

    public List<Game> getInfoGame(String textSearch)throws GameNotFoundException{
        List<Game> gamesList = new ArrayList<>();
        String searchedEncoded = "";
        try {
            searchedEncoded = URLEncoder.encode(textSearch, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
//https://api.rawg.io/api/games?key=03aefea4690c4af5828591dca83a3c8f&search=sonic&page_size=10&ordering=-added
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.rawg.io/api/games?key=03aefea4690c4af5828591dca83a3c8f"
                + "&search=" + searchedEncoded + "&page_size=12&ordering=-added"))
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
            result = objectMapper.readValue(response.body(), ResultGame.class);
            if (result.getResults().length == 0) {
                throw new GameNotFoundException();
            }

            for (Result gameResult : result.getResults()) {
                Game game = new Game();
                game.setId(gameResult.getId());
                game.setName(gameResult.getName());
                game.setImageURL(gameResult.getBackgroundImage());

                System.out.println(game.getName());
                gamesList.add(game);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return gamesList;
    }

}
