package com.example.enjoylearning;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;


    public void switchToSecondScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EnjoyLearningApplication.class.getResource("second-scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Second scene!");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EnjoyLearningApplication.class.getResource("main-scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Enjoy learning!");
        stage.setScene(scene);
        stage.show();
    }
}