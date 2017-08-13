package com.kata.minesweeper;

import java.util.Objects;

/**
 * Created by yannickgrenzinger on 08/01/2016.
 */
public class Mine {

    private final int rowIndex;
    private final int colIndex;

    private Mine(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public static Mine of(int rowIndex, int colIndex) {
        return new Mine(rowIndex, colIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mine mine = (Mine) o;
        return rowIndex == mine.rowIndex &&
                colIndex == mine.colIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colIndex);
    }

    @Override
    public String toString() {
        return "Mine{" +
                "rowIndex=" + rowIndex +
                ", colIndex=" + colIndex +
                '}';
    }
}
