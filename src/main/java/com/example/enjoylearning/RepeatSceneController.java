package com.example.enjoylearning;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import java.util.ArrayList;

public class RepeatSceneController {
    private Model model;
    CardManager cardManager;
    FilterManager filterManager;
    private ArrayList<WordCard> cards;
    private ArrayList<WordCard> filteredCards;
    private int iteration = 1;
    private WordCard currentCard;
    private final ArrayList<String> cardsToDelete = new ArrayList<>();

    @FXML
    ChoiceBox topicChoiceBox;
    @FXML
    ChoiceBox tagChoiceBox;
    @FXML
    ChoiceBox scoreChoiceBox;
    @FXML
    ChoiceBox orientationChoiceBox;
    @FXML
    Button startButton;

    @FXML
    VBox flipWordCard;

    @FXML
    Label labelTopic;
    @FXML
    Label labelWord;
    @FXML
    Label labelTranslation;
    @FXML
    Label labelTag;
    @FXML
    Label completionText;

    @FXML
    HBox scoreBox;

    @FXML
    Button deleteButton;
    @FXML
    Button deleteButtonNo;
    @FXML
    Button deleteButtonYes;

    public void setModel(Model model) {
        this.model = model;
    }

    public void updateChoiceBoxes() {
        cardManager = new CardManager();
        cards = cardManager.getCards();

        this.filterManager = new FilterManager(cards);
        ArrayList<String> topics = filterManager.getTopics();
        ArrayList<String> tags = filterManager.getTags();
        ArrayList<String> scores = filterManager.getScores();

        ObservableList<String> uniqueTopics = FXCollections.observableArrayList(topics);
        topicChoiceBox.setItems(uniqueTopics);
        topicChoiceBox.setValue("all");
        ObservableList<String> uniqueTags = FXCollections.observableArrayList(tags);
        tagChoiceBox.setItems(uniqueTags);
        tagChoiceBox.setValue("all");
        ObservableList<String> uniqueScores = FXCollections.observableArrayList(scores);
        scoreChoiceBox.setItems(uniqueScores);
        scoreChoiceBox.setValue("all");
    }

    @FXML
    private void goToMain() {
        closeCard();
        model.setCurrentView(Model.View.MAIN);
    }

    public void renderFlipCard() {
        this.currentCard = filteredCards.get(iteration - 1);
//        reset all card labels
        labelTopic.setText(currentCard.getTopic());
        labelTag.setText(currentCard.getTag());
        labelWord.setText("");
        labelTranslation.setText("");
        scoreBox.setVisible(false);

        if (orientationChoiceBox.getValue().equals("normal")) {
            labelWord.setText(currentCard.getWord());
        } else {
            labelTranslation.setText(currentCard.getTranslation());
        }
    }

    private void renderNextCard() {
        if (iteration == filteredCards.size()) {

            completionText.setText("Well done! Click start to repeat words again");

            hideDeleteButtonsGroup();
            deleteCards();
            closeCard();
        } else {
            iteration++;
            renderFlipCard();
            hideDeleteButtonsGroup();
        }
    }

    @FXML
    private void startRepetition() {
        cardManager = new CardManager();
        cards = cardManager.getCards();

        FilterManager filterManager = new FilterManager(cards);
        String topicChoiceBoxValue = (String) topicChoiceBox.getValue();
        String tagChoiceBoxValue = (String) tagChoiceBox.getValue();
        String scoreChoiceBoxValue = (String) scoreChoiceBox.getValue();

        this.filteredCards = filterManager.filterCardList(topicChoiceBoxValue, tagChoiceBoxValue, scoreChoiceBoxValue);
        if (this.filteredCards.isEmpty()) {
            completionText.setText("No cards with selected parameters");
        } else {
            topicChoiceBox.setDisable(true);
            tagChoiceBox.setDisable(true);
            orientationChoiceBox.setDisable(true);
            scoreChoiceBox.setDisable(true);
            startButton.setDisable(true);
            deleteButton.setVisible(true);
            renderFlipCard();
            flipWordCard.setVisible(true);
            completionText.setText("");
            flipWordCard.setOnMouseClicked(_ -> clickOnCard());
        }
    }

    @FXML
    private void closeCard() {
        topicChoiceBox.setDisable(false);
        tagChoiceBox.setDisable(false);
        scoreChoiceBox.setDisable(false);
        orientationChoiceBox.setDisable(false);
        flipWordCard.setVisible(false);
        startButton.setDisable(false);

        hideDeleteButtonsGroup();
        deleteButton.setVisible(false);

        cardManager = new CardManager();
        cards = cardManager.getCards();
        updateChoiceBoxes();

        iteration = 1;
    }

    @FXML
    private void clickOnCard() {
        scoreBox.setVisible(true);

        if (orientationChoiceBox.getValue().equals("normal")) {
            labelTranslation.setText(currentCard.getTranslation());
        } else {
            labelWord.setText(currentCard.getWord());
        }
    }

    private void deleteCards() {
        for (String id : cardsToDelete) {
            this.cards.removeIf(card -> card.getId().equals(id));
        }
        cardsToDelete.clear();
        cardManager.saveCards();
    }

    public void clickOnScoreButton(ActionEvent event) {
        Button currentButton = (Button) event.getSource();
        String currentButtonValue = currentButton.getText();
        this.currentCard.setCurrentProficiencyScore(currentButtonValue);
        cardManager.saveCards();

        renderNextCard();
    }

    public void showDeleteButtonsGroup() {
        deleteButtonYes.setVisible(true);
        deleteButtonNo.setVisible(true);
        deleteButton.setManaged(false);
        deleteButton.setVisible(false);
    }

    public void hideDeleteButtonsGroup() {
        deleteButtonYes.setVisible(false);
        deleteButtonNo.setVisible(false);
        deleteButton.setManaged(true);
        deleteButton.setVisible(true);
    }

    public void clickOnDeleteButtonYes() {
        cardsToDelete.add(this.currentCard.getId());
        renderNextCard();
    }

    public void clearCompletionText() {
        if (!completionText.getText().isEmpty()) completionText.setText("");
    }
}
