package com.example.sae_201;



import apiManagement.APIGameManager;
import apiManagement.APITendanceManager;
import apiManagement.GameNotFoundException;
import gameModel.Game;
import gameModel.MyGames;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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

        new Thread(initTask).start();
    }

    private void jeuSelectionner(int id) {
        List<Game> selectionGame = apiGameManager.getInfoGame(id);
        for (Game game : selectionGame) {
            int compteurGame = 0;
            gameInfoController.getRatingValueLabel().setText(game.getRate());
            gameInfoController.getRatingScaleLabel().setText(game.getRate() + "/5 étoiles");
            String htmlContent = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { background-color: #2e2e2e; color: white; }" +
                    "p { color: white; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" + game.getDescription() + "</body>" +
                    "</html>";
            gameInfoController.getWebDescView().getEngine().loadContent(htmlContent);

            gameInfoController.getBannerImageView().setImage(new Image(game.getImageURL()));
            VBox vBox1 = new VBox();
            Label label1 = new Label(game.getPlatforms()[0].getRequirementMinimum());
            label1.setTextFill(Paint.valueOf("white"));
            vBox1.getChildren().add(label1);
            gameInfoController.getRequirementGridPane().add(label1, 0,0);
            vBox1 = new VBox();
            label1 = new Label(game.getPlatforms()[0].getRequirementRecommended());
            label1.setTextFill(Paint.valueOf("white"));
            vBox1.getChildren().add(label1);
            gameInfoController.getRequirementGridPane().add(label1, 0,1);

            for (int i = 0; i<game.getPlatforms().length; i++) {
                VBox vBox = new VBox();
                Label label = new Label(game.getPlatforms()[i].getPlatformName());
                label.setTextFill(Paint.valueOf("white"));
                vBox.getChildren().add(label);
                gameInfoController.getPlateformeGridPane().add(vBox, 0, compteurGame);
                compteurGame++; }

            compteurGame = 0;
            for (int j = 0; j<game.getPublishers().length; j++){
                VBox vBox = new VBox();
                Label label = new Label(game.getPublishers()[j].getName());
                label.setTextFill(Paint.valueOf("white"));
                vBox.getChildren().add(label);
                gameInfoController.getEditorGridPane().add(vBox, 0, compteurGame);
                compteurGame++; }

            compteurGame = 0;
            for(int k = 0; k<game.getDevelopers().length; k++){
                VBox vBox = new VBox();
                Label label = new Label(game.getDevelopers()[k].getName());
                label.setTextFill(Paint.valueOf("white"));
                vBox.getChildren().add(label);
                gameInfoController.getDevGridPane().add(vBox, 0, compteurGame);
                compteurGame++; }
            
            compteurGame = 0;
            for (int m = 0;m<game.getTags().length; m++){
                Button vBox = new Button();
                vBox.setTextFill(Paint.valueOf("white"));
                vBox.setText(game.getTags()[m].getName());
                vBox.setStyle("-fx-background-color: #3a3a3a;");
                gameInfoController.getTagGridPane().add(vBox, 0, compteurGame);
                compteurGame++; }


        }
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
