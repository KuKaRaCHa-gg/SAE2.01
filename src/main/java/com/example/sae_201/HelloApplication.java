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

            FXMLLoader fxmlsearchLoader = new FXMLLoader(HelloApplication.class.getResource("Recherche.fxml"));
            Parent searchRoot = fxmlsearchLoader.load();
            Scene searchPage = new Scene(searchRoot, 1225,745);
            accueil toSearchController = (accueil) fxmlLoader.getController();
            toSearchController.setSearchScene(searchPage);
            toSearchController.setSearchStage(stage);

            FXMLLoader fxmlBiblioLoader = new FXMLLoader(HelloApplication.class.getResource("mesJeu.fxml"));
            Parent biblioRoot = fxmlBiblioLoader.load();
            Scene biblioPage = new Scene(biblioRoot, 1225, 745);
            accueil toBiblioController = (accueil) fxmlLoader.getController();
            toBiblioController.setBliblioScene(biblioPage);
            toBiblioController.setBiblioStage(stage);
            PageJeuController jeuToBiblio = (PageJeuController) fxmlgameLoader.getController();
            jeuToBiblio.setBliblioScene(biblioPage);
            jeuToBiblio.setBiblioStage(stage);
            rechercheController searchToBiblio = (rechercheController) fxmlsearchLoader.getController();
            searchToBiblio.setBliblioScene(biblioPage);
            searchToBiblio.setBiblioStage(stage);

            FXMLLoader fxmlcreateTAGLoader = new FXMLLoader(HelloApplication.class.getResource("CreerTag.fxml"));
            Parent createTAGRoot =  fxmlcreateTAGLoader.load();
            Scene createTAGPage = new Scene(createTAGRoot, 1225,745);


            rechercheController searchGameController = fxmlsearchLoader.getController();
            toSearchController.setSearchController(searchGameController);


            rechercheController searchToJeu = (rechercheController) fxmlsearchLoader.getController();
            searchToJeu.setNewScene(gamePage);
            searchToJeu.setStage(stage);

            searchToJeu.setGameController(gameInfoController);
            gameInfoController.setSearchController(searchGameController);

            PageJeuController jeuToSearch = (PageJeuController) fxmlgameLoader.getController();
            jeuToSearch.setNewScene(searchPage);
            jeuToSearch.setStage(stage);

            MesJeuController biblioToSearch = (MesJeuController) fxmlBiblioLoader.getController();
            biblioToSearch.setSearchScene(searchPage);
            biblioToSearch.setSearchStage(stage);

            MesJeuController biblioToGame = (MesJeuController) fxmlBiblioLoader.getController();
            biblioToGame.setNewScene(gamePage);
            biblioToGame.setStage(stage);

            biblioToSearch.setSearchController(searchGameController);
            biblioToSearch.setGameController(gameInfoController);

            PageJeuController jeuToJeu = (PageJeuController) fxmlgameLoader.getController();
            jeuToJeu.setThisScene(gamePage);
            jeuToJeu.setStage(stage);

            MesJeuController biblioController = fxmlBiblioLoader.getController();
            jeuToJeu.setBiblioController(biblioController);


            accueil toTagController = (accueil) fxmlLoader.getController();
            toTagController.setTagScene(createTAGPage);
            toTagController.setTagStage(stage);

            PageJeuController JeuTagController = (PageJeuController) fxmlgameLoader.getController();
            JeuTagController.setTagScene(createTAGPage);
            JeuTagController.setTagStage(stage);

            MesJeuController biblioTagController = (MesJeuController) fxmlBiblioLoader.getController();
            biblioTagController.setTagScene(createTAGPage);
            biblioTagController.setTagStage(stage);

            rechercheController searchTagController = (rechercheController) fxmlsearchLoader.getController();
            searchTagController.setTagScene(createTAGPage);
            searchTagController.setTagStage(stage);

            CreerTagController TagtoSearch = (CreerTagController) fxmlcreateTAGLoader.getController();
            TagtoSearch.setSearchScene(searchPage);
            TagtoSearch.setSearchStage(stage);

            CreerTagController TagtoBiblio = (CreerTagController) fxmlcreateTAGLoader.getController();
            TagtoBiblio.setBliblioScene(biblioPage);
            TagtoBiblio.setBiblioStage(stage);

            CreerTagController TagtoGame = (CreerTagController) fxmlcreateTAGLoader.getController();
            TagtoGame.setNewScene(gamePage);
            TagtoGame.setStage(stage);

            TagtoGame.setGameController(gameInfoController);
            TagtoGame.setSearchController(searchGameController);





        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}
