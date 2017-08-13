package com.kata.minesweeper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yannickgrenzinger on 08/01/2016.
 */
public class MineField {
    private final List<Mine> mines;
    private final int height;
    private final int width;

    public MineField(int height, int width, List<Mine> mines) {
        this.height = height;
        this.width = width;
        this.mines = mines;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public List<Mine> mines() {
        return mines;
    }

    public static MineField of(int height, int width, List<Mine> mines) {
        return new MineField(height, width, mines);
    }
}
