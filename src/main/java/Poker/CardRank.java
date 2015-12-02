package Poker;

import java.util.*;

public class CardRank {

    private static final Map<String, Integer> FACE_RANKS = new HashMap<>();
    private static final Map<String, String> RANKS_LABEL = new HashMap<>();

    public static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

    static {
        FACE_RANKS.put("T", 10);
        FACE_RANKS.put("J", 11);
        FACE_RANKS.put("Q", 12);
        FACE_RANKS.put("K", 13);
        FACE_RANKS.put("A", 14);

        RANKS_LABEL.put("2", "Two");
        RANKS_LABEL.put("3", "Three");
        RANKS_LABEL.put("4", "Four");
        RANKS_LABEL.put("5", "Five");
        RANKS_LABEL.put("6", "Six");
        RANKS_LABEL.put("7", "Seven");
        RANKS_LABEL.put("8", "Height");
        RANKS_LABEL.put("9", "Nine");
        RANKS_LABEL.put("T", "Ten");
        RANKS_LABEL.put("J", "Jack");
        RANKS_LABEL.put("Q", "Queen");
        RANKS_LABEL.put("K", "King");
        RANKS_LABEL.put("A", "Ace");
    }

    private final String rank;

    public String getRank() {
        return rank;
    }

    private CardRank(String rank) {
        this.rank = rank;
    }

    public int value() {
        if (isFaceRank(rank)) {
            return FACE_RANKS.get(rank);
        }
        return Integer.valueOf(rank);
    }

    private static boolean isFaceRank(String rank) {
        return FACE_RANKS.keySet().stream().anyMatch(faceRank -> faceRank.equals(rank));
    }

    public static Optional<CardRank> of(String rank) {
        if (isFaceRank(rank)) {
            return Optional.of(new CardRank(rank));
        }
        try {
            int value = Integer.parseInt(rank);
            if (value > 1 && value < 10) {
                return Optional.of(new CardRank(rank));
            }
            return Optional.empty();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardRank cardRank = (CardRank) o;
        return Objects.equals(rank, cardRank.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank);
    }

    @Override
    public String toString() {
        return RANKS_LABEL.get(rank);
    }


}
