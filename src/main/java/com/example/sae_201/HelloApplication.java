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
            FXMLLoader accueilLoader = new FXMLLoader(HelloApplication.class.getResource("accueil.fxml"));
            Parent accueilRoot = accueilLoader.load();
            accueil accueilController = accueilLoader.getController();
            Scene accueilScene = new Scene(accueilRoot, 1225, 745);
            stage.setTitle("Gestionnaire de jeu");
            stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("Images/LOGO.png"))));
            stage.setScene(accueilScene);

            // Préparer la fenêtre modale de chargement (pageChargement.fxml)
            FXMLLoader chargementLoader = new FXMLLoader(HelloApplication.class.getResource("pageChargement.fxml"));
            Parent chargementRoot = chargementLoader.load();
            Scene chargementScene = new Scene(chargementRoot, 600, 300);

            Stage modalChargement = new Stage(StageStyle.DECORATED);
            modalChargement.initModality(Modality.APPLICATION_MODAL);
            modalChargement.initOwner(stage);
            modalChargement.setScene(chargementScene);
            modalChargement.setTitle("Chargement...");
            modalChargement.show();

            accueilController.setChargement(modalChargement);
            accueilController.initialization();

            // Chargement des autres contrôleurs
            FXMLLoader gameLoader = new FXMLLoader(HelloApplication.class.getResource("PageJeu.fxml"));
            Parent gameRoot = gameLoader.load();
            PageJeuController gameController = gameLoader.getController();
            Scene gameScene = new Scene(gameRoot, 1225, 745);

            FXMLLoader searchLoader = new FXMLLoader(HelloApplication.class.getResource("Recherche.fxml"));
            Parent searchRoot = searchLoader.load();
            rechercheController searchController = searchLoader.getController();
            Scene searchScene = new Scene(searchRoot, 1225, 745);

            FXMLLoader biblioLoader = new FXMLLoader(HelloApplication.class.getResource("mesJeu.fxml"));
            Parent biblioRoot = biblioLoader.load();
            MesJeuController biblioController = biblioLoader.getController();
            Scene biblioScene = new Scene(biblioRoot, 1225, 745);

            // Configurez les scènes et les contrôleurs
            accueilController.setNewScene(gameScene);
            accueilController.setStage(stage);
            accueilController.setSearchScene(searchScene);
            accueilController.setSearchStage(stage);
            accueilController.setBliblioScene(biblioScene);
            accueilController.setBiblioStage(stage);
            accueilController.setGameController(gameController);
            accueilController.setSearchController(searchController);

            gameController.setNewScene(searchScene);
            gameController.setStage(stage);
            gameController.setBliblioScene(biblioScene);
            gameController.setBiblioStage(stage);
            gameController.setSearchController(searchController);
            gameController.setThisScene(gameScene);
            gameController.setBiblioController(biblioController);

            searchController.setNewScene(gameScene);
            searchController.setStage(stage);
            searchController.setBliblioScene(biblioScene);
            searchController.setBiblioStage(stage);
            searchController.setGameController(gameController);

            biblioController.setNewScene(gameScene);
            biblioController.setStage(stage);
            biblioController.setSearchScene(searchScene);
            biblioController.setSearchStage(stage);
            biblioController.setSearchController(searchController);
            biblioController.setGameController(gameController);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
