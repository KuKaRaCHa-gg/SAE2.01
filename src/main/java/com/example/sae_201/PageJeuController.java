package com.example.sae_201;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class PageJeuController {

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
    private ImageView logoImageView;

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

    @FXML
    private void initialize() {
        // Initialize the controller
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
        NavigationUtil.navigateTo(stage, "Recherche.fxml");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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

    public void handleMesJeuxButtonAction(ActionEvent event) {
        navigateTo(event, "mesJeux.fxml");
    }

    @FXML
    public void handleCreerTagButtonAction(ActionEvent event) {
        navigateTo(event, "CreerTag.fxml");
    }

    @FXML
    public void handleTagsButtonAction(ActionEvent event) {
        navigateTo(event, "RechercheParTAG.fxml");
    }

    private void navigateTo(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement du fichier FXML : " + e.getMessage());
        }
    }
}