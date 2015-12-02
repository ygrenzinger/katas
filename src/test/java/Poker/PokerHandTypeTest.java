package Poker;

import org.junit.Test;

import static Poker.PokerHandType.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PokerHandTypeTest {

    @Test
    public void should_return_flush() {
        assertThat(PokerHand.of("2S 8S AS QS 3S").get().getType()).isEqualTo(FLUSH);
    }

    @Test
    public void should_return_straight() {
        assertThat(PokerHand.of("TD 9H 8S QS JC").get().getType()).isEqualTo(STRAIGHT);
    }

    @Test
    public void should_return_straight_flush() {
        assertThat(PokerHand.of("KS AS QS TS JS").get().getType()).isEqualTo(STRAIGHT_FLUSH);
    }

    @Test
    public void should_return_four_of_a_kind() {
        assertThat(PokerHand.of("KS AS KC KH KD").get().getType()).isEqualTo(FOUR_OF_A_KIND);
    }

    @Test
    public void should_return_high_card() {
        assertThat(PokerHand.of("2S 3S KC 5H TD").get().getType()).isEqualTo(HIGH_CARD);
    }

    @Test
    public void should_return_pair() {
        assertThat(PokerHand.of("2S 5S KC 2H TD").get().getType()).isEqualTo(PAIR);
    }

    @Test
    public void should_return_two_pair() {
        assertThat(PokerHand.of("2S 5S KC 2H 5D").get().getType()).isEqualTo(TWO_PAIR);
    }

    @Test
    public void should_return_three_of_a_kind() {
        assertThat(PokerHand.of("2S AS 2C 2H 5D").get().getType()).isEqualTo(THREE_OF_A_KIND);
    }

    @Test
    public void should_return_full_house() {
        assertThat(PokerHand.of("2S 5S 2C 2H 5D").get().getType()).isEqualTo(FULL_HOUSE);
    }
}
