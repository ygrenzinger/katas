import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    @Test
    public void should_return_0() throws NegativeNumberException {
        assertThat(StringCalculator.add("")).isEqualTo(0);
    }

    @Test
    public void should_return_1() throws NegativeNumberException {
        assertThat(StringCalculator.add("1")).isEqualTo(1);
    }

    @Test
    public void should_add_two_numbers() throws NegativeNumberException {
        assertThat(StringCalculator.add("1,1")).isEqualTo(2);
    }

    @Test
    public void should_add_many_numbers() throws NegativeNumberException {
        assertThat(StringCalculator.add("1,2,3,4")).isEqualTo(10);
    }

    @Test
    public void should_manage_another_separator() throws NegativeNumberException {
        assertThat(StringCalculator.add("1\n2,3\n4")).isEqualTo(10);
    }

    @Test(expected = Exception.class)
    public void should_return_an_exception() throws NegativeNumberException {
        StringCalculator.add("1\n2,\n4");
    }

    @Test
    public void should_allow_to_change_delimiter() throws NegativeNumberException {
        StringCalculator.add("//;\n1;2;3;4");
    }

    @Test
    public void should_throw_negative_number_exception() {
        try {
            StringCalculator.add("//;\n1;-2;3;-4");
        } catch (NegativeNumberException e) {
            assertThat(e.getMessage()).isEqualTo("Negative numbers: -2 -4");
        }
    }

    @Test
    public void should_ignore_numbers_superior_to_1000() throws NegativeNumberException {
        assertThat(StringCalculator.add("1000,2,1001,4")).isEqualTo(1006);
    }

}