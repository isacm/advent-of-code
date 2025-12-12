package pt.isacm.day5;

public record Range(long lowerBound, long upperBound) {

    public boolean overlaps(Range other) {
        return lowerBound <= other.upperBound() && other.lowerBound() <= upperBound;
    }

    public Range unify(Range other) {

        if(!overlaps(other)) {
            throw new IllegalArgumentException("Ranges must overlap to be unified");
        }

        return new Range(
                Math.min(lowerBound, other.lowerBound()),
                Math.max(upperBound, other.upperBound())
        );
    }

    public boolean contains(long num) {
        return num >= lowerBound && num <= upperBound;
    }

    public long rangeSize() {
        return upperBound - lowerBound + 1;
    }
}
