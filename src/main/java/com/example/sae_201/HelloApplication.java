package com.example.sae_201;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button mesJeuxButton;

    @FXML
    private Button tagsButton;

    @FXML
    private Button creerTagButton;

    @FXML
    private void handleMesJeuxButtonAction(ActionEvent event) throws IOException {
        loadScene("MainScreen.fxml");
    }

    @FXML
    private void handleTagsButtonAction(ActionEvent event) throws IOException {
        loadScene("TagsScreen.fxml");
    }

    @FXML
    private void handleCreerTagButtonAction(ActionEvent event) throws IOException {
        loadScene("CreateTagScreen.fxml");
    }

    private void loadScene(String fxml) throws IOException {
        Stage stage = (Stage) mesJeuxButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
