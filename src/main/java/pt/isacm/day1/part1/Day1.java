package pt.isacm.day1.part1;

import java.util.Scanner;

public class Day1 {

    public static void main(String[] args) {
        new Day1().run();
    }

    void run() {
        final var scanner = new Scanner(this.getClass().getResourceAsStream("/day1.txt"));

        final var vault = new Vault(50, 100);
        var counter = 0;
        while (scanner.hasNext()) {
            final var line = scanner.next();

            final Command command;
            if (line.startsWith("L")) {
                command = new Command(Direction.LEFT, Integer.parseInt(line.substring(1)));
            } else {
                command = new Command(Direction.RIGHT, Integer.parseInt(line.substring(1)));
            }

            final var newPosition = vault.position(command);

            if (newPosition == 0) {
                counter++;
            }
        }

        System.out.println(counter);
    }
}
