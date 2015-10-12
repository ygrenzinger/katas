package RomanNumeral;

import java.util.ArrayDeque;
import java.util.Queue;

public enum RomanNumeral {
    I(1, 'I'), V(5, 'V'), X(10, 'X'), L(50, 'L'), C(100, 'C'), D(500, 'D'), M(1000, 'M');

    private int decimalValue;
    private int character;

    RomanNumeral(int value, char character) {
        decimalValue = value;
        this.character = character;
    }

    public static int stringToDecimal(String input) {
        if (input.length() == 1)  {
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
        if (next != null){
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
}
