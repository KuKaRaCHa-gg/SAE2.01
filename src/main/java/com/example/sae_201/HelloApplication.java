package com.example.sae_201;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("accueil.fxml"));
            Parent root = fxmlLoader.load();
            accueil accueilController = fxmlLoader.getController();
            Scene scene = new Scene(root, 1225, 745);
            stage.setTitle("Gestionnaire de jeu");
            stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("Images/LOGO.png"))));
            stage.setScene(scene);

            // Préparer la fenêtre modale de chargement (pageChargement.fxml)
            FXMLLoader fxmlChargementLoader = new FXMLLoader(HelloApplication.class.getResource("pageChargement.fxml"));
            Parent loadRoot = fxmlChargementLoader.load();
            Scene sceneChargement = new Scene(loadRoot, 400, 200);

            Stage modalChargement = new Stage(StageStyle.DECORATED);
            modalChargement.initModality(Modality.APPLICATION_MODAL);
            modalChargement.initOwner(stage);
            modalChargement.setScene(sceneChargement);
            modalChargement.setTitle("Chargement...");
            accueilController.setChargement(modalChargement);
            modalChargement.show();

            // Simuler une tâche de chargement (vous pouvez la remplacer par votre logique de chargement)

            new Thread(() -> {
                try {
                    Thread.sleep(2000); // Simuler une attente de 2 secondes pour le chargement

                    // Fermer la fenêtre de chargement sur le thread JavaFX
                    javafx.application.Platform.runLater(() -> {
                        modalChargement.close();
                        stage.show(); // Afficher la fenêtre principale après le chargement
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            accueilController.initialization();


            FXMLLoader fxmlgameLoader = new FXMLLoader(HelloApplication.class.getResource("PageJeu.fxml"));
            Parent gameRoot = fxmlgameLoader.load();
            Scene gamePage = new Scene(gameRoot, 1225, 745);
            accueil toJeuController = (accueil) fxmlLoader.getController();
            toJeuController.setNewScene(gamePage);
            toJeuController.setStage(stage);

            PageJeuController gameInfoController = fxmlgameLoader.getController();
            toJeuController.setGameController(gameInfoController);




            //ChargementController controller = fxmlChargementLoader.getController();
            //controller.updateProgress(0.5);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}
