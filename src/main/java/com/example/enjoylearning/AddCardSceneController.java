package com.example.enjoylearning;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.ArrayList;

public class AddCardSceneController {
    private Model model;
    public void setModel(Model model) {
        this.model = model;
    }

    CardManager cardManager = new CardManager();

    @FXML
    TextField inputTopic;
    @FXML
    TextField inputWord;
    @FXML
    TextField inputTranslation;
    @FXML
    TextField inputTag;

    @FXML
    private void goToMain() {
        model.setCurrentView(Model.View.MAIN);
    }

    @FXML
    private void addNewCard() {
        if (inputWord.getText().isBlank() || inputTranslation.getText().isBlank()) {
            return;
        }
        cardManager.addCard(new WordCard(inputWord.getText(), inputTranslation.getText(), inputTopic.getText(), inputTag.getText()));
        cardManager.saveCards();
        System.out.println("New card has been added");
        ArrayList<WordCard> cards = cardManager.getCards();

        for (WordCard card : cards) {
            System.out.println(card.getWord() + " " + card.getTranslation());
        }
        System.out.println(cardManager.getCards());
    }

}
