package pt.isacm.day6;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class MatrixTest {

    @Test
    void transposes_matrix() {

        final var matrix = new Matrix();
        matrix.addRow(List.of(1L, 2L, 3L, 4L, 5L));
        matrix.addRow(List.of(6L, 7L, 8L, 9L, 10L));

        var expected = new Matrix();
        expected.addRow(List.of(1L, 6L));
        expected.addRow(List.of(2L, 7L));
        expected.addRow(List.of(3L, 8L));
        expected.addRow(List.of(4L, 9L));
        expected.addRow(List.of(5L, 10L));


        var actual = matrix.transpose();


        Assertions.assertEquals(expected, actual);

    }
}
