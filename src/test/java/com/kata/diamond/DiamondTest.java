package com.kata.diamond;

import org.assertj.core.api.StrictAssertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by yannickgrenzinger on 28/06/2015.
 */
public class DiamondTest {

    @Test
    public void printA() {
        StrictAssertions.assertThat(Diamond.print('A')).isEqualTo("A");
    }

    @Test
    public void printB() {
        String[] lines = lines(Diamond.print('B'));

        assertThat(lines[0]).isEqualTo(" A ");
        assertThat(lines[1]).isEqualTo("B B");
        assertThat(lines[2]).isEqualTo(" A ");
    }

    @Test
    public void printC() {
        String[] lines = lines(Diamond.print('C'));

        assertThat(lines[0]).isEqualTo("  A  ");
        assertThat(lines[1]).isEqualTo(" B B ");
        assertThat(lines[2]).isEqualTo("C   C");
        assertThat(lines[3]).isEqualTo(" B B ");
        assertThat(lines[4]).isEqualTo("  A  ");
    }

    @Test
    public void printD() {
        String[] lines = lines(Diamond.print('D'));

        assertThat(lines[0]).isEqualTo("   A   ");
        assertThat(lines[1]).isEqualTo("  B B  ");
        assertThat(lines[2]).isEqualTo(" C   C ");
        assertThat(lines[3]).isEqualTo("D     D");
        assertThat(lines[4]).isEqualTo(" C   C ");
        assertThat(lines[5]).isEqualTo("  B B  ");
        assertThat(lines[6]).isEqualTo("   A   ");
    }

    private String[] lines(String result) {
        return result.split("\n");
    }
}