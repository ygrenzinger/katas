package com.kata.poker;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PokerTest {

    @Test
    public void should_return_white_winning_with_high_card_ace() {
        final Optional<Poker> pokerGame = Poker.of("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
        assertThat(pokerGame.get().game()).isEqualTo("White wins with high card: Ace of Heart");
    }

    @Test
    public void should_return_black_winning_with_flush() {
        final Optional<Poker> pokerGame = Poker.of("Black: 2S 8S AS QS 3S  White: TD 9H 8D QD JC");
        assertThat(pokerGame.get().game()).isEqualTo("Black wins with flush");
    }

    @Test
    public void should_return_black_winning_with_four_of_a_kind() {
        final Optional<Poker> pokerGame = Poker.of("Black: KS AS KC KH KD  White: 2S 7S 2C 2H 5D");
        assertThat(pokerGame.get().game()).isEqualTo("Black wins with four of a kind");
    }
}
