package pt.isacm.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class Day6 {

    public static void main(String[] args) {
        new Day6().run();
        new Day6().runPart2();
    }

    private void runPart2() {
        final var scanner = new Scanner(this.getClass().getResourceAsStream("/day6.txt"));


        final var matrix = new Matrix<String>();
        final var operators = new ArrayList<Operator>();
        var lines = new ArrayList<String>();
        String opRow = "";

        while (scanner.hasNext()) {
            final var line = scanner.nextLine();

            if (scanner.hasNext()) {
                lines.add(line);
            } else {
                opRow = line;
            }
        }

        final var sizes = new ArrayList<Integer>();

        var count = 0;
        for (int i = 0; i < opRow.length(); i++) {
            final var ch = opRow.charAt(i);
            if (ch == '+') {
                operators.add(Operator.ADD);
                if (count > 0) {
                    sizes.add(count);
                }
                count = 0;
            } else if (opRow.charAt(i) == '*') {
                operators.add(Operator.MULT);
                sizes.add(count);
                count = 0;
            } else {
                count++;
            }
        }

        for (final var line : lines) {
            var currIndex = 0;
            final var row = new ArrayList<String>();
            for (final var size : sizes) {
                row.add(line.substring(currIndex, currIndex + size));
                currIndex += (size + 1);
            }

            row.add(line.substring(currIndex));
            matrix.addRow(row);
        }

        final var vector = matrix.transpose().apply(operators,
            Map.of(Operator.ADD, Long::sum,
                Operator.MULT, (Long l1, Long l2) -> l1 * l2),
            this::convert);

        System.out.println(vector.stream().reduce(Long::sum));
    }

    private List<Long> convert(List<String> columns) {

        final var colsSize = columns.iterator().next().length();
        final var result = new ArrayList<Long>();

        for (int i = 0; i < colsSize; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < columns.size(); j++) {
                final var ch = columns.get(j).charAt(i);

                if (ch != ' ') {
                    s.append(ch);
                }
            }

            result.add(Long.parseLong(s.toString()));
        }

        return result;
    }

    private void run() {
        final var scanner = new Scanner(this.getClass().getResourceAsStream("/day6.txt"));

        final var matrix = new Matrix<Long>();
        List<Operator> operators = List.of();

        while (scanner.hasNext()) {
            final var line = scanner.nextLine();
            final var columns = line.split("(\\ )+");

            if (scanner.hasNext()) {
                final var row = Arrays.stream(columns)
                    .filter(Predicate.not(String::isEmpty))
                    .map(Long::parseLong)
                    .toList();

                matrix.addRow(row);
            } else {
                operators = Arrays.stream(columns)
                    .map(s -> s.equals("+") ? Operator.ADD : Operator.MULT)
                    .toList();
            }
        }

        final var vector = matrix.transpose().apply(operators,
            Map.of(Operator.ADD, Long::sum,
                Operator.MULT, (Long l1, Long l2) -> l1 * l2),
            Function.identity());

        System.out.println(vector.stream().reduce(Long::sum));
    }
}
