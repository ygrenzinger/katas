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

    public static final Pattern CUSTOM_SEPARATOR_PATTERN = Pattern.compile("^(//(.)\n).*$");

    public static int add(String input) throws NegativeNumberException {
        if (isEmpty(input)) return 0;

        List<String> separators = Arrays.asList(RETURN_LINE, COMMA);

        Matcher matcher = CUSTOM_SEPARATOR_PATTERN.matcher(input);
        if (matcher.matches()) {
            String separator = matcher.group(2);
            separators = Arrays.asList(separator);
            input = input.substring(matcher.group(1).length());
        }

        if (separators.stream().anyMatch(input::contains)) {
            List<String> negativeNumbers = new ArrayList<>();
            int sum = splitInput(input, buildSplitSeparators(separators))
                    .map(s -> {
                        if (s.startsWith("-")) {
                            negativeNumbers.add(s);
                            return "0";
                        }
                        return s;
                    })
                    .mapToInt(Integer::parseInt)
                    .filter(value -> value <= 1000)
                    .sum();
            if (!negativeNumbers.isEmpty()) {
                throw new NegativeNumberException(negativeNumbers);
            }
            return sum;

        }

        return Integer.parseInt(input);
    }

    private static String buildSplitSeparators(List<String> separators) {
        return separators.stream().collect(Collectors.joining("|"));
    }

    private static Stream<String> splitInput(String input, String sep) {
        return Stream.of(input.split(sep));
    }
}
