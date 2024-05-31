package com.example.sae_201;

import javafx.fxml.FXML;

public class PageJeuController {
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
