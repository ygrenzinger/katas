package com.kata.poker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerHand implements Comparable<PokerHand> {

    private final List<PokerCard> pokerCards;
    private PokerHandType type;
    private PokerCard highestCard;

    private PokerHand(List<Optional<PokerCard>> pokerCardOptionals) {
        pokerCards = pokerCardOptionals.stream().map(Optional::get).collect(Collectors.toList());
        type = PokerHandType.findTypeOf(this);
        highestCard = pokerCards.stream().max(new PokerCardComparator()).get();
    }

    public static Optional<PokerHand> of(String pokerHandInput) {
        String[] pokerCardsInput = pokerHandInput.split(" ");
        if (pokerCardsInput.length != 5) return Optional.empty();

        if (Stream.of(pokerCardsInput).anyMatch(input -> input.length() != 2)) {
            return Optional.empty();
        }

        List<Optional<PokerCard>> pokerCardOptionals = Stream.of(pokerCardsInput)
                .map(input -> PokerCard.of(input.substring(0, 1), input.substring(1, 2)))
                .collect(Collectors.toList());

        if (pokerCardOptionals.stream().anyMatch(optional -> !optional.isPresent())) {
            return Optional.empty();
        }

        return Optional.of(new PokerHand(pokerCardOptionals));
    }

    public List<PokerCard> getCards() {
        return new ArrayList<>(pokerCards);
    }

    public PokerHandType getType() {
        return type;
    }

    public PokerCard getHighestCard() {
        return highestCard;
    }

    @Override
    public String toString() {
        return pokerCards.stream().map(PokerCard::toString).collect(Collectors.joining(" "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand pokerHand = (PokerHand) o;
        return Objects.equals(pokerCards, pokerHand.pokerCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokerCards);
    }


    @Override
    public int compareTo(PokerHand p) {
        int typeValueDiff = this.type.getValue() - p.type.getValue();
        if (typeValueDiff != 0) return typeValueDiff;

        return this.highestCard.compareTo(p.highestCard);
    }
}
