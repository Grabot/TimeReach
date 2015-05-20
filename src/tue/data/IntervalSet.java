package tue.data;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

public class IntervalSet {

    private BitSet bitwiseRepresentation = new BitSet();

    public IntervalSet() {

    }

    // Instantiate an intervalSet with a bitset
    public IntervalSet(BitSet bitset) {
        bitwiseRepresentation = bitset;
    }

    public IntervalSet cross(IntervalSet is2) {
        BitSet result = (BitSet) this.getBitwiseRepresentation().clone();
        result.and(is2.getBitwiseRepresentation());
        return new IntervalSet(result);
    }

    public IntervalSet plus(IntervalSet is2) {
        BitSet result = (BitSet) this.getBitwiseRepresentation().clone();
        result.or(is2.getBitwiseRepresentation());
        return new IntervalSet(result);
    }

    private BitSet getBitwiseRepresentation() {
        return bitwiseRepresentation;
    }

    public void addInterval(Interval interval) {
        // Add the interval to the bitwise representation
        bitwiseRepresentation.set(interval.getStartTime(), interval.getEndTime(), true);
    }

    public static IntervalSet empty() {
        return new IntervalSet();
    }
}
