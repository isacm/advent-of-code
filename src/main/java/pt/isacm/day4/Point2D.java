package pt.isacm.day4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public record Point2D(int x, int y) {

    Set<Point2D> neighbours() {

        final var neighbours = new HashSet<Point2D>();

        if (x - 1 >= 0) {
            neighbours.add(new Point2D(x - 1, y));
            neighbours.add(new Point2D(x - 1, y + 1));
        }

        if (y - 1 >= 0) {
            neighbours.add(new Point2D(x, y - 1));
            neighbours.add(new Point2D(x + 1, y - 1));
        }

        if (x - 1 >= 0 && y - 1 >= 0) {
            neighbours.add(new Point2D(x - 1, y - 1));
        }

        neighbours.add(new Point2D(x + 1, y));
        neighbours.add(new Point2D(x, y + 1));
        neighbours.add(new Point2D(x + 1, y + 1));

        return neighbours;
    }
}
