package StringCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by yannickgrenzinger on 27/06/2015.
 */
public class StringCalculator {

    private final static String RETURN_LINE = "\n";
    private final static String COMMA = ",";

    private List<String> separators = new ArrayList<>();

    {
        separators.addAll(Arrays.asList(RETURN_LINE, COMMA));
    }

    private static String VALIDATION_REGEX = "^(//(.|\\[.+\\])\n)?(-?\\d+(.+|\n))*(-?\\d+)$";
    private static final Pattern CUSTOM_SEPARATOR_PATTERN = Pattern.compile("^(//(.|\\[.+\\])\n)((.|\\n)*)$");

    public int add(String input) {
        if (isEmpty(input)) return 0;
        if (!input.matches(VALIDATION_REGEX)) throw new RuntimeException("Wrong input");

        input = extractCustomDelimiter(input);
        checkIfNegativeNumbers(input);

        return splitInput(input).mapToInt(Integer::valueOf).filter(i -> i < 1001).sum();
    }

    private void checkIfNegativeNumbers(String input) {
        List<String> negativeNumbers = splitInput(input).filter(s -> Integer.valueOf(s) < 0).collect(Collectors.toList());
        if (!negativeNumbers.isEmpty()) throw new NegativeNumberException(negativeNumbers);
    }

    private String extractCustomDelimiter(String input) {
        Matcher matcher = CUSTOM_SEPARATOR_PATTERN.matcher(input);
        if (matcher.matches()) {
            String separator = matcher.group(2);
            separator = separator.replace("[", "").replace("]","").replace("$", "\\$").replace("*", "\\*");
            separators.add(separator);
            input = input.substring(input.indexOf('\n') + 1);
        }
        return input;
    }

    private Stream<String> splitInput(String input) {
        return Stream.of(input.split(buildSplitSeparators()));
    }

    private String buildSplitSeparators() {
        return separators.stream().collect(Collectors.joining("|"));
    }
}
