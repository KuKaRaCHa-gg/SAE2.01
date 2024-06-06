package gameModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MyGames implements Serializable {

    private List<Game> allGames;

    public MyGames() {
        super();
        allGames = new ArrayList<>();
    }

    public void addGame(Game newGame) {
        allGames.add(newGame);
    }

    public List<Game> getAllGames() {
        return allGames;
    }

    public void removeGame(Game game) {
        allGames.remove(game);
    }


}
