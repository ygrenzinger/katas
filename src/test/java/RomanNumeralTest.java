import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralTest {

    @Test
    public void equal1() {
        assertThat(RomanNumeral.stringToDecimal("I")).isEqualTo(1);
    }

    @Test
    public void equal4() {
        assertThat(RomanNumeral.stringToDecimal("IV")).isEqualTo(4);
    }

    @Test
    public void equal5() {
        assertThat(RomanNumeral.stringToDecimal("V")).isEqualTo(5);
    }

    @Test
    public void equal6() {
        assertThat(RomanNumeral.stringToDecimal("VI")).isEqualTo(6);
    }

    @Test
    public void equal9() {
        assertThat(RomanNumeral.stringToDecimal("IX")).isEqualTo(9);
    }

    @Test
    public void equal11() {
        assertThat(RomanNumeral.stringToDecimal("XI")).isEqualTo(11);
    }

    @Test
    public void equal1904() {
        assertThat(RomanNumeral.stringToDecimal("MCMIV")).isEqualTo(1904);
    }

    @Test
    public void equal1954() {
        assertThat(RomanNumeral.stringToDecimal("MCMLIV")).isEqualTo(1954);
    }

    @Test
    public void equal1990() {
        assertThat(RomanNumeral.stringToDecimal("MCMXC")).isEqualTo(1990);
    }




}