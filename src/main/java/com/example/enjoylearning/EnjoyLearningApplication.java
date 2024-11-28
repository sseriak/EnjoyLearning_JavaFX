package com.example.enjoylearning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class EnjoyLearningApplication extends Application {
    private Parent viewMain;
    private Parent viewAdd;
    private Parent viewRepeat;
    private Parent viewPairs;
    private Parent viewSearch;

    @Override
    public void start(Stage stage) throws IOException {
        Model model = new Model();

        FXMLLoader loaderMain = new FXMLLoader(EnjoyLearningApplication.class.getResource("main-scene.fxml"));
        viewMain = loaderMain.load();
        MainSceneController controllerMain = loaderMain.getController();
        controllerMain.setModel(model);

        FXMLLoader loaderAdd = new FXMLLoader(EnjoyLearningApplication.class.getResource("add-card-scene.fxml"));
        viewAdd = loaderAdd.load();
        AddCardSceneController controllerAdd = loaderAdd.getController();
        controllerAdd.setModel(model);

        FXMLLoader loaderRepeat = new FXMLLoader(EnjoyLearningApplication.class.getResource("repeat-scene.fxml"));
        viewRepeat = loaderRepeat.load();
        RepeatSceneController controllerRepeat = loaderRepeat.getController();
        controllerRepeat.setModel(model);
        controllerRepeat.updateChoiceBoxes();

        FXMLLoader loaderPairs = new FXMLLoader(EnjoyLearningApplication.class.getResource("pairs-scene.fxml"));
        viewPairs = loaderPairs.load();
        PairsSceneController controllerPairs = loaderPairs.getController();
        controllerPairs.setModel(model);

        FXMLLoader loaderSearch = new FXMLLoader(EnjoyLearningApplication.class.getResource("search-scene.fxml"));
        viewSearch = loaderSearch.load();
        SearchSceneController controllerSearch = loaderSearch.getController();
        controllerSearch.setModel(model);

        // create scene with initial view:
        Scene scene = new Scene(viewFromModel(model.getCurrentView()), 640, 640);

        // change view when model property changes:
        model.currentViewProperty().addListener((_, _, newView) -> {
            if (newView == Model.View.REPEAT) controllerRepeat.updateChoiceBoxes();
            if (newView == Model.View.SEARCH) {
                controllerSearch.updateCardList();
                controllerSearch.updateChoiceBox();
                controllerSearch.renderTable();
            }

            scene.setRoot(viewFromModel(newView));
        });

        stage.setTitle("Enjoy learning!");
        stage.setScene(scene);

// prevent default exit, save cards before and then close window
        stage.setOnCloseRequest(event -> {
            event.consume();
            stage.close();
        });

        stage.show();
    }

    private Parent viewFromModel(Model.View view) {
        return switch (view) {
            case MAIN -> viewMain;
            case ADD -> viewAdd;
            case REPEAT -> viewRepeat;
            case PAIRS -> viewPairs;
            case SEARCH -> viewSearch;
        };
    }

    public static void main(String[] args) {
        launch();
    }
}
