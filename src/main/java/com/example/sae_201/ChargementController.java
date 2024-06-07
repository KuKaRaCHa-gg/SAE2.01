package com.example.sae_201;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class ChargementController {

    @FXML
    private ProgressBar progressBar;

    public void initialize() {
        // Initialiser la ProgressBar ici si nécessaire
        progressBar.setProgress(0.0);
        startTask();
    }

    // Méthode pour mettre à jour la ProgressBar
    public void updateProgress(double progress) {
        progressBar.setProgress(progress);
    }
    public void startTask() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(100);
                    updateProgress(i, 100);
                }
                return null;
            }
        };

        progressBar.progressProperty().bind(task.progressProperty());

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

}
