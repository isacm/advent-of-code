package pt.isacm.day1.part1;

public class Vault {

    private int currentPosition;
    private final int numberOfPositions;

    public Vault(int currentPosition, int numberOfPositions) {
        this.currentPosition = currentPosition;
        this.numberOfPositions = numberOfPositions;
    }

    public int position(Command command) {
        currentPosition = switch (command.direction()) {
            case LEFT -> normalize((currentPosition - command.units()) % numberOfPositions);
            case RIGHT -> (currentPosition + command.units()) % numberOfPositions;
        };

        return currentPosition;
    }

    private int normalize(int units) {
        if(units < 0) {
            return numberOfPositions + units;
        }

        return units;
    }
}
