package com.example.sae_201;



import apiManagement.APITendanceManager;
import apiManagement.GameNotFoundException;
import gameModel.Game;
import gameModel.MyGames;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
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

    //private ImageView['imageTendance1']

    @FXML
    private GridPane gridPane;

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
        int compteur = 0;
        int compteur2 = 0;
        List<Game> newGames;
        newGames = apiTendanceManager.getMultipleGames();

        // Ajoute tous les nouveaux jeux au modèle
        for (Game game : newGames) {
            model.addGame(game);
            VBox vBox = new VBox();
            Label label = new Label(game.getName());
            label.setTextFill(Paint.valueOf("white"));
            ImageView image = new ImageView(new Image (game.getImageURL(), gridPane.getPrefWidth()/5,100,true,true));
            vBox.getChildren().add(image);
            vBox.getChildren().add(label);
            vBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(game.getName());
                }
            });
            gridPane.add(vBox, compteur, compteur2);
            if (compteur == 4) {
                compteur = 0;
                compteur2++;
            } else {
                compteur++;
            }
        }
        persistentModelManager.save(model);

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


    public void handleMesJeuxButtonAction(ActionEvent event) {
    }

    public void handleCreerTagButtonAction(ActionEvent event) {
    }

    public void handleTagsButtonAction(ActionEvent event) {
    }
}
