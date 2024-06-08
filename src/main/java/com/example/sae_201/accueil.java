package com.example.sae_201;



import apiManagement.APIGameManager;
import apiManagement.APITendanceManager;
import apiManagement.GameNotFoundException;
import gameModel.Game;
import gameModel.MyGames;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import persistence.PersistenceBySerialization;
import result.Result;

import java.util.List;


public class accueil {

    @FXML
    private HBox ajoutRecLabel;

    @FXML
    private HBox alphaLabel;

    @FXML
    private ImageView gameImage1;

    @FXML
    private ImageView gameImage10;

    @FXML
    private ImageView gameImage2;

    @FXML
    private ImageView gameImage3;

    @FXML
    private ImageView gameImage4;

    @FXML
    private ImageView gameImage5;

    @FXML
    private ImageView gameImage6;

    @FXML
    private ImageView gameImage7;

    @FXML
    private ImageView gameImage8;

    @FXML
    private ImageView gameImage9;

    @FXML
    private Label gameLabel1;

    @FXML
    private Label gameLabel10;

    @FXML
    private Label gameLabel2;

    @FXML
    private Label gameLabel3;

    @FXML
    private Label gameLabel4;

    @FXML
    private Label gameLabel5;

    @FXML
    private Label gameLabel6;

    @FXML
    private Label gameLabel7;

    @FXML
    private Label gameLabel8;

    @FXML
    private Label gameLabel9;

    @FXML
    private ImageView image;



    @FXML
    private ImageView imageTendance1;

    @FXML
    private ImageView imageTendance2;

    @FXML
    private ImageView imageTendance3;

    @FXML
    private ImageView imageTendance4;

    @FXML
    private ImageView imageTendance5;

    //private ImageView['imageTendance1']

    @FXML
    private GridPane gridPane;

    @FXML
    private VBox textBienvenu;

    private MyGames model;
    private PersistentModelManager persistentModelManager;
    private APITendanceManager apiTendanceManager;
    private APIGameManager apiGameManager;
    private Result result;
    private Scene scene;
    private Stage stage;
    private PageJeuController gameInfoController;
    private int compteur = 0;
    private int compteur2 = 0;

    private Stage modalChargement;

    public accueil(){
        super();
        apiTendanceManager = new APITendanceManager();
        apiGameManager = new APIGameManager();
        model = new MyGames();
        persistentModelManager = new PersistenceBySerialization();
    }

    public void setChargement(Stage modalChargement) {
        this.modalChargement = modalChargement;
    }

    public void setNewScene(Scene gamePage) {
        this.scene = gamePage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialization() {
        Task<Void> initTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                List<Game> newGames = apiTendanceManager.getMultipleGames();

                // Ajoute tous les nouveaux jeux au modèle
                for (Game game : newGames) {
                    model.addGame(game);
                    Platform.runLater(() -> {
                        VBox vBox = new VBox();
                        Label label = new Label(game.getName());
                        label.setTextFill(Paint.valueOf("white"));
                        ImageView image = new ImageView(new Image(game.getImageURL(), gridPane.getPrefWidth() / 4, 250, true, true));
                        vBox.getChildren().add(image);
                        vBox.getChildren().add(label);
                        vBox.setOnMouseClicked(mouseEvent -> {
                            jeuSelectionner(game.getId());
                            Stage stage = (Stage) vBox.getScene().getWindow();
                            stage.setScene(scene);
                        });
                        gridPane.add(vBox, compteur, compteur2);
                        if (compteur == 3) {
                            compteur = 0;
                            compteur2++;
                        } else {
                            compteur++;
                        }
                    });
                }
                persistentModelManager.save(model);
                return null;
            }

            @Override
            protected void succeeded() {
                Platform.runLater(() -> {
                    if (modalChargement != null) {
                        modalChargement.close();
                    }
                    if (stage != null) {
                        stage.show();
                    }
                });
            }

            @Override
            protected void failed() {
                Platform.runLater(() -> {
                    if (modalChargement != null) {
                        modalChargement.close();
                    }
                    // Optionnel : Afficher un message d'erreur ou effectuer d'autres actions en cas d'échec
                });
            }
        };

        // Démarrer le Task d'initialisation dans un nouveau thread
        new Thread(initTask).start();
    }

    private void jeuSelectionner(int id) {
        List<Game> selectionGame = apiGameManager.getInfoGame(id);
        for (Game game : selectionGame) {
            gameInfoController.getRatingValueLabel().setText(game.getRate());
            gameInfoController.getRatingScaleLabel().setText(game.getRate() + "/5 étoiles");
            gameInfoController.getDescriptionTextArea().setText(game.getDescription());
            gameInfoController.getBannerImageView().setImage(new Image(game.getImageURL()));
            gameInfoController.getBannerImageView().setFitWidth(1000);
            System.out.println(game.getRate());
        }
    }

    public void onTestClicked(ActionEvent event) {
        ajoutRecLabel.setOpacity(1);
        gameLabel1.setOpacity(1);
        gameLabel2.setOpacity(1);
        gameLabel3.setOpacity(1);
        gameLabel4.setOpacity(1);
        gameLabel5.setOpacity(1);
        textBienvenu.setOpacity(0);
    }

    public void handleMesJeuxButtonAction(ActionEvent event) {
    }

    public void handleCreerTagButtonAction(ActionEvent event) {
    }

    public void handleTagsButtonAction(ActionEvent event) {
    }

    public void setGameController(PageJeuController gameInfoController) {
        this.gameInfoController = gameInfoController;
    }
}
