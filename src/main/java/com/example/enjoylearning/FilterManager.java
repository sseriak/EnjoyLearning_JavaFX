package com.example.enjoylearning;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterManager {
    private final ArrayList<WordCard> initialCardList;
    private ArrayList<String> topics;
    private ArrayList<String> tags;
    private ArrayList<String> scores;

    public FilterManager(ArrayList<WordCard> cardList) {
        this.initialCardList = cardList;
        this.topics = this.initialCardList.stream()
                .map(WordCard::getTopic)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
        this.tags = this.initialCardList.stream()
                .map(WordCard::getTag)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
        this.scores = this.initialCardList.stream()
                .map(WordCard::getCurrentProficiencyScore)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<WordCard> filterCardList(String topic, String tag, String score) {
        return (ArrayList<WordCard>) this.initialCardList.stream()
                .filter(card -> "all".equals(topic) || topic.equals(card.getTopic()))
                .filter(card -> "all".equals(tag) || tag.equals(card.getTag()))
                .filter(card -> "all".equals(score) || score.equals(card.getCurrentProficiencyScore()))
                .toList();
    }

    public ArrayList<String> getTopics() {
        return this.topics;
    }

    public ArrayList<String> getTags() {
        return this.tags;
    }

    public ArrayList<String> getScores() {
        return this.scores;
    }
}
