package com.example.sae_201;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("accueil.fxml"));
            Parent root = fxmlLoader.load();
            accueil accueilController = fxmlLoader.getController();
            Scene scene = new Scene(root, 1024, 720);
            stage.setTitle("Gestionnaire de jeu");
            stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("Images/LOGO.png"))));
            stage.setScene(scene);
            stage.show();
            accueilController.initialization();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
