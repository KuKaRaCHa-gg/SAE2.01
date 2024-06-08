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
            Scene sceneChargement = new Scene(loadRoot, 600, 300);

            Stage modalChargement = new Stage(StageStyle.DECORATED);
            modalChargement.initModality(Modality.APPLICATION_MODAL);
            modalChargement.initOwner(stage);
            modalChargement.setScene(sceneChargement);
            modalChargement.setTitle("Chargement...");
            modalChargement.show();

            accueilController.setChargement(modalChargement);
            accueilController.initialization();

            FXMLLoader fxmlgameLoader = new FXMLLoader(HelloApplication.class.getResource("PageJeu.fxml"));
            Parent gameRoot = fxmlgameLoader.load();
            Scene gamePage = new Scene(gameRoot, 1225, 745);
            accueil toJeuController = (accueil) fxmlLoader.getController();
            toJeuController.setNewScene(gamePage);
            toJeuController.setStage(stage);

            PageJeuController gameInfoController = fxmlgameLoader.getController();
            toJeuController.setGameController(gameInfoController);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}
