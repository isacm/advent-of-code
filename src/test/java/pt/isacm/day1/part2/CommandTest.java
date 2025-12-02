package pt.isacm.day1.part2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CommandTest {

    @Test
    void splitsInFourCommands() {
        var command = new Command(Direction.LEFT, 16);

        var result = command.splitWithMaxUnits(5);

        Assertions.assertEquals(List.of(
                new Command(Direction.LEFT, 5),
                new Command(Direction.LEFT, 5),
                new Command(Direction.LEFT, 5),
                new Command(Direction.LEFT, 1)
        ), result);
    }

    @Test
    void doesNotSplitCommand() {
        var command = new Command(Direction.LEFT, 4);

        var result = command.splitWithMaxUnits(5);

        Assertions.assertEquals(List.of(
                new Command(Direction.LEFT, 4)
        ), result);
    }

    @Test
    void splitsInThreeCommands() {
        var command = new Command(Direction.LEFT, 15);

        var result = command.splitWithMaxUnits(5);

        Assertions.assertEquals(List.of(
                new Command(Direction.LEFT, 5),
                new Command(Direction.LEFT, 5),
                new Command(Direction.LEFT, 5)
        ), result);
    }
}
