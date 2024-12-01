package com.example.enjoylearning;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class WordCard {
    private final String word;
    private final String translation;
    private final String topic;
    private final ArrayList<String> tags;
    private String currentProficiencyScore;
    private final String id;

    public WordCard(String word, String translation, String topic, ArrayList<String> tags) {
        this.word = word;
        this.translation = translation;
        this.topic = topic;
        this.tags = tags;
        this.id = UUID.randomUUID().toString();
    }

    public String getTopic() {
        return topic;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getConcatenatedTags() {
        String resultString;

        if (tags == null) {
            resultString = "";
        } else {
            StringBuilder result = new StringBuilder();
            for (String tag : this.tags) {
                result.append(tag);
                result.append(" ");
            }
            resultString = result.toString();

        }

        return resultString;
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
