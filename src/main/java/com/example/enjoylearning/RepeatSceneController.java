package com.example.enjoylearning;

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
    private int iteration = 1;
    private String cardOrientation = "NORMAL";
    private WordCard currentCard;

    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    ChoiceBox categoryChoiceBox;
    @FXML
    ChoiceBox tagChoiceBox;
    @FXML
    ChoiceBox scoreChoiceBox;
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

        if (cardOrientation.equals("NORMAL")) {
            labelWord.setText(currentCard.getWord());
        } else {
            labelTranslation.setText(currentCard.getTranslation());
        }
    }

    @FXML
    private void startRepetition() {
        categoryChoiceBox.setDisable(true);
        tagChoiceBox.setDisable(true);
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

        if (cardOrientation.equals("NORMAL")) {
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
            categoryChoiceBox.setDisable(false);
            tagChoiceBox.setDisable(false);
            scoreChoiceBox.setDisable(false);
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
