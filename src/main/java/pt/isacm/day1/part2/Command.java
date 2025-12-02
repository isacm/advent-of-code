package pt.isacm.day1.part2;

import java.util.ArrayList;
import java.util.List;

public record Command(Direction direction, int units) {

    public List<Command> splitWithMaxUnits(int maxUnits) {
        if(units < maxUnits) {
            return List.of(this);
        }

        var currentUnits = units;
        final var commands = new ArrayList<Command>();
        while (currentUnits > 0 ) {
            commands.add(new Command(direction, Math.min(currentUnits, maxUnits)));
            currentUnits -= maxUnits;
        }

        return commands;
    }
}
