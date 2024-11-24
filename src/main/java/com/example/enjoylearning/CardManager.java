package com.example.enjoylearning;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CardManager {
    private final String FILE_NAME = "data/cards.json";
    private final ArrayList<WordCard> cards;
    private final Gson gson = new Gson();

    public CardManager() {
//        load cards from file
        cards = loadCards();
    }

    public void addCard(WordCard card) {
        cards.add(card);
    }

    public ArrayList<WordCard> getCards() {
        return cards;
    }

    public void saveCards() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(cards, writer);
            System.out.println("The cards have been saved");
        } catch (IOException e) {
            System.err.println("Card saving error: " + e.getMessage());
        }
    }

    public ArrayList<WordCard> loadCards() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Type listType = new TypeToken<ArrayList<WordCard>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.err.println("File not found or corrupted. Create a new list");
            return new ArrayList<>();
        }
    }
}
