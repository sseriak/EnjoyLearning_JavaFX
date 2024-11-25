package com.example.enjoylearning;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class FlipWordCard extends WordCard {
    public FlipWordCard(String word, String translation, String topic, String tag) {
        super(word, translation, topic, tag);
    }

    public enum Side { FRONT, BACK }

    private final ObjectProperty<Side> currentSide = new SimpleObjectProperty<>(Side.FRONT);

    public Side getCurrentSide() {
        return currentSide.get();
    }

    public ObjectProperty<Side> getCurrentSideProperty() {
        return currentSide;
    }

    public void setCurrentSide(Side side) {
        this.currentSide.set(side);
    }

    public void flipByClick() {
        if (currentSide.get() == Side.FRONT) {
            setCurrentSide(Side.BACK);
        } else {
            setCurrentSide(Side.FRONT);;
        }
    }
}
