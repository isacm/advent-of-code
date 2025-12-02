package pt.isacm.day1.part2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VaultTest {

    @Test
    void moves_left_properly() {

        var vault = new Vault(5, 10);

        var times = vault.howManyPassesOnZero(new Command(Direction.LEFT, 16));

        Assertions.assertEquals(2, times);
    }

    @Test
    void moves_left_properly_and_stays_on_zero() {

        var vault = new Vault(5, 10);

        var times = vault.howManyPassesOnZero(new Command(Direction.LEFT, 15));

        Assertions.assertEquals(2, times);
    }

    @Test
    void moves_right_properly() {

        var vault = new Vault(5, 10);

        var times = vault.howManyPassesOnZero(new Command(Direction.RIGHT, 16));

        Assertions.assertEquals(2, times);
    }

    @Test
    void moves_right_properly_and_stays_on_zero() {

        var vault = new Vault(5, 10);

        var times = vault.howManyPassesOnZero(new Command(Direction.RIGHT, 15));

        Assertions.assertEquals(2, times);
    }
}
