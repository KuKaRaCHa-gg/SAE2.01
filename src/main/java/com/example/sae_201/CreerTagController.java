package com.example.sae_201;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
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
    private Button creerTagButton;

    @FXML
    private TextField ecrireNomTag;

    @FXML
    private TextField ecrireDescTag;

    private Stage stage;
    private List<Tag> tags = new ArrayList<>();

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
        if (tagName != null && !tagName.trim().isEmpty() && tagDescription != null && !tagDescription.trim().isEmpty()) {
            Tag newTag = new Tag(tagName, tagDescription);
            tags.add(newTag);
            ecrireNomTag.clear();
            ecrireDescTag.clear();
            saveTags();
        }
    }

    private void saveTags() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tags.dat"))) {
            out.writeObject(tags);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTags() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("tags.dat"))) {
            tags = (List<Tag>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
