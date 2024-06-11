package com.example.sae_201;

import apiManagement.APIGameManager;
import apiManagement.APIRechercheManager;
import apiManagement.GameNotFoundException;
import gameModel.Game;
import gameModel.MyGames;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import persistence.PersistenceBySerialization;

import java.util.List;

public class pageJeuController {

    @FXML
    private Label aboutGameLabel;

    @FXML
    private TextArea aboutGameTextArea;

    @FXML
    private Button adventureTagButton;

    @FXML
    private VBox authorDateContainer;

    @FXML
    private Label authorDateLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label authorText;

    @FXML
    private ImageView bannerImageView;

    @FXML
    private Label descriptionLabel;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label developersLabel;

    @FXML
    private Label developersText1;

    @FXML
    private Label developersText2;

    @FXML
    private Label developersText3;

    @FXML
    private Button freeToPlayTagButton;

    @FXML
    private Label generalRatingLabel;

    @FXML
    private VBox leftContainer;

    @FXML
    private ImageView loginImageView;

    @FXML
    private ImageView logoToAccueil;

    @FXML
    private Label minimumGraphicsLabel;

    @FXML
    private Label minimumLabel;

    @FXML
    private Label minimumMemoryLabel;

    @FXML
    private Label minimumOSLabel;

    @FXML
    private Label minimumProcessorLabel;

    @FXML
    private Label minimumStorageLabel;

    @FXML
    private Button moreInfoButton;

    @FXML
    private Button multiplayerTagButton;

    @FXML
    private Label ratingScaleLabel;

    @FXML
    private Label ratingValueLabel;


    @FXML
    private GridPane tagGridPane;

    @FXML
    private GridPane plateformeGridPane;

    @FXML
    private GridPane devGridPane;

    @FXML
    private GridPane editorGridPane;

    @FXML
    private GridPane requirementGridPane;

    @FXML
    private Label recommendedGraphicsLabel;

    @FXML
    private Label recommendedLabel;

    @FXML
    private Label recommendedMemoryLabel;

    @FXML
    private Label recommendedOSLabel;

    @FXML
    private Label recommendedProcessorLabel;

    @FXML
    private Label recommendedStorageLabel;

    @FXML
    private Label releaseDateLabel;

    @FXML
    private Label releaseDateText;

    @FXML
    private VBox requirementsContainer;

    @FXML
    private Label requirementsLabel;

    @FXML
    private VBox rightContainer;

    @FXML
    private Button rpgTagButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Separator separator1;

    @FXML
    private Separator separator2;

    @FXML
    private VBox tagsContainer;

    @FXML
    private Label tagsLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private WebView webDescView;


    private Stage stage;
    private APIRechercheManager apiRechercheManager;
    private rechercheController searchGameController;
    private int compteurX = 0;
    private int compteurY = 0;
    private Scene searchPage;
    private APIGameManager apiGameManager;
    private Scene scene;
    private Scene biblioPage;
    private Stage biblioStage;
    private MesJeuController biblioController;
    private List<Game> savedGames;
    private static final String FILE_PATH = "mes_jeux.ser";


    public pageJeuController(){
        apiRechercheManager = new APIRechercheManager();
        apiGameManager = new APIGameManager();
        currentGame = new Game();
        model = new MyGames();
        gameCompte = 0;
    }

    @FXML
    private Button pageAjout;
    private int compteur = 0;
    private int compteur2 = 0;
    private Game currentGame;
    private MyGames model;
    private int gameCompte;


    public Game getCurrentGame(){
        return currentGame;
    }

    public Label getRatingValueLabel(){
        return ratingValueLabel;
    }

    public TextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public ImageView getBannerImageView() {
        return bannerImageView;
    }

    public Label getRatingScaleLabel() {
        return ratingScaleLabel;
    }

    public GridPane getTagGridPane() {
        return tagGridPane;
    }

    public GridPane getDevGridPane() {
        return devGridPane;
    }

    public GridPane getPlateformeGridPane() {
        return plateformeGridPane;
    }

    public GridPane getEditorGridPane() {
        return editorGridPane;
    }

    public GridPane getRequirementGridPane() {
        return requirementGridPane;
    }

    public WebView getWebDescView() {
        return webDescView;
    }



    @FXML
    void onActionJeu(ActionEvent event) throws GameNotFoundException {
        String searchedText = searchTextField.getText();

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
                    getCurrentGame().setId(game.getId());
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
        searchTextField.setText("");
        searchGameController.getEntrySearch().setText(searchedText);
        Stage stage = (Stage) searchTextField.getScene().getWindow();
        stage.setScene(searchPage);
}


    public void jeuSelectionner(int id) {
        List<Game> selectionGame = apiGameManager.getInfoGame(id);

        getRequirementGridPane().getChildren().clear();
        getPlateformeGridPane().getChildren().clear();
        getTagGridPane().getChildren().clear();
        getDevGridPane().getChildren().clear();
        getEditorGridPane().getChildren().clear();
        for (Game game : selectionGame) {
            int compteurGame = 0;
            getRatingValueLabel().setText(game.getRate());
            getRatingScaleLabel().setText(game.getRate() + "/5 étoiles");
            String htmlContent = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { background-color: #2e2e2e; color: white; }" +
                    "p { color: white; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" + game.getDescription() + "</body>" +
                    "</html>";
            getWebDescView().getEngine().loadContent(htmlContent);
            getBannerImageView().setImage(new Image(game.getImageURL()));

            VBox vBox1 = new VBox();
            Text text1 = new Text(game.getPlatforms()[0].getRequirementMinimum());
            text1.setFill(Paint.valueOf("white"));
            TextFlow textFlow1 = new TextFlow(text1);
            textFlow1.setMaxWidth(800);
            vBox1.getChildren().add(textFlow1);
            getRequirementGridPane().add(vBox1, 0, 0);

            vBox1 = new VBox();
            Text text2 = new Text(game.getPlatforms()[0].getRequirementRecommended());
            text2.setFill(Paint.valueOf("white"));
            TextFlow textFlow2 = new TextFlow(text2);
            textFlow2.setMaxWidth(800);
            vBox1.getChildren().add(textFlow2);
            getRequirementGridPane().add(vBox1, 0, 1);

            for (int i = 0; i<game.getPlatforms().length; i++) {
                VBox vBox = new VBox();
                Label label = new Label(game.getPlatforms()[i].getPlatformName());
                label.setTextFill(Paint.valueOf("white"));
                vBox.getChildren().add(label);
                getPlateformeGridPane().add(vBox, 0, compteurGame);
                compteurGame++; }

            compteurGame = 0;
            for (int j = 0; j<game.getPublishers().length; j++){
                VBox vBox = new VBox();
                Label label = new Label(game.getPublishers()[j].getName());
                label.setTextFill(Paint.valueOf("white"));
                vBox.getChildren().add(label);
                getEditorGridPane().add(vBox, 0, compteurGame);
                compteurGame++; }

            compteurGame = 0;
            for(int k = 0; k<game.getDevelopers().length; k++){
                VBox vBox = new VBox();
                Label label = new Label(game.getDevelopers()[k].getName());
                label.setTextFill(Paint.valueOf("white"));
                vBox.getChildren().add(label);
                getDevGridPane().add(vBox, 0, compteurGame);
                compteurGame++; }

            compteurGame = 0;
            for (int m = 0;m<game.getTags().length; m++){
                Button vBox = new Button();
                vBox.setTextFill(Paint.valueOf("white"));
                vBox.setText(game.getTags()[m].getName());
                vBox.setStyle("-fx-background-color: #3a3a3a;");
                getTagGridPane().add(vBox, 0, compteurGame);
                compteurGame++; }

            getCurrentGame().setId(game.getId());
            getCurrentGame().setName(game.getName());
            getCurrentGame().setImageURL(game.getImageURL());

        }
    }

    @FXML
    void mesJeuOnAction(ActionEvent event) {
        Button btn = (Button)event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setScene(biblioPage);
    }

    @FXML
    void onAjoutClicked(ActionEvent event) {

        if (currentGame == null) {
            return; // Aucun jeu n'est actuellement sélectionné
        }

        VBox vBox = new VBox();
        Label label = new Label(currentGame.getName());
        label.setTextFill(Paint.valueOf("white"));
        ImageView image = new ImageView(new Image(currentGame.getImageURL(), 1000, 250, true, true));
        image.setViewport(new Rectangle2D(image.getImage().getWidth()/2 - 268 /2 ,0,268, 268));
        vBox.getChildren().add(image);
        vBox.getChildren().add(label);
        vBox.getProperties().put("gameId", currentGame.getId());
        vBox.setOnMouseClicked(mouseEvent -> {
            int gameId = Integer.parseInt(String.valueOf(getGameNameFromVBox(vBox)));
            jeuSelectionner(gameId);
            Stage stage = (Stage) vBox.getScene().getWindow();
            stage.setScene(scene);
        });
        biblioController.getGridRecherchePane().add(vBox, compteur, compteur2);
        if (compteur == 3) {
            compteur = 0;
            compteur2++;
        } else {
            compteur++;
        }


    }

    public int getGameNameFromVBox(VBox vBox) {
        return (int) vBox.getProperties().get("gameId");
    }


    public void setBliblioScene(Scene biblioPage) {
        this.biblioPage = biblioPage;
    }

    public void setBiblioStage(Stage stage) {
        this.biblioStage = stage;
    }


    public void setSearchController(rechercheController searchGameController) {
        this.searchGameController = searchGameController;
    }

    public void setBiblioController(MesJeuController biblioController) {
        this.biblioController = biblioController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setNewScene(Scene searchPage) {
        this.searchPage = searchPage;
    }

    public void setThisScene(Scene scene) {
        this.scene = scene;
    }

    private void populateMesJeux() {
        for (Game game : savedGames) {
            VBox vBox = new VBox();
            Label label = new Label(game.getName());
            label.setTextFill(Paint.valueOf("white"));
            ImageView image = new ImageView(new Image(game.getImageURL(), 1000, 250, true, true));
            image.setViewport(new Rectangle2D(image.getImage().getWidth() / 2 - 268 / 2, 0, 268, 268));
            vBox.getChildren().add(image);
            vBox.getChildren().add(label);
            vBox.getProperties().put("gameId", game.getId());
            vBox.setOnMouseClicked(mouseEvent -> {
                int gameId = Integer.parseInt(String.valueOf(getGameNameFromVBox(vBox)));
                jeuSelectionner(gameId);
                Stage stage = (Stage) vBox.getScene().getWindow();
                stage.setScene(scene);
            });
            biblioController.getGridRecherchePane().add(vBox, compteur, compteur2);
            if (compteur == 3) {
                compteur = 0;
                compteur2++;
            } else {
                compteur++;
            }
        }
    }

}
