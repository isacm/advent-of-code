package pt.isacm.day1.part2;

import java.util.Scanner;

public class Day1 {

    public static void main(String[] args) {
        new Day1().run();
    }

    void run() {
        final var scanner = new Scanner(this.getClass().getResourceAsStream("/day1.txt"));
        var position = 50;
        final var vault = new Vault(position, 100);
        var counter = 0;
        while (scanner.hasNext()) {
            final var line = scanner.next();

            final Command command;
            if (line.startsWith("L")) {
                command = new Command(Direction.LEFT, Integer.parseInt(line.substring(1)));
            } else {
                command = new Command(Direction.RIGHT, Integer.parseInt(line.substring(1)));
            }

            System.out.println("Original command " + command);
            final var commands = command.splitWithMaxUnits(50);
            System.out.println("Split commands " + commands);

            for(final var c : commands) {
                System.out.println("position " + position);
                final var newPosition = vault.position(c);
                System.out.println("new position " + newPosition);

                if(newPosition == 0) {
                    counter++;
                    System.out.println("counter " + counter);
                } else {
                    switch (command.direction()) {
                        case LEFT -> {
                            if(newPosition > position && position != 0) {
                                counter++;
                                System.out.println("counter " + counter);
                            }
                        }
                        case RIGHT -> {
                            if(newPosition < position && position != 0) {
                                counter++;
                                System.out.println("counter " + counter);
                            }
                        }
                    }
                }

                position = newPosition;
            }
        }

        System.out.println(counter);
    }
}
