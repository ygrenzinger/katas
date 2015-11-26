package RomanNumeral;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum RomanNumeral {
    I(1, 'I'), V(5, 'V'), X(10, 'X'), L(50, 'L'), C(100, 'C'), D(500, 'D'), M(1000, 'M');

    public static RomanNumeral[] units = {C, X, I};

    private int decimalValue;
    private char character;

    RomanNumeral(int value, char character) {
        decimalValue = value;
        this.character = character;
    }

    public String toString() {
        return String.valueOf(character);
    }

    private static List<RomanNumeral> romanNumeralsByDescendingOrder = Stream.of(RomanNumeral.values())
            .sorted((o1, o2) -> o2.decimalValue - o1.decimalValue)
            .collect(Collectors.toList());

    public static int romanToArabic(String input) {
        if (input.length() == 1) {
            return findCorrespondingNumeral(input.charAt(0)).decimalValue;
        }
        Queue<RomanNumeral> numerals = new ArrayDeque<>();
        for (int i = 0; i < input.toCharArray().length; i++) {
            numerals.add(findCorrespondingNumeral(input.toCharArray()[i]));
        }

        int total = 0;
        while (!numerals.isEmpty()) {
            total += retrieveNextDecimal(numerals);
        }

        return total;
    }

    private static int retrieveNextDecimal(Queue<RomanNumeral> numerals) {
        RomanNumeral current = numerals.remove();
        RomanNumeral next = numerals.peek();
        if (next != null) {
            switch (current) {
                case I:
                    switch (next) {
                        case V:
                            numerals.remove();
                            return 4;
                        case X:
                            numerals.remove();
                            return 9;

                    }
                case X:
                    switch (next) {
                        case L:
                            numerals.remove();
                            return 40;
                        case C:
                            numerals.remove();
                            return 90;

                    }
                case C:
                    switch (next) {
                        case D:
                            numerals.remove();
                            return 400;
                        case M:
                            numerals.remove();
                            return 900;

                    }

            }
        }
        return current.decimalValue;
    }

    private static RomanNumeral findCorrespondingNumeral(char character) {
        for (RomanNumeral romanNumeral : RomanNumeral.values()) {
            if (romanNumeral.character == character) {
                return romanNumeral;
            }
        }
        throw new IllegalArgumentException(character + " is not a roman litteral");
    }

    public static String arabicToRoman(final int arabicNumber) {
        List<Integer> remainingValue = Stream.of(units).map(unit -> unit.decimalValue)
                .map(decimalValue -> arabicNumber % decimalValue)
                .collect(Collectors.toList());
        return IntStream.range(0, units.length).mapToObj(i -> buildRomanNumberForUnit(remainingValue.get(i), units[i])).collect(Collectors.joining());

        /*
        for (RomanNumeral romanDecimalUnit : units) {
            romanNumber += buildRomanNumberForUnit(arabicNumber, romanDecimalUnit);
            arabicNumber = arabicNumber % romanDecimalUnit.decimalValue;
        }
        return romanNumber;
        */
    }

    private static String buildRomanNumberForUnit(int arabicNumber, RomanNumeral romanDecimalUnit) {
        final int unitValue = arabicNumber / romanDecimalUnit.decimalValue;
        switch (romanDecimalUnit) {
            case C:
                if (unitValue > 0) {
                    return findRomanNumberForCurrentUnit(unitValue, M, D, C);
                }
                break;
            case X:
                if (unitValue > 0) {
                    return findRomanNumberForCurrentUnit(unitValue, C, L, X);
                }
                break;
            default:
                return findRomanNumberForCurrentUnit(unitValue, X, V, I);
        }
        return "";
    }

    private static String findRomanNumberForCurrentUnit(int unitValue, RomanNumeral upperUnit, RomanNumeral middleUnit, RomanNumeral lowerUnit) {
        if (unitValue == 4) {
            return lowerUnit.toString() + middleUnit.toString();
        }
        if (unitValue == 9) {
            return lowerUnit.toString() + upperUnit.toString();
        }
        String romanNumbers = "";
        if (unitValue >= 5) {
            romanNumbers += middleUnit.toString();
            unitValue -= middleUnit.decimalValue;
        }
        if (unitValue > 0 && unitValue < 4) {
            romanNumbers += IntStream.range(0, unitValue)
                    .mapToObj(i -> lowerUnit.toString())
                    .collect(Collectors.joining());
        }
        return romanNumbers;
    }


}
