package com.example.sae_201;

import apiManagement.APIGameManager;
import apiManagement.APIRechercheManager;
import apiManagement.GameNotFoundException;
import gameModel.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.List;

public class MesJeuController {
    @FXML
    private HBox alphaLabel;

    @FXML
    private TextField entrySearch;

    @FXML
    private GridPane gridRecherchePane;

    @FXML
    private ImageView logoToAccueil;
    private APIRechercheManager apiRechercheManager;
    private int compteurX = 0;
    private int compteurY = 0;
    private rechercheController searchGameController;
    private PageJeuController gameInfoController;
    private Scene searchScene;
    private Stage searchStage;
    private Stage stage;
    private Scene scene;
    private APIGameManager apiGameManager;
    private Scene createTAGPage;
    private Stage createStage;

    public MesJeuController() {
    apiRechercheManager = new APIRechercheManager();
    apiGameManager = new APIGameManager();

    }

    public GridPane getGridRecherchePane(){
        return gridRecherchePane;
    }

    @FXML
    void onActionRecherche(ActionEvent event) throws GameNotFoundException {
            String searchedText = entrySearch.getText();

            if (searchedText.isBlank()){
                return; }

            List<Game> researchGame = apiRechercheManager.getInfoGame(searchedText);

            compteurX = 0;
            compteurY = 0;
            searchGameController.getGridRecherchePane().getChildren().clear();
            for (Game game : researchGame) {
                Platform.runLater(() -> {
                    VBox vBox = new VBox();
                    Label label = new Label(game.getName());
                    label.setTextFill(Paint.valueOf("white"));
                    ImageView image = new ImageView(new Image(game.getImageURL(), 1000, 250, true, true));
                    image.setViewport(new Rectangle2D(image.getImage().getWidth()/2 - 268 /2 ,0,268, 268));
                    vBox.getChildren().add(image);
                    vBox.getChildren().add(label);
                    vBox.setOnMouseClicked(mouseEvent -> {
                        jeuSelectionner(game.getId());
                        gameInfoController.getCurrentGame().setId(game.getId());
                        Stage stage = (Stage) vBox.getScene().getWindow();
                        stage.setScene(scene);
                    });
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
            Stage stage = (Stage) entrySearch.getScene().getWindow();
            stage.setScene(searchScene);

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

            gameInfoController.getCurrentGame().setId(game.getId());
            gameInfoController.getCurrentGame().setName(game.getName());
            gameInfoController.getCurrentGame().setImageURL(game.getImageURL());

        }
    }

    @FXML
    public void onTagCreatClicked(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setScene(createTAGPage);
    }


    public void setSearchController(rechercheController researchGameController) {
        this.searchGameController = researchGameController;
    }

    public void setGameController(PageJeuController gameInfoController) {
        this.gameInfoController = gameInfoController;
    }

    public void setSearchScene(Scene searchPage) {
        this.searchScene = searchPage;
    }

    public void setSearchStage(Stage stage) {
        this.searchStage = stage;
    }

    public void setNewScene(Scene gamePage) {
        this.scene = gamePage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setTagScene(Scene createTAGPage) {
        this.createTAGPage = createTAGPage;
    }

    public void setTagStage(Stage stage) {
        this.createStage = stage;
    }
}


