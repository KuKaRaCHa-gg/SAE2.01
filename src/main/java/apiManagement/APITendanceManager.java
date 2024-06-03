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

public class APITendanceManager {

    public List<Game> getMultipleGames(String searchedText) throws GameNotFoundException {
        List<Game> gamesList = new ArrayList<>();

        String searchedEncoded = "";
        try {
            searchedEncoded = URLEncoder.encode(searchedText, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }

        //Permet de recuperer les jeu en tendances
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.rawg.io/api/games"
						+ "?key=03aefea4690c4af5828591dca83a3c8f"
						+ "&ordering=-added"
						+ "&page_size=10"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;

		 /*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.rawg.io/api/games"
                        + "?key=03aefea4690c4af5828591dca83a3c8f"
                        + "&page_size=10"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

		 */
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

            for (int i = 0; i < result.getResults().length; i++) {
                Result gameResult = result.getResults()[i];
                Game game = new Game();
                game.setId(gameResult.getId());
                game.setName(gameResult.getName());
                game.setImageURL(gameResult.getBackgroundImage());

                // Get detailed information for each game
                HttpRequest newRequest = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.rawg.io/api/games/" + game.getId() + "?key=03aefea4690c4af5828591dca83a3c8f"))
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                HttpResponse<String> newResponse = null;
                try {
                    newResponse = HttpClient.newHttpClient().send(newRequest, HttpResponse.BodyHandlers.ofString());
                } catch (InterruptedException | IOException e) {
                    Thread.currentThread().interrupt();
                    return gamesList;
                }

                ObjectMapper detailedObjectMapper = new ObjectMapper();
                detailedObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

                Result detailedResult;
                try {
                    detailedResult = detailedObjectMapper.readValue(newResponse.body(), Result.class);
                    game.setDescription(detailedResult.getDescription());
                    game.setRate(detailedResult.getRating());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                gamesList.add(game);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return gamesList;
    }}
