package com.example.enjoylearning;

import java.io.Serializable;

public class WordCard implements Serializable {
    private String word;
    private String translation;
    private String topic;
    private String tag;
    private String currentProficiencyScore;

    public WordCard(String word, String translation, String topic, String tag) {
        this.word = word;
        this.translation = translation;
        this.topic = topic;
        this.tag = tag;
    }

    public String getTopic() {
        return topic;
    }

    public String getTag() {
        return tag;
    }

    //    current proficiency score control
    public String getCurrentProficiencyScore() {
        return currentProficiencyScore;
    }

    public void setCurrentProficiencyScore(String currentProficiencyScore) {
        this.currentProficiencyScore = currentProficiencyScore;
    }

//    public void toggleCurrentSide() {
//        this.isCurrentSideFront = !this.isCurrentSideFront;
//    }

    public String getWord() {
        return this.word;
    }

    public String getTranslation() {
        return this.translation;
    }
}
