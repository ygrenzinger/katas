package com.kata.tictactoe;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TicTacToeTest {

    @Test
    public void there_is_two_players() {
        assertThat(TicTacToe.getPlayers()).contains(Player.X, Player.O);
    }

    @Test
    public void a_player_can_take_field_only_if_free() {
        TicTacToe ticTacToe = new TicTacToe();
        assertThat(ticTacToe.playAt(Player.X, 0, 0)).isTrue();
        assertThat(ticTacToe.playAt(Player.X, 0, 0)).isFalse();
        assertThat(ticTacToe.playAt(Player.O, 0, 0)).isFalse();
    }

    @Test
    public void game_is_over_full_board() {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playAt(Player.X, 0, 0);
        assertThat(ticTacToe.isGameOver()).isFalse();
        ticTacToe.playAt(Player.X, 0, 1);
        assertThat(ticTacToe.isGameOver()).isFalse();
        ticTacToe.playAt(Player.O, 0, 2);
        assertThat(ticTacToe.isGameOver()).isFalse();

        ticTacToe.playAt(Player.O, 1, 0);
        assertThat(ticTacToe.isGameOver()).isFalse();
        ticTacToe.playAt(Player.X, 1, 1);
        assertThat(ticTacToe.isGameOver()).isFalse();
        ticTacToe.playAt(Player.X, 1, 2);
        assertThat(ticTacToe.isGameOver()).isFalse();

        ticTacToe.playAt(Player.X, 2, 0);
        assertThat(ticTacToe.isGameOver()).isFalse();
        ticTacToe.playAt(Player.O, 2, 1);
        assertThat(ticTacToe.isGameOver()).isFalse();
        ticTacToe.playAt(Player.X, 2, 2);
        assertThat(ticTacToe.isGameOver()).isTrue();
    }

    @Test
    public void a_player_win_if_he_has_taken_a_row() {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playAt(Player.X, 0, 0);
        ticTacToe.playAt(Player.X, 0, 1);
        ticTacToe.playAt(Player.X, 0, 2);
        assertThat(ticTacToe.isGameOver()).isTrue();
        assertThat(ticTacToe.winner()).isEqualTo(Player.X);
    }

    @Test
    public void a_player_win_if_he_has_taken_a_column() {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playAt(Player.X, 0, 0);
        ticTacToe.playAt(Player.X, 1, 0);
        ticTacToe.playAt(Player.X, 2, 0);
        assertThat(ticTacToe.isGameOver()).isTrue();
        assertThat(ticTacToe.winner()).isEqualTo(Player.X);
    }

    @Test
    public void a_player_win_if_he_has_taken_one_diagonal() {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playAt(Player.X, 0, 0);
        ticTacToe.playAt(Player.X, 1, 1);
        ticTacToe.playAt(Player.X, 2, 2);
        assertThat(ticTacToe.isGameOver()).isTrue();
        assertThat(ticTacToe.winner()).isEqualTo(Player.X);
    }

    @Test
    public void a_player_win_if_he_has_taken_the_other_diagonal() {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playAt(Player.X, 0, 2);
        ticTacToe.playAt(Player.X, 1, 1);
        ticTacToe.playAt(Player.X, 2, 0);
        assertThat(ticTacToe.isGameOver()).isTrue();
        assertThat(ticTacToe.winner()).isEqualTo(Player.X);
    }
}