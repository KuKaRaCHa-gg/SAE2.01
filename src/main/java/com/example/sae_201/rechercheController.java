package com.example.sae_201;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class rechercheController {
    @FXML
    private HBox alphaLabel;

    @FXML
    private GridPane gridRecherchePane;

    @FXML
    private TextField entrySearch;


    public GridPane getGridRecherchePane() {
        return gridRecherchePane;
    }

    public TextField getEntrySearch() {
        return entrySearch;
    }





}
