package pt.isacm.day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BankTest {

    @CsvSource(textBlock = """
            123456789111    | 123456789111
            1234567891111   | 234567891111
            918273645918273 | 987645918273
            """, delimiter = '|')
    @ParameterizedTest
    void overJoltageIsCorrect(String input, String expected) {
        final var bank = new Bank(input.codePoints()
                .mapToObj(Character::getNumericValue)
                .toList());

        final var overJoltage = bank.maxOverJoltage();

        Assertions.assertEquals(Long.parseLong(expected), overJoltage);
    }
}
