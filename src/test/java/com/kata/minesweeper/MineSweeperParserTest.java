package com.kata.minesweeper;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MineSweeperParserTest {
    @Test
    public void should_parse_a_field_with_no_mine() {
        String gameInput = Stream.of(
                "2 2",
                ". .",
                ". ."
        ).collect(Collectors.joining("\n"));

        MineField mineField = MineSweeperParser.parse(gameInput);
        assertThat(mineField.width()).isEqualTo(2);
        assertThat(mineField.height()).isEqualTo(2);
        assertThat(mineField.mines()).isEmpty();
    }

    @Test
    public void should_parse_a_field_with_one_mine() {
        String gameInput = Stream.of(
                "2 3",
                ". * .",
                ". . ."
        ).collect(Collectors.joining("\n"));

        MineField mineField = MineSweeperParser.parse(gameInput);
        assertThat(mineField.width()).isEqualTo(3);
        assertThat(mineField.height()).isEqualTo(2);
        assertThat(mineField.mines()).contains(Mine.of(0, 1));
    }
}
