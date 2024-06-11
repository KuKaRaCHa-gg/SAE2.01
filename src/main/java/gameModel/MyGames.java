package gameModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyGames implements Serializable {
    private List<Game> games;

    public MyGames() {
        games = new ArrayList<>();
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public List<Game> getGames() {
        return games;
    }
}
