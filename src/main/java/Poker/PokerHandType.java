package Poker;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public enum PokerHandType {

    STRAIGHT_FLUSH(9, "straight flush") {
        @Override
        public boolean isEqualTo(PokerHand pokerHand) {
            return STRAIGHT.isEqualTo(pokerHand) && FLUSH.isEqualTo(pokerHand);
        }
    },
    FOUR_OF_A_KIND(8, "four of a kind") {
        @Override
        public boolean isEqualTo(PokerHand pokerHand) {
            return countCards(pokerHand).values().contains(4L);
        }
    },
    FULL_HOUSE(7, "full house") {
        @Override
        public boolean isEqualTo(PokerHand pokerHand) {
            return THREE_OF_A_KIND.isEqualTo(pokerHand) && PAIR.isEqualTo(pokerHand);
        }
    },
    FLUSH(6, "flush") {
        @Override
        public boolean isEqualTo(PokerHand pokerHand) {
            long count = pokerHand.getCards().stream().map(PokerCard::getSuit).distinct().count();
            return count == 1;
        }
    },
    STRAIGHT(5, "straight") {
        @Override
        public boolean isEqualTo(PokerHand pokerHand) {
            List<String> ranksInHand = pokerHand.getCards().stream()
                    .map(card -> card.getRank().getRank())
                    .collect(Collectors.toList());
            return LIST_OF_STRAIGHT_HANDS.stream()
                    .anyMatch(straightHand -> straightHand.stream().allMatch(ranksInHand::contains));
        }
    },
    THREE_OF_A_KIND(4, "three of a kind") {
        @Override
        public boolean isEqualTo(PokerHand pokerHand) {
            return countCards(pokerHand).values().contains(3L);
        }
    },
    TWO_PAIR(3, "two pair") {
        @Override
        public boolean isEqualTo(PokerHand pokerHand) {
            return countCards(pokerHand).values().stream().filter(l -> l == 2L).count() == 2;
        }
    },
    PAIR(2, "pair") {
        @Override
        public boolean isEqualTo(PokerHand pokerHand) {
            return countCards(pokerHand).values().contains(2L);
        }
    },
    HIGH_CARD(1, "high card") {
        @Override
        public boolean isEqualTo(PokerHand pokerHand) {
            return true;
        }
    };

    private final int value;
    private final String label;

    PokerHandType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public abstract boolean isEqualTo(PokerHand pokerHand);

    private final static List<List<String>> LIST_OF_STRAIGHT_HANDS;

    static {
        Stream<String> cardRankStream = Stream.of(CardRank.RANKS);
        RollingSpliterator<String> rollingSpliterator = new RollingSpliterator<String>(cardRankStream.spliterator(), 5);
        Stream<Stream<String>> streamOfStream = StreamSupport.stream(rollingSpliterator, false);
        LIST_OF_STRAIGHT_HANDS = streamOfStream
                .map(stream -> stream.collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private static Map<CardRank, Long> countCards(PokerHand pokerHand) {
        return pokerHand.getCards().stream()
                .map(PokerCard::getRank)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static PokerHandType findTypeOf(PokerHand pokerHand) {
        return Stream.of(PokerHandType.values())
                .filter(pokerHandType -> pokerHandType.isEqualTo(pokerHand))
                .findFirst().orElse(HIGH_CARD);
    }
}
