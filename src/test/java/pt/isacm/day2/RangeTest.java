package pt.isacm.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class RangeTest {

    @CsvSource(textBlock = """
            11         | 22         | 11,22
            95         | 115        | 99
            998        | 1012       | 1010
            1188511880 | 1188511890 | 1188511885
            222220     | 222224     | 222222
            1698522    | 1698528    | 
            446443     | 446449     | 446446
            38593856   | 38593862   | 38593859
            """, delimiter = '|')
    @ParameterizedTest
    void hasExpectedRanges(String lo, String hi, String expectedList) {

        final var expected = new ArrayList<Long>();

        if(expectedList != null) {
            Arrays.stream(expectedList.split(","))
                    .map(Long::parseLong)
                    .forEach(expected::add);
        }

        var range = new Range(lo, hi);

        var actual = range.invalidIds();

        Assertions.assertEquals(expected, actual);
    }

    @CsvSource(textBlock = """
            11         | 22         | 11,22
            95         | 115        | 99,111
            998        | 1012       | 999,1010
            1188511880 | 1188511890 | 1188511885
            222220     | 222224     | 222222
            1698522    | 1698528    | 
            446443     | 446449     | 446446
            38593856   | 38593862   | 38593859
            565653     | 565659     | 565656
            824824821  | 824824827  | 824824824
            2121212118 | 2121212124 | 2121212121
            """, delimiter = '|')
    @ParameterizedTest
    void hasExpectedRangesV2(String lo, String hi, String expectedList) {

        final var expected = new HashSet<Long>();

        if(expectedList != null) {
            Arrays.stream(expectedList.split(","))
                    .map(Long::parseLong)
                    .forEach(expected::add);
        }

        var range = new Range(lo, hi);

        var actual = range.invalidIdsV2();

        Assertions.assertEquals(expected, actual);
    }
}
