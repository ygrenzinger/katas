package com.kata.poker;

import java.util.Optional;
import java.util.stream.Stream;

public enum CardSuit {
    SPADE("S", "Spade"), HEART("H", "Heart"), DIAMOND("D", "Diamond"), CLUB("C", "Club");

    private final String representation;
    private final String label;

    CardSuit(String representation, String label) {
        this.representation = representation;
        this.label = label;
    }

    public static Optional<CardSuit> of(String value) {
        return Stream.of(CardSuit.values()).filter(suit -> suit.representation.equals(value)).findFirst();
    }

    @Override
    public String toString() {
        return label;
    }
}
