package com.example.enjoylearning;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SearchSceneController {
    private Model model;
    private ArrayList<WordCard> cards;
    private ArrayList<WordCard> filteredCards;
    CardManager cardManager;
    FilterManager filterManager;

    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    VBox wordTableContainer;

    @FXML
    TextField wordInput;

    @FXML
    TextField tagInput;

    @FXML
    ChoiceBox topicChoiceBox;

    @FXML
    private void goToMain() {
        model.setCurrentView(Model.View.MAIN);
    }

    public void updateCardList() {
        cardManager = new CardManager();
        cards = cardManager.getCards();
    }

    public void updateChoiceBox() {
        this.filterManager = new FilterManager(cards);
        ArrayList<String> topics = filterManager.getTopics();

        ObservableList<String> uniqueTopics = FXCollections.observableArrayList(topics);
        topicChoiceBox.setItems(uniqueTopics);
        topicChoiceBox.setValue("all");

        topicChoiceBox.valueProperty().addListener((_, _, _) -> {
            onInput();
        });
    }

    public void renderTable() {
        TableView<WordCard> wordTable = new TableView<>();

        TableColumn<WordCard, String> topicColumn = new TableColumn<>("Topic");
        topicColumn.setCellValueFactory(new PropertyValueFactory<>("topic"));

        TableColumn<WordCard, String> wordColumn = new TableColumn<>("Word");
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));

        TableColumn<WordCard, String> translationColumn = new TableColumn<>("Translation");
        translationColumn.setCellValueFactory(new PropertyValueFactory<>("translation"));

        TableColumn<WordCard, String> tagColumn = new TableColumn<>("Tag");
        tagColumn.setCellValueFactory(new PropertyValueFactory<>("tag"));

        wordTable.getColumns().add(topicColumn);
        wordTable.getColumns().add(wordColumn);
        wordTable.getColumns().add(translationColumn);
        wordTable.getColumns().add(tagColumn);

        wordTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        wordTable.setPlaceholder(new Label("No cards with selected parameters"));
        wordTable.setMinHeight(520);

        if (filteredCards == null) {
            for (WordCard card : cards) {
                wordTable.getItems().add(card);
            }
        } else {
            for (WordCard card : filteredCards) {
                wordTable.getItems().add(card);
            }
        }

        wordTableContainer.getChildren().clear();
        wordTableContainer.getChildren().add(wordTable);
    }

    @FXML
    public void onInput() {
        filteredCards = filterManager.searchWords((String) topicChoiceBox.getValue(), wordInput.getText(), tagInput.getText());
        renderTable();
    }

    @FXML
    public void reset() {
        topicChoiceBox.setValue("all");
        wordInput.setText("");
        tagInput.setText("");
        onInput();
    }










}
