package GameOfLife;

/**
 * Created by yannickgrenzinger on 24/07/2015.
 */
public enum CellState {
    ALIVE, DEAD;



    public CellState nextGeneration(int numberOfNeighbors) {
        if (numberOfNeighbors == 2) {
            return this;
        }
        if (numberOfNeighbors == 3) {
            return ALIVE;
        }
        return DEAD;
    }
}
