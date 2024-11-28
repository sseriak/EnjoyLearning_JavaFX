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
                .filter(topic -> topic != null && !topic.isEmpty())
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
        this.topics.addFirst("all");
        this.tags = this.initialCardList.stream()
                .map(WordCard::getTag)
                .filter(tag -> tag != null && !tag.isEmpty())
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
        this.tags.addFirst("all");
        this.scores = this.initialCardList.stream()
                .map(WordCard::getCurrentProficiencyScore)
                .filter(score -> score != null && !score.isEmpty())
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
        this.scores.addFirst("all");
    }

    public ArrayList<WordCard> filterCardList(String topic, String tag, String score) {
        return this.initialCardList.stream()
                .filter(card -> "all".equals(topic) || topic.equals(card.getTopic()))
                .filter(card -> "all".equals(tag) || tag.equals(card.getTag()))
                .filter(card -> "all".equals(score) || score.equals(card.getCurrentProficiencyScore()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<WordCard> searchWords(String topic, String word, String tag) {
        return this.initialCardList.stream()
                .filter(card -> "all".equals(topic) || topic == null || topic.equals(card.getTopic()))
                .filter(card -> card.getWord().toLowerCase().contains(word.toLowerCase()))
                .filter(card -> card.getTag().toLowerCase().contains(tag.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
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
