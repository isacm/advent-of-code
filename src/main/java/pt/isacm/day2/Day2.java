package pt.isacm.day2;

import java.util.Arrays;
import java.util.Scanner;

public class Day2 {

    public static void main(String[] args) {
        new Day2().runPart1();
        new Day2().runPart2();
    }

    private void runPart1() {
        final var scanner = new Scanner(this.getClass().getResourceAsStream("/day2.txt"));

        final var input = scanner.nextLine();

        final var sum = Arrays.stream(input.split(","))
                .map(range -> {
                    final var ids = range.split("-");
                    return new Range(ids[0], ids[1]);
                })
                .flatMap(r -> r.invalidIds().stream())
                .reduce(Long::sum);

        System.out.println(sum);
    }

    private void runPart2() {
        final var scanner = new Scanner(this.getClass().getResourceAsStream("/day2.txt"));

        final var input = scanner.nextLine();

        final var sum = Arrays.stream(input.split(","))
                .map(range -> {
                    final var ids = range.split("-");
                    return new Range(ids[0], ids[1]);
                })
                .flatMap(r -> r.invalidIdsV2().stream())
                .reduce(Long::sum);

        System.out.println(sum);
    }
}
