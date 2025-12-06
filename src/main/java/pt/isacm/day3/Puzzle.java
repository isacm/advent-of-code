package pt.isacm.day3;

import java.util.List;

public record Puzzle(List<Bank> banks) {

    public int maxJoltage() {
        return banks.stream()
                .map(Bank::maxJoltage)
                .reduce(0, Integer::sum);
    }

    public long maxOverJoltage() {
        return banks.stream()
                .map(Bank::maxOverJoltage)
                .reduce(0L, Long::sum);
    }
}
