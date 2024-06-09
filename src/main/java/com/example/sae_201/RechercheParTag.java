package com.example.sae_201;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RechercheParTag {
    public void handleMesJeuxButtonAction(ActionEvent event) {
        navigateTo(event, "mesJeux.fxml");
    }

    @FXML
    public void handleCreerTagButtonAction(ActionEvent event) {
        navigateTo(event, "CreerTag.fxml");
    }

    @FXML
    public void handleTagsButtonAction(ActionEvent event) {
        navigateTo(event, "RechercheParTAG.fxml");
    }

    private void navigateTo(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement du fichier FXML : " + e.getMessage());
        }
    }
}
