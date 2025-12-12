package pt.isacm.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class RangeTest {

    @CsvSource(textBlock = """
            0 | 5  | 1 | 6  | true
            3 | 6  | 0 | 4  | true
            0 | 5  | 5 | 10 | true
            5 | 10 | 0 | 5  | true
            0 | 5  | 6 | 10 | false
            5 | 10 | 0 | 4  | false
            0 | 5  | 0 | 5  | true
            """, delimiter = '|')
    @ParameterizedTest
    void hasExpectedResult(Integer lb, Integer hb, Integer olb, Integer ohb, Boolean expected) {
        var range = new Range(lb, hb);

        var actual = range.overlaps(new Range(olb, ohb));

        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> addRangesUnifiedParams() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Range(0, 5),
                                new Range(5, 10)
                        ),
                        Set.of(new Range(0, 10))
                ),
                Arguments.of(
                        List.of(
                                new Range(5, 10),
                                new Range(0, 5)
                        ),
                        Set.of(new Range(0, 10))
                ),
                Arguments.of(
                        List.of(
                                new Range(0, 5),
                                new Range(2, 7)
                        ),
                        Set.of(new Range(0, 7))
                ),
                Arguments.of(
                        List.of(
                                new Range(2, 7),
                                new Range(0, 5)
                        ),
                        Set.of(new Range(0, 7))

                ),
                Arguments.of(
                        List.of(
                                new Range(0, 10),
                                new Range(1, 9)
                        ),
                        Set.of(new Range(0, 10))
                ),
                Arguments.of(
                        List.of(
                                new Range(1, 9),
                                new Range(0, 10)

                        ),
                        Set.of(new Range(0, 10))
                ),
                Arguments.of(
                        List.of(
                                new Range(0, 5),
                                new Range(7, 10)

                        ),
                        Set.of(new Range(0, 5),
                                new Range(7, 10))
                )
        );
    }

    @MethodSource("addRangesUnifiedParams")
    @ParameterizedTest
    void addRangesUnified(List<Range> ranges, Set<Range> expected) {
        var rangeMap = new RangeMap();

        ranges.forEach(rangeMap::addRange);

        var actual = rangeMap.ranges();

        Assertions.assertEquals(expected, actual);
    }
}
