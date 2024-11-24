package com.example.enjoylearning;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;

public class WordCard implements Serializable {
    private String word;
    private String translation;
    private String topic;
    private String tag;
    private int currentProficiencyScore;
    private String currentSide = "FRONT";

    public WordCard(String word, String translation, String topic, String tag) {
        this.word = word;
        this.translation = translation;
        this.topic = topic;
        this.tag = tag;
    }

    //    current proficiency score control
    public int getCurrentProficiencyScore() {
        return currentProficiencyScore;
    }

    public void setCurrentProficiencyScore(int currentProficiencyScore) {
        this.currentProficiencyScore = currentProficiencyScore;
    }

    public String getWord() {
        return this.word;
    }

    public String getTranslation() {
        return this.translation;
    }

    public void setCurrentCardSide(String side) {
        this.currentSide = side;
    }

    public void flipByClick() {
        if (currentSide.equals("FRONT")) {
            setCurrentCardSide("BACK");
        } else {
            setCurrentCardSide("FRONT");
        }
    }
}
