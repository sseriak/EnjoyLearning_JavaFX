package com.example.enjoylearning;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    Label errorMessageTag;

    @FXML
    private void goToMain() {
        model.setCurrentView(Model.View.MAIN);
    }

    private ArrayList<String> extractTags(String input) {
        ArrayList<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("#\\S+");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            result.add(matcher.group().trim());
        }

        return result;
    }

    @FXML
    private void addNewCard() {
        CardManager cardManager = new CardManager();
        removeWordError();
        removeTranslationError();
        removeTagError();
        if (!isValidated()) return;
        cardManager.addCard(new WordCard(inputWord.getText(), inputTranslation.getText(), inputTopic.getText(), extractTags(inputTag.getText())));
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

    @FXML
    public void removeTagError() {
        errorMessageTag.setVisible(false);
        inputTag.setStyle("-fx-border-color: transparent transparent black transparent;");
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

        if (!inputTag.getText().isBlank() && !inputTag.getText().contains("#")) {
            addError(errorMessageTag, inputTag);
            isValidated = false;
        }

        return isValidated;
    }
}
