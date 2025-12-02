package pt.isacm.day1.part1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VaultTest {

    @Test
    void moves_left_properly() {

        var vault = new Vault(5, 10);

        var position = vault.position(new Command(Direction.LEFT, 8));

        Assertions.assertEquals(7, position);
    }

    @Test
    void moves_right_properly() {
        var vault = new Vault(5, 10);

        var position = vault.position(new Command(Direction.RIGHT, 8));

        Assertions.assertEquals(3, position);
    }

    @Test
    void moves_right_to_zero() {
        var vault = new Vault(5, 10);

        var position = vault.position(new Command(Direction.RIGHT, 5));

        Assertions.assertEquals(0, position);
    }

    @Test
    void moves_left_to_zero() {
        var vault = new Vault(5, 10);

        var position = vault.position(new Command(Direction.LEFT, 5));

        Assertions.assertEquals(0, position);
    }
}
