package com.example.sae_201;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("accueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 720);
        stage.setTitle("Gestionnaire de jeu");
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("Images/LOGO.png"))));
        stage.setScene(scene);
        stage.show();
        // test

    }

    public static void main(String[] args) {
        launch();
    }
}
