package com.kata.minesweeper;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by yannickgrenzinger on 08/01/2016.
 */
public class MinesweeperTest {

    @Test
    public void game1() {
        String gameInput = Stream.of(
                "3 5",
                "* * . . .",
                ". . . . .",
                ". * . . ."
        ).collect(Collectors.joining("\n"));

        String gameOutput = Stream.of(
                "* * 1 0 0",
                "3 3 2 0 0",
                "1 * 1 0 0"
        ).collect(Collectors.joining("\n"));

        assertThat(Minesweeper.build(gameInput)).isEqualTo(gameOutput);

    }

}
