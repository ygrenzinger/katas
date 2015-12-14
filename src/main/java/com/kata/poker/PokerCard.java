package com.kata.poker;

import java.util.Objects;
import java.util.Optional;

public class PokerCard implements Comparable<PokerCard> {
    private final CardSuit suit;
    private final CardRank rank;

    private PokerCard(CardSuit suit, CardRank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Optional<PokerCard> of(String rank, String suit) {
        Optional<CardRank> cardRank = CardRank.of(rank);
        Optional<CardSuit> cardSuit = CardSuit.of(suit);
        if (cardSuit.isPresent() && cardRank.isPresent()) {
            return Optional.of(new PokerCard(cardSuit.get(), cardRank.get()));
        }
        return Optional.empty();
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardRank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank.toString() + " of " + suit.toString();
    }

    @Override
    public int compareTo(PokerCard o) {
        return rank.value() - o.rank.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerCard pokerCard = (PokerCard) o;
        return suit == pokerCard.suit &&
                Objects.equals(rank, pokerCard.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
