package com.example.sae_201;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class rechercheController {
    @FXML
    private HBox alphaLabel;

    @FXML
    private GridPane gridRecherchePane;

    @FXML
    private TextField entrySearch;
    private Scene scene;
    private Stage stage;


    public GridPane getGridRecherchePane() {
        return gridRecherchePane;
    }

    public TextField getEntrySearch() {
        return entrySearch;
    }

    public void setNewScene(Scene gamePage) {
        this.scene = gamePage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }





}
