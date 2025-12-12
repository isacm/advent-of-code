package pt.isacm.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class RangeMapTest {

    @Test
    void getsExpectedRanges() {
        var rangeMap = new RangeMap();

        rangeMap.addRange(new Range(3, 5));
        rangeMap.addRange(new Range(10, 14));
        rangeMap.addRange(new Range(16, 20));
        rangeMap.addRange(new Range(12, 18));

        Assertions.assertEquals(Optional.empty(), rangeMap.range(1));
        Assertions.assertEquals(Optional.of(new Range(3, 5)), rangeMap.range(5));
        Assertions.assertEquals(Optional.empty(), rangeMap.range(8));
        Assertions.assertEquals(Optional.of(new Range(10, 20)), rangeMap.range(11));
        Assertions.assertEquals(Optional.of(new Range(10, 20)), rangeMap.range(17));
        Assertions.assertEquals(Optional.empty(), rangeMap.range(32));
    }

    private static Stream<Arguments> rangeMapUnifiedProperlyParams() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Range(0, 5),
                                new Range(15, 20),
                                new Range(4, 16)
                        ),
                        Set.of(
                                new Range(0, 20)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Range(0, 5),
                                new Range(15, 20),
                                new Range(5, 16)
                        ),
                        Set.of(
                                new Range(0, 20)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Range(5, 10),
                                new Range(15, 20),
                                new Range(0, 16)
                        ),
                        Set.of(
                                new Range(0, 20)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Range(5, 10),
                                new Range(15, 20),
                                new Range(0, 15)
                        ),
                        Set.of(
                                new Range(0, 20)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Range(5, 10),
                                new Range(15, 20),
                                new Range(2, 25)
                        ),
                        Set.of(
                                new Range(2, 25)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Range(5, 10),
                                new Range(15, 20),
                                new Range(10, 15)
                        ),
                        Set.of(
                                new Range(5, 20)
                        )
                ),
                Arguments.of(
                        List.of(
                                new Range(0, 5),
                                new Range(15, 20),
                                new Range(7, 13)
                        ),
                        Set.of(
                                new Range(0, 5),
                                new Range(15, 20),
                                new Range(7, 13)
                        )
                )
        );
    }

    @MethodSource("rangeMapUnifiedProperlyParams")
    @ParameterizedTest
    void rangeMapUnifiedProperly(List<Range> ranges, Set<Range> expected) {
        var rangeMap = new RangeMap();

        ranges.forEach(rangeMap::addRange);

        var actual = rangeMap.ranges();

        Assertions.assertEquals(expected, actual);
    }
}
