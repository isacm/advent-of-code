package pt.isacm.day4;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Day4 {

    public static void main(String[] args) {
        new Day4().run();
        new Day4().runPart2();
    }

    void run() {
        final var scanner = new Scanner(this.getClass().getResourceAsStream("/day4.txt"));

        Map<Point2D, Set<Point2D>> graph = new HashMap<>();
        Map<Point2D, Character> kinds = new HashMap<>();

        int y = 0;
        while (scanner.hasNextLine()) {
            final var line = scanner.nextLine();
            final var size = line.length();
            for (int x = 0; x < size; x++) {
                final var point = new Point2D(x, y);
                final var ch = line.charAt(x);

                graph.put(point, point.neighbours());
                kinds.put(point, ch);
            }

            y++;
        }

        final var adjacentRolls = graph.entrySet()
            .stream()
            .filter(e -> kinds.get(e.getKey()) == '@')
            .map(e -> Map.entry(e.getKey(), e.getValue().stream()
                .map(kinds::get)
                .filter(Objects::nonNull)
                .filter(ch -> ch == '@')
                .count()
            ))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        final var forkliftPositions = adjacentRolls.values()
            .stream()
            .filter(v -> v < 4)
            .count();

        System.out.println(forkliftPositions);
    }

    void runPart2() {
        final var scanner = new Scanner(this.getClass().getResourceAsStream("/day4.txt"));

        Map<Point2D, Set<Point2D>> graph = new HashMap<>();
        Map<Point2D, Character> kinds = new HashMap<>();

        int y = 0;
        while (scanner.hasNextLine()) {
            final var line = scanner.nextLine();
            final var size = line.length();
            for (int x = 0; x < size; x++) {
                final var point = new Point2D(x, y);
                final var ch = line.charAt(x);

                graph.put(point, point.neighbours());
                kinds.put(point, ch);
            }

            y++;
        }

        int total = 0;
        int partialTotal;
        do {
            partialTotal = 0;
            final var adjacentRolls = graph.entrySet()
                .stream()
                .filter(e -> kinds.get(e.getKey()) == '@')
                .map(e -> Map.entry(e.getKey(), e.getValue().stream()
                    .map(kinds::get)
                    .filter(Objects::nonNull)
                    .filter(ch -> ch == '@')
                    .count()
                ))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            for (final var entry : adjacentRolls.entrySet()) {

                if (entry.getValue() < 4) {
                    partialTotal++;
                    kinds.put(entry.getKey(), 'x');
                }


            }

            total += partialTotal;

        } while (partialTotal > 0);


        System.out.println(total);
    }
}
