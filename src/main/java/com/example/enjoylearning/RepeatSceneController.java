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

    public RepeatSceneController() {
        this.filterManager = new FilterManager(cards);
        this.topics = filterManager.getTopics();
        this.tags = filterManager.getTags();
        this.scores = filterManager.getScores();
    }

//    private void createChoiceBoxes() {
//
//    }


    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private void onTopicChoiceBoxClick() {
        ObservableList<String> uniqueTopicsObservable = FXCollections.observableArrayList(this.topics);
        topicChoiceBox.setItems(uniqueTopicsObservable);
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
        model.setCurrentView(Model.View.MAIN);
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
            topicChoiceBox.setDisable(false);
            tagChoiceBox.setDisable(false);
            scoreChoiceBox.setDisable(false);
            orientationChoiceBox.setDisable(false);
            flipWordCard.setVisible(false);
            startButton.setDisable(false);
            completionText.setText("Well done! Click start to repeat words again");

            iteration = 1;
        } else {
            iteration++;
            renderFlipCard();
        }
    }
}
