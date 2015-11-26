package RomanNumeral;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralTest {

    @Test
    public void should_return_I_when_1() {
        assertThat(RomanNumeral.arabicToRoman(1)).isEqualTo("I");
    }

    @Test
    public void should_return_II_when_2() {
        assertThat(RomanNumeral.arabicToRoman(2)).isEqualTo("II");
    }

    @Test
    public void should_return_III_when_3() {
        assertThat(RomanNumeral.arabicToRoman(3)).isEqualTo("III");
    }

    @Test
    public void should_return_IV_when_4() {
        assertThat(RomanNumeral.arabicToRoman(4)).isEqualTo("IV");
    }

    @Test
    public void should_return_V_when_5() {
        assertThat(RomanNumeral.arabicToRoman(5)).isEqualTo("V");
    }

    @Test
    public void should_return_VII_when_7() {
        assertThat(RomanNumeral.arabicToRoman(7)).isEqualTo("VII");
    }

    @Test
    public void should_return_IX_when_9() {
        assertThat(RomanNumeral.arabicToRoman(9)).isEqualTo("IX");
    }

    @Test
    public void should_return_XXVII_when_27() {
        assertThat(RomanNumeral.arabicToRoman(27)).isEqualTo("XXVII");
    }

    @Test
    public void should_return_CMXCIX_when_999() {
        assertThat(RomanNumeral.arabicToRoman(999)).isEqualTo("CMXCIX");
    }

    @Test
    public void should_return_1_when_I() {
        assertThat(RomanNumeral.romanToArabic("I")).isEqualTo(1);
    }

    @Test
    public void should_return_4_when_IV() {
        assertThat(RomanNumeral.romanToArabic("IV")).isEqualTo(4);
    }

    @Test
    public void should_return_5_when_V() {
        assertThat(RomanNumeral.romanToArabic("V")).isEqualTo(5);
    }

    @Test
    public void should_return_6_when_VI() {
        assertThat(RomanNumeral.romanToArabic("VI")).isEqualTo(6);
    }

    @Test
    public void should_return_9_when_IX() {
        assertThat(RomanNumeral.romanToArabic("IX")).isEqualTo(9);
    }

    @Test
    public void should_return_11_when_XI() {
        assertThat(RomanNumeral.romanToArabic("XI")).isEqualTo(11);
    }

    @Test
    public void should_return_1904_when_MCMIV() {
        assertThat(RomanNumeral.romanToArabic("MCMIV")).isEqualTo(1904);
    }

    @Test
    public void should_return_1954_when_MCMLIV() {
        assertThat(RomanNumeral.romanToArabic("MCMLIV")).isEqualTo(1954);
    }

    @Test
    public void should_return_1990_when_MCMXC() {
        assertThat(RomanNumeral.romanToArabic("MCMXC")).isEqualTo(1990);
    }


}