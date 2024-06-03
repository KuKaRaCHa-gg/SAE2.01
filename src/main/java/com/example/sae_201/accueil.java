package com.example.sae_201;



import apiManagement.APITendanceManager;
import apiManagement.GameNotFoundException;
import gameModel.Game;
import gameModel.MyGames;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import persistence.PersistenceBySerialization;
import result.Result;

import java.util.List;


public class accueil {

    @FXML
    private HBox ajoutRecLabel;

    @FXML
    private HBox alphaLabel;

    @FXML
    private ImageView gameImage1;

    @FXML
    private ImageView gameImage10;

    @FXML
    private ImageView gameImage2;

    @FXML
    private ImageView gameImage3;

    @FXML
    private ImageView gameImage4;

    @FXML
    private ImageView gameImage5;

    @FXML
    private ImageView gameImage6;

    @FXML
    private ImageView gameImage7;

    @FXML
    private ImageView gameImage8;

    @FXML
    private ImageView gameImage9;

    @FXML
    private Label gameLabel1;

    @FXML
    private Label gameLabel10;

    @FXML
    private Label gameLabel2;

    @FXML
    private Label gameLabel3;

    @FXML
    private Label gameLabel4;

    @FXML
    private Label gameLabel5;

    @FXML
    private Label gameLabel6;

    @FXML
    private Label gameLabel7;

    @FXML
    private Label gameLabel8;

    @FXML
    private Label gameLabel9;

    @FXML
    private ImageView image;


    @FXML
    private ImageView imageTendance1;

    @FXML
    private ImageView imageTendance2;

    @FXML
    private ImageView imageTendance3;

    @FXML
    private ImageView imageTendance4;

    @FXML
    private ImageView imageTendance5;
    @FXML
    private VBox textBienvenu;

    private MyGames model;
    private PersistentModelManager persistentModelManager;
    private APITendanceManager apiTendanceManager;
    private Result result;

    public accueil(){
        super();
        apiTendanceManager = new APITendanceManager();
        model = new MyGames();
        persistentModelManager = new PersistenceBySerialization();
    }

    public void initialization() throws GameNotFoundException {
        List<Game> newGames;
        newGames = apiTendanceManager.getMultipleGames();

        // Ajoute tous les nouveaux jeux au modèle
        for (Game game : newGames) {
            model.addGame(game);
        }
        persistentModelManager.save(model);

        // Rafraîchit l'image du premier jeu et la liste complète
        if (!newGames.isEmpty()) {
            refreshImage(newGames.get(0));
        }
        refreshList();
    }


    private void refreshList() {
    }

    private void refreshImage(Game game) {
    }

    public void onTestClicked(javafx.event.ActionEvent event) {
        ajoutRecLabel.setOpacity(1);
        gameLabel1.setOpacity(1);
        gameLabel2.setOpacity(1);
        gameLabel3.setOpacity(1);
        gameLabel4.setOpacity(1);
        gameLabel5.setOpacity(1);
        textBienvenu.setOpacity(0);

    }





}
