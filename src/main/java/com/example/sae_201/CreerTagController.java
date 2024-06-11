package com.example.sae_201;

import apiManagement.APIGameManager;
import apiManagement.APIRechercheManager;
import apiManagement.GameNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gameModel.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreerTagController {

    @FXML
    private Button mesJeuxButton1;

    @FXML
    private TextField ecrireNomTag;

    @FXML
    private TextField entryAccueil;

    @FXML
    private Label titreCreerTag;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Button creerTagButton;

    @FXML
    private TextField ecrireDescTag;
    
    @FXML
    private ImageView logoImageView;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button tagButton;

    @FXML
    private ImageView loginImageView;

    @FXML
    private Button mesJeuxButton;

    @FXML
    private Button tagsButton;

    @FXML
    private Button creerTagNavButton;
    ;

    private Stage stage;
    private List<Tag> tags = new ArrayList<>();
    private static final String TAGS_FILE = "tags.json";
    private APIRechercheManager apiRechercheManager;
    private int compteurX;
    private int compteurY;
    private Scene biblioPage;
    private Stage biblioStage;
    private pageJeuController gameInfoController;
    private APIGameManager apiGameManager;
    private rechercheController searchGameController;
    private Scene scene;
    private Scene searchScene;
    private Stage searchStage;

    public CreerTagController() {
        apiRechercheManager = new APIRechercheManager();
        apiGameManager = new APIGameManager();
    }
    
    @FXML
    private void initialize() {
        loadTags();
    }

    @FXML
    private void handleMesJeuxButtonAction() {
        NavigationUtil.navigateTo(stage, "accueil.fxml");
    }

    @FXML
    private void handleTagsButtonAction() {
        NavigationUtil.navigateTo(stage, "RechercheParTAG.fxml");
    }

    @FXML
    private void handleCreerTagButtonAction() {
        String tagName = ecrireNomTag.getText();
        String tagDescription = ecrireDescTag.getText();
        Color tagColor = colorPicker.getValue();

        if (tagName != null && !tagName.trim().isEmpty() && tagDescription != null && !tagDescription.trim().isEmpty()) {
            Tag newTag = new Tag(tagName, tagDescription, tagColor);
            tags.add(newTag);
            ecrireNomTag.clear();
            ecrireDescTag.clear();
            colorPicker.setValue(Color.WHITE);
            saveTags();
        }
    }

    private void saveTags() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(TAGS_FILE), tags);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTags() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            tags = objectMapper.readValue(new File(TAGS_FILE), new TypeReference<List<Tag>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
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


    public void jeuSelectionner(int id) {
        List<Game> selectionGame = apiGameManager.getInfoGame(id);

        gameInfoController.getRequirementGridPane().getChildren().clear();
        gameInfoController.getPlateformeGridPane().getChildren().clear();
        gameInfoController.getTagGridPane().getChildren().clear();
        gameInfoController.getDevGridPane().getChildren().clear();
        gameInfoController.getEditorGridPane().getChildren().clear();
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


    @FXML
    void mesJeuOnAction(ActionEvent event) {
        Button btn = (Button)event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setScene(biblioPage);
    }

    public void setBliblioScene(Scene biblioPage) {
        this.biblioPage = biblioPage;
    }

    public void setBiblioStage(Stage stage) {
        this.biblioStage = stage;
    }

    public void setGameController(pageJeuController gameInfoController) {
        this.gameInfoController = gameInfoController;
    }

    public void setSearchController(rechercheController researchGameController) {
        this.searchGameController = researchGameController;
    }

    public void setNewScene(Scene gamePage) {
        this.scene = gamePage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSearchScene(Scene searchPage) {
        this.searchScene = searchPage;
    }

    public void setSearchStage(Stage stage) {
        this.searchStage = stage;
    }
    
    


}
