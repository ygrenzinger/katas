package GameOfLife;

import org.junit.Test;

import static GameOfLife.CellState.ALIVE;
import static GameOfLife.CellState.DEAD;
import static org.assertj.core.api.Assertions.assertThat;

public class GameOfLifeTest {

    //mettre en avant un comportement / transformation
    //object calisthenics
    //InfiniTests
    // TDD as if you meant it

    private int neighborsCount(int x, int y, CellState[][] grid) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0) && grid[x + i][y + j] == ALIVE) {
                    count++;
                }
            }
        }
        return count;
    }

    private CellState[][] emptyGrid() {
        return fillGridWith(DEAD);
    }

    private CellState[][] fillGrid() {
        return fillGridWith(ALIVE);
    }

    private CellState[][] fillGridWith(CellState state) {
        CellState[][] grid = new CellState[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = state;
            }
        }
        return grid;
    }

    //Any_live_cell_with_fewer_than_two_live_neighbours_dies_on_the_next_gen
    @Test
    public void any_live_cell_with_0_neighbours_dies_on_the_next_gen() {

        assertThat(ALIVE.nextGeneration(0)).isEqualTo(DEAD);
    }

    @Test
    public void any_live_cell_with_1_neighbours_dies_on_the_next_gen() {

        assertThat(ALIVE.nextGeneration(1)).isEqualTo(DEAD);
    }

    //Any live cell with two or three live neighbours lives on to the next generation
    @Test
    public void any_live_cell_with_2_neighbours_survives_on_the_next_gen() {

        assertThat(ALIVE.nextGeneration(2)).isEqualTo(ALIVE);
    }

    @Test
    public void any_live_cell_with_3_neighbours_survives_on_the_next_gen() {

        assertThat(ALIVE.nextGeneration(3)).isEqualTo(ALIVE);
    }

    //Any live cell with more than three live neighbours dies, as if by overcrowding.
    @Test
    public void any_live_cell_with_more_than_4_neighbours_dies_on_the_next_gen() {

        assertThat(ALIVE.nextGeneration(4)).isEqualTo(DEAD);
    }

    //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
    @Test
    public void any_dead_cell_with_3_neighbours_becomes_alive_on_the_next_gen() {

        assertThat(DEAD.nextGeneration(3)).isEqualTo(ALIVE);
    }

    //basic case
    @Test
    public void a_dead_cell_with_3_neighbours_stays_dead_on_the_next_gen() {

        assertThat(DEAD.nextGeneration(2)).isEqualTo(DEAD);
    }

    //count neighbors
    @Test
    public void a_cell_alone_in_a_grid_has_no_neighbors() {
        CellState[][] grid = emptyGrid();
        assertThat(neighborsCount(1, 1, grid)).isEqualTo(0);
    }

    @Test
    public void a_cell_with_a_full_grid_has_8_neighbors() {
        CellState[][] grid = fillGrid();
        assertThat(neighborsCount(1, 1, grid)).isEqualTo(8);
    }

    @Test
    public void a_cell_with_a_horizontal_line_of_neighbors_cell_has_2_neighbors() {
        CellState[][] grid = emptyGrid();
        grid[1][0] = ALIVE;
        grid[1][2] = ALIVE;
        assertThat(neighborsCount(1, 1, grid)).isEqualTo(2);
    }

    @Test
    public void a_cell_with_a_vertical_line_of_neighbors_cell_has_2_neighbors() {
        CellState[][] grid = emptyGrid();
        grid[0][1] = ALIVE;
        grid[2][1] = ALIVE;
        assertThat(neighborsCount(1, 1, grid)).isEqualTo(2);
    }

    @Test
    public void a_cell_with_a_diagonal_line_of_neighbors_cell_has_2_neighbors() {
        CellState[][] grid = emptyGrid();
        grid[0][0] = ALIVE;
        grid[2][2] = ALIVE;
        assertThat(neighborsCount(1, 1, grid)).isEqualTo(2);
    }

    @Test
    public void a_cell_with_the_second_diagonal_line_of_neighbors_cell_has_2_neighbors() {
        CellState[][] grid = emptyGrid();
        grid[0][2] = ALIVE;
        grid[2][0] = ALIVE;
        assertThat(neighborsCount(1, 1, grid)).isEqualTo(2);
    }

}