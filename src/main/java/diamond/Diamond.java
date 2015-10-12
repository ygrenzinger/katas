package diamond;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yannickgrenzinger on 28/06/2015.
 */
public class Diamond {

    public static String print(Character character) {
        List<String> lines = new ArrayList<>();
        int shift = character.compareTo('A') + 1;
        for (int i = 0; i < shift; i++) {
            lines.add(buildLine(shift, i));
        }
        return buildRowSymmetry(lines).stream().collect(Collectors.joining("\n"));
    }

    private static List<String> buildRowSymmetry(List<String> lines) {
        List<String> linesWithSymmetry = new ArrayList<>();
        linesWithSymmetry.addAll(lines);
        for (int i = lines.size() - 2; i >= 0; i--) {
            linesWithSymmetry.add(lines.get(i));
        }
        return linesWithSymmetry;
    }

    private static String buildLine(int shift, int i) {
        StringBuilder line = new StringBuilder();
        for (int j = 0; j < shift; j++) {
            if (i + j == shift - 1) {
                 line.append((char)((int)'A' + i));
            } else {
                line.append(' ');
            }

        }
        buildRowSymmetry(line);
        return line.toString();
    }

    private static void buildRowSymmetry(StringBuilder line) {
        StringBuilder symmetry = new StringBuilder(line.toString().substring(0, line.length()-1));
        line.append(symmetry.reverse());
    }
}
