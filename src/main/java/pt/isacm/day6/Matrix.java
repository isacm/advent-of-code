package pt.isacm.day6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Matrix<T> {

    private final List<List<T>> matrix;

    public Matrix() {
        this.matrix = new ArrayList<>();
    }

    public void addRow(List<T> row) {
        matrix.add(row);
    }

    public Matrix<T> transpose() {
        final var transpose = new Matrix<T>();

        //Unsafe in practice, but I know in this case is not empty matrix
        final int rowSize = matrix.iterator().next().size();

        for (int i = 0; i < rowSize; i++) {
            final var vector = new ArrayList<T>();
            for (int j = 0; j < matrix.size(); j++) {
                vector.add(matrix.get(j).get(i));
            }
            transpose.addRow(vector);
        }

        return transpose;
    }

    public <V> List<V> apply(
        List<Operator> operators,
        Map<Operator, BinaryOperator<V>> binaryOperators,
        Function<List<T>, List<V>> transformer) {

        final var result = new ArrayList<V>();

        for (int i = 0; i < operators.size(); i++) {
            final var op = operators.get(i);
            final var vector = transformer.apply(matrix.get(i));

            final var val = vector.stream()
                .reduce(binaryOperators.get(op))
                .orElseThrow();

            result.add(val);
        }

        return result;

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return Objects.equals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matrix);
    }

    @Override
    public String toString() {
        return "Matrix{" +
            "matrix=" + matrix +
            '}';
    }
}
