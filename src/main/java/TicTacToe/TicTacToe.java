package TicTacToe;

import java.util.Optional;
import java.util.stream.Stream;

public class TicTacToe {

    private static final Player[] PLAYERS = {Player.X, Player.O};

    private static final int SIZE = 3;
    private Player[][] board;
    private Optional<Player> winningPlayer = Optional.empty();

    public TicTacToe() {
        board = new Player[SIZE][SIZE];
    }

    public static Player[] getPlayers() {
        return PLAYERS;
    }

    public boolean playAt(Player player, int i, int j) {
        if (board[i][j] == null) {
            board[i][j] = player;
            return true;
        }
        return false;
    }

    public boolean isGameOver() {
        findWinner();
        return winningPlayer.isPresent() || isFull();
    }

    private boolean isFull() {
        int total = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                   if (board[i][j] != null) total++;
            }
        }
        return total == 9;
    }

    public Player winner() {
        return winningPlayer.get();
    }

    private void findWinner() {
        Stream.of(board)
                .filter(row -> row[0] != null && row[0] == row[1] && row[1] == row[2])
                .findFirst().ifPresent(line -> winningPlayer = Optional.of(line[0]));

        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                winningPlayer = Optional.of(board[0][i]);
            }
        }
        if (board[1][1] != null && board[0][0] == board[1][1] && board[2][2] == board[1][1])  {
            winningPlayer = Optional.of(board[1][1]);
        }
        if (board[1][1] != null && board[0][2] == board[1][1] && board[2][0] == board[1][1])  {
            winningPlayer = Optional.of(board[1][1]);
        }
    }

}
