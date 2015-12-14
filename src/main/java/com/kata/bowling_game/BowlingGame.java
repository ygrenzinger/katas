package com.kata.bowling_game;

import java.util.stream.IntStream;

public class BowlingGame {

    public static final char MISS = '-';
    public static final char SPARE = '/';
    public static final char STRIKE = 'X';

    public static int scoreFor(String rolls) {
        return IntStream.range(0, rolls.length()).map(i -> scoreOfRoll(rolls, i)).sum();
    }

    private static int scoreOfRoll(String rolls, int i) {
        switch (rolls.charAt(i)) {
            case MISS:
                return valueOf(rolls.charAt(i));
            case SPARE:
                return valueOf(rolls.charAt(i)) + spare(rolls, i);
            case STRIKE:
                return valueOf(rolls.charAt(i)) + bonusStrikeRoll(rolls, i);
            default: return valueOf(rolls.charAt(i));
        }
    }

    private static int valueOf(Character character) {
        if (character == MISS) return 0;
        if (character == STRIKE || character == SPARE) return 10;
        return Integer.valueOf(String.valueOf(character));
    }

    private static int bonusStrikeRoll(String rolls, int i) {
        if (i >= rolls.length() - 3) return 0; //if we are on bonus roll of last (tenth) frame
        return valueOf(rolls.charAt(i + 1)) + valueOf(rolls.charAt(i + 2));
    }

    private static int spare(String rolls, int i) {
        return -scoreOfRoll(rolls, i - 1) + bonusSpareRoll(rolls, i);
    }

    private static int bonusSpareRoll(String rolls, int i) {
        if (i == rolls.length() - 2) return 0; //if we are on bonus roll of last (tenth) frame
        return valueOf(rolls.charAt(i + 1));
    }

}
