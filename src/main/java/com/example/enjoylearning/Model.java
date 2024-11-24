package com.example.enjoylearning;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Model {
    public enum View { MAIN, ADD, REPEAT, PAIRS, SEARCH }

    private final ObjectProperty<View> currentView = new SimpleObjectProperty<>(View.MAIN);

    public View getCurrentView() {
        return currentView.get();
    }

    public ObjectProperty<View> currentViewProperty() {
        return currentView;
    }

    public void setCurrentView(View currentView) {
        this.currentView.set(currentView);
    }
}
