package com.kata.bowling_game;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class BowlingGameTest {

    @Test
    public void should_have_a_score_of_10() {
        assertThat(BowlingGame.scoreFor("11111111111111111111")).isEqualTo(20);
    }

    @Test
    public void should_have_a_score_of_50() {
        assertThat(BowlingGame.scoreFor("5-5-5-5-5-5-5-5-5-5-")).isEqualTo(50);
    }

    @Test
    public void should_have_a_score_of_55() {
        assertThat(BowlingGame.scoreFor("5/5-5-5-5-5-5-5-5---")).isEqualTo(55);
    }

    @Test
    public void should_have_a_score_of_45() {
        assertThat(BowlingGame.scoreFor("5-5-5-5/-5-5-5-5---")).isEqualTo(45);
    }

    @Test
    public void should_have_a_score_of_60() {
        assertThat(BowlingGame.scoreFor("5-5-5-5-5-5-5-5-5-5/5")).isEqualTo(60);
    }

    @Test
    public void should_have_a_score_of_150() {
        assertThat(BowlingGame.scoreFor("5/5/5/5/5/5/5/5/5/5/5")).isEqualTo(150);
    }

    @Test
    public void should_have_a_score_of_240() {
        assertThat(BowlingGame.scoreFor("XXXXXXXXX--")).isEqualTo(7 * 30 + 20 + 10);
    }

    @Test
    public void should_have_a_score_of_300() {
        assertThat(BowlingGame.scoreFor("XXXXXXXXXXXX")).isEqualTo(300);
    }

}
