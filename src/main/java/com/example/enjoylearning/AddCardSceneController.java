package com.example.enjoylearning;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AddCardSceneController {
    private Model model;
    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    TextField inputTopic;
    @FXML
    TextField inputWord;
    @FXML
    TextField inputTranslation;
    @FXML
    TextField inputTag;
    @FXML
    Label errorMessageWord;
    @FXML
    Label errorMessageTranslation;

    @FXML
    private void goToMain() {
        model.setCurrentView(Model.View.MAIN);
    }

    @FXML
    private void addNewCard() {
        CardManager cardManager = new CardManager();
        removeWordError();
        removeTranslationError();
        if (!isValidated()) return;
        cardManager.addCard(new WordCard(inputWord.getText(), inputTranslation.getText(), inputTopic.getText(), inputTag.getText()));
        cardManager.saveCards();
        System.out.println("New card has been added");
        inputTopic.setText("");
        inputWord.setText("");
        inputTranslation.setText("");
        inputTag.setText("");
    }

    private void addError(Label errorMessage, TextField errorField) {
        errorMessage.setVisible(true);
        errorField.setStyle("-fx-border-color: transparent transparent #f23f3f transparent;");
    }

    @FXML
    public void removeWordError() {
        errorMessageWord.setVisible(false);
        inputWord.setStyle("-fx-border-color: transparent transparent black transparent;");
    }

    @FXML
    public void removeTranslationError() {
        errorMessageTranslation.setVisible(false);
        inputTranslation.setStyle("-fx-border-color: transparent transparent black transparent;");
    }

    private boolean isValidated() {
        boolean isValidated = true;
        if (inputWord.getText().isBlank()) {
            addError(errorMessageWord, inputWord);
            isValidated = false;
        }

        if (inputTranslation.getText().isBlank()) {
            addError(errorMessageTranslation, inputTranslation);
            isValidated = false;
        }

        return isValidated;
    }
}
