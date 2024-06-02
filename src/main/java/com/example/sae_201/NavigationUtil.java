package com.example.sae_201;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationUtil {

    public static void navigateTo(Stage stage, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationUtil.class.getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            Object controller = loader.getController();
            if (controller instanceof MainScreenController) {
                ((MainScreenController) controller).setStage(stage);
            } else if (controller instanceof HelloController) {
                ((HelloController) controller).setStage(stage);
            } else if (controller instanceof PageJeuController) {
                ((PageJeuController) controller).setStage(stage);
            }
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
