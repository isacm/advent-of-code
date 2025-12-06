package pt.isacm.day3;

import java.util.List;

public record Bank(List<Integer> batteries) {

    public int maxJoltage() {
        int fstMax = 0;
        int sndMax = 0;
        int size = batteries.size();

        for(int i = 0; i < size; i++) {
            final var joult = batteries.get(i);

            if(joult > fstMax && i != size - 1) {
                fstMax = joult;
                sndMax = 0;
            } else if(joult > sndMax) {
                sndMax = joult;
            }
        }

        return (fstMax * 10) + sndMax;
    }

    public long maxOverJoltage() {

        int sequenceSize = 12;
        int[] maximums = new int[sequenceSize];
        int size = batteries.size();

        for(int i = 0; i < size; i++) {
            final var joult = batteries.get(i);

            final var subSeq = Math.min(sequenceSize, size - i);

            for(int j = sequenceSize - subSeq; j < sequenceSize; j++) {
                final var val = maximums[j];

                if(joult > val) {
                    maximums[j] = joult;

                    for(int k = j + 1; k < sequenceSize; k++) {
                        maximums[k] = 0;
                    }

                    break;
                }
            }
        }


        long overJoltage = 0;
        for(int i = sequenceSize - 1; i >= 0; i--) {
            final var multiplier = (long) Math.pow(10, (sequenceSize - 1) - i);
            final var max = maximums[i] * multiplier;
            overJoltage += max;
        }

        return overJoltage;
    }


}
