package com.example.sae_201;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        NavigationUtil.navigateTo(primaryStage, "accueil.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
