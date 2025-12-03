package pt.isacm.day2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Range(String lo, String hi) {

    public List<Long> invalidIds() {

        final var loLength = lo.length();
        final var hiLength = hi.length();

        final var loIt = loLength / 2;
        final var hiIt = Math.ceilDiv(hiLength, 2);
        final var loSubRange = Long.parseLong(loIt == 0 ? lo :  lo.substring(0, loIt));
        final var hiSubRange = Long.parseLong(hi.substring(0, hiIt));

        final var loVal = Long.parseLong(lo);
        final var hiVal = Long.parseLong(hi);

        final var invalidIds = new ArrayList<Long>();

        for (var i = loSubRange; i <= hiSubRange; i++) {
            final var s = String.valueOf(i);
            invalidIds.addAll(patterns(s).stream()
                            .filter(palindrome -> palindrome.length() >= loLength)
                    .map(Long::parseLong)
                    .filter(l -> l >= loVal && l <= hiVal)
                    .toList());
        }

        return invalidIds;
    }

    public Set<Long> invalidIdsV2() {

        final var loLength = lo.length();
        final var hiLength = hi.length();

        final var hiIt = Math.ceilDiv(hiLength, 2);
        final var loSubRange = 1;
        final var hiSubRange = Long.parseLong(hi.substring(0, hiIt));

        final var loVal = Long.parseLong(lo);
        final var hiVal = Long.parseLong(hi);


        final var invalidIds = new HashSet<Long>();

        for (var i = loSubRange; i <= hiSubRange; i++) {
            final var s = String.valueOf(i);
            invalidIds.addAll(patternsV2(s, hiLength).stream()
                    .filter(palindrome -> palindrome.length() >= loLength)
                    .map(Long::parseLong)
                    .filter(l -> l >= loVal && l <= hiVal)
                    .toList());
        }

        return invalidIds;
    }

    private List<String> patterns(String startWith) {

        var result = new ArrayList<String>();

        result.add(startWith + startWith);

        return result;
    }

    private List<String> patternsV2(String startWith, int maxLen) {

        var s = startWith;
        var result = new ArrayList<String>();

        while (s.length() < maxLen) {
            s += startWith;
            result.add(s);
        }

        return result;
    }
}
