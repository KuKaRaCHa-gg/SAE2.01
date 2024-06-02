package com.example.sae_201;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PageJeuController {

    @FXML
    private ImageView logoImageView;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button tagButton;

    @FXML
    private ImageView loginImageView;

    @FXML
    private Button mesJeuxButton;

    @FXML
    private Button tagsButton;

    @FXML
    private Button creerTagButton;

    private Stage stage;

    @FXML
    private void initialize() {
        // Initialize the controller
    }

    @FXML
    private void handleMesJeuxButtonAction() {
        NavigationUtil.navigateTo(stage, "accueil.fxml");
    }

    @FXML
    private void handleTagsButtonAction() {
        NavigationUtil.navigateTo(stage, "RechercheParTAG.fxml");
    }

    @FXML
    private void handleCreerTagButtonAction() {
        NavigationUtil.navigateTo(stage, "Recherche.fxml");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
