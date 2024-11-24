package com.example.enjoylearning;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddCardSceneController {
    private Model model;
    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private void goToMain() {
        model.setCurrentView(Model.View.MAIN);
    }

    @FXML
    TextField inputTopic;
    @FXML
    TextField inputWord;
    @FXML
    TextField inputTranslation;
    @FXML
    TextField inputTag;

}
