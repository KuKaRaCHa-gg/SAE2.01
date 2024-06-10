package gameModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILE_PATH = "mes_jeux.txt";

    public static void saveGames(List<Game> games) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Game game : games) {
                writer.println(game.getId());
                writer.println(game.getName());
                writer.println(game.getImageURL());
                // Ajoutez d'autres propriétés de Game ici si nécessaire
                writer.println(); // Ligne vide pour séparer les jeux
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Game> loadGames() {
        List<Game> games = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int id = Integer.parseInt(line);
                String name = reader.readLine();
                String imageURL = reader.readLine();
                // Ajoutez d'autres propriétés de Game ici si nécessaire
                reader.readLine(); // Lire la ligne vide qui sépare les jeux
                Game game = new Game();
                game.setId(id);
                game.setName(name);
                game.setImageURL(imageURL);
                // Ajoutez d'autres propriétés de Game ici si nécessaire
                games.add(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return games;
    }
}
