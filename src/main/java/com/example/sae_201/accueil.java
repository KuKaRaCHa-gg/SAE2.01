package com.example.sae_201;



import apiManagement.APIGameManager;
import apiManagement.APIRechercheManager;
import apiManagement.APITendanceManager;
import apiManagement.GameNotFoundException;
import gameModel.Game;
import gameModel.MyGames;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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

    @FXML
    private TextField entryAccueil;

    private MyGames model;
    private PersistentModelManager persistentModelManager;
    private APITendanceManager apiTendanceManager;
    private APIGameManager apiGameManager;
    private APIRechercheManager apiRechercheManager;
    private Result result;
    private Scene scene;
    private Stage stage;
    private Scene searchScene;
    private Stage searchStage;
    private PageJeuController gameInfoController;
    private rechercheController searchGameController;
    private int compteur = 0;
    private int compteur2 = 0;
    private int compteurX = 0;
    private int compteurY = 0;

    private Stage modalChargement;

    public accueil(){
        super();
        apiTendanceManager = new APITendanceManager();
        apiGameManager = new APIGameManager();
        apiRechercheManager = new APIRechercheManager();
        model = new MyGames();
        persistentModelManager = new PersistenceBySerialization();
        gameInfoController = new PageJeuController();
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

    public void jeuSelectionner(int id) {
        List<Game> selectionGame = apiGameManager.getInfoGame(id);

        gameInfoController.getRequirementGridPane().getChildren().clear();
        gameInfoController.getPlateformeGridPane().getChildren().clear();
        gameInfoController.getTagGridPane().getChildren().clear();
        gameInfoController.getDevGridPane().getChildren().clear();
        gameInfoController. getEditorGridPane().getChildren().clear();
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
            Text text1 = new Text(game.getPlatforms()[0].getRequirementMinimum());
            text1.setFill(Paint.valueOf("white"));
            TextFlow textFlow1 = new TextFlow(text1);
            textFlow1.setMaxWidth(800);
            vBox1.getChildren().add(textFlow1);
            gameInfoController.getRequirementGridPane().add(vBox1, 0, 0);

            vBox1 = new VBox();
            Text text2 = new Text(game.getPlatforms()[0].getRequirementRecommended());
            text2.setFill(Paint.valueOf("white"));
            TextFlow textFlow2 = new TextFlow(text2);
            textFlow2.setMaxWidth(800);
            vBox1.getChildren().add(textFlow2);
            gameInfoController.getRequirementGridPane().add(vBox1, 0, 1);

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

    public void onActionAccueil() throws GameNotFoundException {
    String searchedText = entryAccueil.getText();

		if (searchedText.isBlank()){
                return; }

        List<Game> researchGame = apiRechercheManager.getInfoGame(searchedText);

        compteurX = 0;
        compteurY = 0;
        searchGameController.getGridRecherchePane().getChildren().clear();
        for (Game game : researchGame) {
            model.addGame(game);
            Platform.runLater(() -> {
                VBox vBox = new VBox();
                Label label = new Label(game.getName());
                label.setTextFill(Paint.valueOf("white"));
                ImageView image = new ImageView(new Image(game.getImageURL(), searchGameController.getGridRecherchePane().getPrefWidth() / 4, 250, true, true));
                vBox.getChildren().add(image);
                vBox.getChildren().add(label);
                vBox.setOnMouseClicked(mouseEvent -> {
                    jeuSelectionner(game.getId());
                    Stage stage = (Stage) vBox.getScene().getWindow();
                    stage.setScene(scene);
                });
                System.out.println(game.getName());
                searchGameController.getGridRecherchePane().add(vBox, compteurX, compteurY);
                if (compteurX == 3) {
                    compteurX = 0;
                    compteurY++;
                } else {
                    compteurX++;
                }
            });


        }
        searchGameController.getEntrySearch().setText(searchedText);
        Stage stage = (Stage) entryAccueil.getScene().getWindow();
        stage.setScene(searchScene);

    }

    public void handleMesJeuxButtonAction(ActionEvent event) {
    }

    public void handleCreerTagButtonAction(ActionEvent event) {
    }

    public void handleTagsButtonAction(ActionEvent event) {
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

    public void setGameController(PageJeuController gameInfoController) {
        this.gameInfoController = gameInfoController;
    }

    public void setSearchController(rechercheController researchGameController) {
        this.searchGameController = researchGameController;
    }

    public void setSearchScene(Scene searchPage) {
        this.searchScene = searchPage;
    }

    public void setSearchStage(Stage stage) {
        this.searchStage = stage;
    }
}
