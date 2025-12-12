package pt.isacm.day5;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

public class RangeMap {

    private final NavigableMap<Long, Range> ranges;

    public RangeMap() {
        this.ranges = new TreeMap<>();
    }

    public Optional<Range> range(long num) {
        return Optional.ofNullable(ranges.floorEntry(num))
                .map(Map.Entry::getValue)
                .filter(range -> range.contains(num));
    }

    public void addRange(Range range) {

        var shouldTryUnify = false;
        var unifiedRange = range;

        do {
            final var floorEntry = ranges.floorEntry(unifiedRange.lowerBound());
            final var ceilingEntry = ranges.ceilingEntry(unifiedRange.lowerBound());
            shouldTryUnify = false;

            if(floorEntry != null) {
                final var floorRange = floorEntry.getValue();

                if(floorRange.overlaps(unifiedRange)) {
                    unifiedRange = floorEntry.getValue().unify(unifiedRange);
                    ranges.remove(floorEntry.getKey());
                    shouldTryUnify = true;
                }
            }

            if(ceilingEntry != null) {
                final var ceilingRange = ceilingEntry.getValue();

                if(ceilingRange.overlaps(unifiedRange)) {
                    unifiedRange = ceilingRange.unify(unifiedRange);
                    ranges.remove(ceilingEntry.getKey());
                    shouldTryUnify = true;
                }
            }

        } while (shouldTryUnify);

        ranges.put(unifiedRange.lowerBound(), unifiedRange);

    }

    public Set<Range> ranges() {
        return Set.copyOf(this.ranges.values());
    }
}
