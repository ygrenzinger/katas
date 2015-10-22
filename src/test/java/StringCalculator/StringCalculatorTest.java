package StringCalculator;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @Before
    public void setup() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void should_return_0() {
        assertThat(stringCalculator.add("")).isEqualTo(0);
    }

    @Test
    public void should_return_1() {
        assertThat(stringCalculator.add("1")).isEqualTo(1);
    }

    @Test
    public void should_add_two_numbers() {
        assertThat(stringCalculator.add("1,1")).isEqualTo(2);
    }

    @Test
    public void should_add_many_numbers() {
        assertThat(stringCalculator.add("1,2,3,4")).isEqualTo(10);
    }

    @Test
    public void should_manage_line_return_separator() {
        assertThat(stringCalculator.add("1\n2,3\n4")).isEqualTo(10);
    }

    @Test(expected = RuntimeException.class)
    public void should_return_an_exception() {
        stringCalculator.add("1\n2,\n4");
    }

    @Test
    public void should_allow_to_change_delimiter() {
        assertThat(stringCalculator.add("//;\n1;2,3\n4")).isEqualTo(10);
    }

    @Test
    public void should_throw_negative_number_exception() {
        try {
            stringCalculator.add("//;\n1;-2;3;-4");
        } catch (NegativeNumberException e) {
            assertThat(e.getMessage()).isEqualTo("Negative numbers: -2 -4");
        }
    }

    @Test
    public void should_ignore_numbers_superior_to_1000() {
        assertThat(stringCalculator.add("1000,2,1001,4")).isEqualTo(1006);
    }

    @Test
    public void should_allow_to_add_a_custom_delimiter_with_more_than_one_char() {
        assertThat(stringCalculator.add("//[***]\n1***2,3***4")).isEqualTo(10);
    }

}