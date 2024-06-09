package com.example.sae_201;

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
    private Button creerTagNavButton; // updated fx:id

    @FXML
    private TextField ecrireNomTag;

    @FXML
    private TextField ecrireDescTag;

    @FXML
    private Button creerTagButton; // keep the original id for creating the tag

    @FXML
    private ColorPicker colorPicker; // new fx:id for ColorPicker

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
        Color tagColor = colorPicker.getValue(); // get the selected color

        if (tagName != null && !tagName.trim().isEmpty() && tagDescription != null && !tagDescription.trim().isEmpty()) {
            Tag newTag = new Tag(tagName, tagDescription, tagColor);
            tags.add(newTag);
            ecrireNomTag.clear();
            ecrireDescTag.clear();
            colorPicker.setValue(Color.WHITE); // reset the color picker
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

    @FXML
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
