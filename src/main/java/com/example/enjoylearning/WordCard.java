package com.example.enjoylearning;

import java.io.Serializable;
import java.util.UUID;

public class WordCard {
    private String word;
    private String translation;
    private String topic;
    private String tag;
    private String currentProficiencyScore;
    private final String id;

    public WordCard(String word, String translation, String topic, String tag) {
        this.word = word;
        this.translation = translation;
        this.topic = topic;
        this.tag = tag;
        this.id = UUID.randomUUID().toString();
    }

    public String getTopic() {
        return topic;
    }

    public String getTag() {
        return tag;
    }

    public String getId() {
        return this.id;
    }

    //    current proficiency score control
    public String getCurrentProficiencyScore() {
        return currentProficiencyScore;
    }

    public void setCurrentProficiencyScore(String currentProficiencyScore) {
        this.currentProficiencyScore = currentProficiencyScore;
    }

    public String getWord() {
        return this.word;
    }

    public String getTranslation() {
        return this.translation;
    }
}
