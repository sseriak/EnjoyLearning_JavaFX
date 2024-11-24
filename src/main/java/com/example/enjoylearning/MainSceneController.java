package com.example.enjoylearning;

import javafx.fxml.FXML;

public class MainSceneController {
    private Model model;
    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private void goToAddCard() {
        model.setCurrentView(Model.View.ADD);
    }

    @FXML
    private void goToRepeat() {
        model.setCurrentView(Model.View.REPEAT);
    }

    @FXML
    private void goToPairs() {
        model.setCurrentView(Model.View.PAIRS);
    }

    @FXML
    private void goToSearch() {
        model.setCurrentView(Model.View.SEARCH);
    }


}