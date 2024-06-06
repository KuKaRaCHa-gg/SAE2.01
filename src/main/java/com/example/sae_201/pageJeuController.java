package com.example.sae_201;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
}
