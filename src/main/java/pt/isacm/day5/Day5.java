package pt.isacm.day5;

import java.util.Scanner;

public class Day5 {

    public static void main(String[] args) {
        new Day5().run();
    }

    private void run() {
        final var scanner = new Scanner(this.getClass().getResourceAsStream("/day5.txt"));

        final var rangeMap = new RangeMap();

        while(scanner.hasNext()) {
            final var line = scanner.nextLine();

            if(line.isEmpty()) {
                break;
            }

            final var range = line.split("-");

            rangeMap.addRange(new Range(
                    Long.parseLong(range[0]),
                    Long.parseLong(range[1])
            ));
        }

        var count = 0;

        while(scanner.hasNext()) {
            final var num = Long.parseLong(scanner.nextLine());
            if(rangeMap.range(num).isPresent()) {
                count++;
            }
        }

        System.out.println(count);

        System.out.println(rangeMap.ranges().stream()
                .map(Range::rangeSize)
                .reduce(Long::sum));
    }
}
