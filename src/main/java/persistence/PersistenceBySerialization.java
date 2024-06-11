package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import gameModel.Game;

public class PersistenceBySerialization {

    private static final String FILE_PATH = "mes_jeux.ser";

    public void saveGames(List<Game> games) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(games);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Game> loadGames() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Game>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}

