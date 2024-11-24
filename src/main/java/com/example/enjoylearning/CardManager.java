package com.example.enjoylearning;

import java.io.*;
import java.util.ArrayList;

public class CardManager {
    private static final String FILE_NAME = "data/cards.ser";
    private static ArrayList<WordCard> cards = null;

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

    public static void saveCards() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(cards);
            System.out.println("The cards have been saved");
        } catch (IOException e) {
            System.err.println("Card saving error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    static ArrayList<WordCard> loadCards() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            return (ArrayList<WordCard>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("File not found or corrupted. Create a new list");
            return new ArrayList<>();
        }
    }
}
