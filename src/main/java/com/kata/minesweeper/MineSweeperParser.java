package com.kata.minesweeper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yannickgrenzinger on 08/01/2016.
 */
public class MineSweeperParser {
    public static MineField parse(String gameInput) {
        String[] lines = gameInput.split("\n");
        String[] sizes = lines[0].split(" ");
        int height = Integer.parseInt(sizes[0]);
        int width = Integer.parseInt(sizes[1]);
        List<Mine> mines = IntStream.range(0, height - 1)
                .mapToObj(rowIndex -> {
                    String fields[] = lines[rowIndex + 1].split(" ");
                    List<Mine> minesInRow = IntStream.range(0, width)
                            .mapToObj(colIndex -> {
                                Mine mine = null;
                                if ("*".equals(fields[colIndex])) {
                                    mine = Mine.of(rowIndex, colIndex);
                                }
                                return Optional.ofNullable(mine);
                            })
                            .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                            .collect(Collectors.toList());
                    return minesInRow;
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        MineField mineField = MineField.of(height, width, mines);
        return mineField;
    }
}
