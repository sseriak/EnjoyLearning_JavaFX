package com.example.enjoylearning;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    HBox scoreBox;

    @FXML
    private void goToMain() {
        model.setCurrentView(Model.View.MAIN);
    }

    public void renderFlipCard() {
        this.currentCard = cards.get(iteration - 1);
        labelTopic.setText(currentCard.getTopic());
        labelTag.setText(currentCard.getTag());

        if (cardOrientation.equals("NORMAL")) {
            labelWord.setText(currentCard.getWord());
        } else {
            labelTranslation.setText(currentCard.getTranslation());
        }

        if (iteration == cards.size()) {
            iteration = 1;
        } else {
            iteration++;
        }
    }

    @FXML
    private void startRepetition() {
        renderFlipCard();
        flipWordCard.setVisible(true);
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



}
