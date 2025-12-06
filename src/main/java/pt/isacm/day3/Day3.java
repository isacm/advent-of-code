package pt.isacm.day3;

import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {

    public static void main(String[] args) {
        final var day3 = new Day3();
        day3.run();
    }

    private void run() {
        final var scanner = new Scanner(this.getClass().getResourceAsStream("/day3.txt"));

        final var banks = new ArrayList<Bank>();

        while (scanner.hasNext()) {
            final var line = scanner.nextLine();

            final var bank = new Bank(line.codePoints()
                    .mapToObj(Character::getNumericValue)
                    .toList());

            banks.add(bank);
        }

        final var puzzle = new Puzzle(banks);
        System.out.println(puzzle.maxJoltage());
        System.out.println(puzzle.maxOverJoltage());

    }
}
