package com.example.enjoylearning;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class WordCard {
    private String word;
    private String translation;
    private String topic;
    private String tag;
    private int currentProficiencyScore;
    public enum Side { BACK, FRONT }

    private final ObjectProperty<Side> currentCardSide = new SimpleObjectProperty<Side>(Side.FRONT);

    public WordCard(String word, String translation, String topic, String tag) {
        this.word = word;
        this.translation = translation;
        this.topic = topic;
        this.tag = tag;
    }

//    current side property control
    public ObjectProperty<Side> getCurrentCardSideProperty() {
        return currentCardSide;
    }

    public Side getCurrentCardSide() {
        return currentCardSide.get();
    }

    public void setCurrentCardSide(Side currentSide) {
        this.currentCardSide.set(currentSide);
    }

    //    current proficiency score control
    public int getCurrentProficiencyScore() {
        return currentProficiencyScore;
    }

    public void setCurrentProficiencyScore(int currentProficiencyScore) {
        this.currentProficiencyScore = currentProficiencyScore;
    }

    public void flipByClick() {
        if (getCurrentCardSide() == Side.FRONT) {
            setCurrentCardSide(Side.BACK);
        } else {
            setCurrentCardSide(Side.FRONT);
        }
    }
}
