package com.kata.poker;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Poker {

    private static final Pattern POKER_INPUT_REGEX = Pattern.compile("^(Black: )(.*)(White: )(.*)$");

    private final PokerHand blackHand;
    private final PokerHand whiteHand;

    public Poker(PokerHand blackHand, PokerHand whiteHand) {
        this.blackHand = blackHand;
        this.whiteHand = whiteHand;
    }

    public String game() {
        int diff = blackHand.compareTo(whiteHand);
        if (diff == 0) return "Tie";
        StringBuilder winMessage = new StringBuilder("");
        if (diff > 0) {
            winMessage.append("Black wins");
            buildWinningMessage(winMessage, blackHand);
        }
        if (diff < 0) {
            winMessage.append("White wins");
            buildWinningMessage(winMessage, whiteHand);
        }
        return winMessage.toString();
    }

    private void buildWinningMessage(StringBuilder winMessage, PokerHand hand) {
        winMessage.append(" with ");
        PokerHandType type = hand.getType();
        winMessage.append(type.getLabel());
        if (type == PokerHandType.HIGH_CARD) {
            winMessage.append(": ");
            winMessage.append(hand.getHighestCard().toString());
        }
    }

    public static Optional<Poker> of(String pokerInput) {
        Matcher m = POKER_INPUT_REGEX.matcher(pokerInput);
        if (!m.matches()) return Optional.empty();

        Optional<PokerHand> blackPokerHandOptional = PokerHand.of(m.group(2));
        Optional<PokerHand> whitePokerHandOptional = PokerHand.of(m.group(4));

        if (blackPokerHandOptional.isPresent() && whitePokerHandOptional.isPresent()) {
            return Optional.of(new Poker(blackPokerHandOptional.get(), whitePokerHandOptional.get()));
        }

        return Optional.empty();
    }
}
