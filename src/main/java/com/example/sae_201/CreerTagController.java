package com.example.sae_201;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreerTagController {

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

    @FXML
    private TextField ecrireNomTag;

    @FXML
    private TextField ecrireDescTag;

    @FXML
    private Button creerTagButton;

    @FXML
    private ColorPicker colorPicker;

    private Stage stage;
    private List<Tag> tags = new ArrayList<>();
    private static final String TAGS_FILE = "tags.json";

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

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
