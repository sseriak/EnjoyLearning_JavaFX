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
    CardManager cardManager = new CardManager();
    ArrayList<WordCard> cards = cardManager.getCards();
    FilterManager filterManager;
    private int iteration = 1;
    private WordCard currentCard;
    private ArrayList<String> topics ;
    private ArrayList<String> tags;
    private ArrayList<String> scores;

    public void setModel(Model model) {
        this.model = model;
    }

    public void updateChoiceBoxes() {
        this.filterManager = new FilterManager(cards);
        this.topics = filterManager.getTopics();
        this.tags = filterManager.getTags();
        this.scores = filterManager.getScores();

        ObservableList<String> uniqueTopics = FXCollections.observableArrayList(this.topics);
        topicChoiceBox.setItems(uniqueTopics);
        topicChoiceBox.setValue("all");
        ObservableList<String> uniqueTags = FXCollections.observableArrayList(this.tags);
        tagChoiceBox.setItems(uniqueTags);
        tagChoiceBox.setValue("all");
        ObservableList<String> uniqueScores = FXCollections.observableArrayList(this.scores);
        scoreChoiceBox.setItems(uniqueScores);
        scoreChoiceBox.setValue("all");
    }

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
    private void goToMain() {
        closeCard();
        model.setCurrentView(Model.View.MAIN);
    }

    @FXML
    private void closeCard() {
        topicChoiceBox.setDisable(false);
        tagChoiceBox.setDisable(false);
        scoreChoiceBox.setDisable(false);
        orientationChoiceBox.setDisable(false);
        flipWordCard.setVisible(false);
        startButton.setDisable(false);

        cardManager = new CardManager();
        cards = cardManager.getCards();
        updateChoiceBoxes();

        iteration = 1;
    }

    public void renderFlipCard() {
        this.currentCard = cards.get(iteration - 1);
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

    @FXML
    private void startRepetition() {
        topicChoiceBox.setDisable(true);
        tagChoiceBox.setDisable(true);
        orientationChoiceBox.setDisable(true);
        scoreChoiceBox.setDisable(true);
        startButton.setDisable(true);
        renderFlipCard();
        flipWordCard.setVisible(true);
        completionText.setText("");
        flipWordCard.setOnMouseClicked(_ -> clickOnCard());
    }

    @FXML
    private void clickOnCard() {
        scoreBox.setVisible(true);

        System.out.println("AHA");

        if (orientationChoiceBox.getValue().equals("normal")) {
            labelTranslation.setText(currentCard.getTranslation());
        } else {
            labelWord.setText(currentCard.getWord());
        }
    }

    public void clickOnScoreButton(ActionEvent event) {
        Button currentButton = (Button) event.getSource();
        String currentButtonValue = currentButton.getText();
        this.currentCard.setCurrentProficiencyScore(currentButtonValue);

        System.out.println(this.currentCard.getCurrentProficiencyScore());
        cardManager.saveCards();

        if (iteration == cards.size()) {
            closeCard();
            completionText.setText("Well done! Click start to repeat words again");
        } else {
            iteration++;
            renderFlipCard();
        }
    }
}
