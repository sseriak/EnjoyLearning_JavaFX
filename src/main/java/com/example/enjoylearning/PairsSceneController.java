package com.example.enjoylearning;

import javafx.fxml.FXML;

public class PairsSceneController {
    private Model model;
    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private void goToMain() {
        model.setCurrentView(Model.View.MAIN);

        System.out.println("It's pairs scene");
    }
}
