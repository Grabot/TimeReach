package tue.data;

import java.util.BitSet;

public class IntervalSet {

    private BitSet bitwiseRepresentation = new BitSet();

    public IntervalSet() {

    }

    // Instantiate an intervalSet with a bitset
    public IntervalSet(BitSet bitset) {
        bitwiseRepresentation = bitset;
    }

    public IntervalSet cross(IntervalSet is2) {
        BitSet result = (BitSet) this.getBits().clone();
        result.and(is2.getBits());
        return new IntervalSet(result);
    }

    public IntervalSet plus(IntervalSet is2) {
        BitSet result = (BitSet) this.getBits().clone();
        result.or(is2.getBits());
        return new IntervalSet(result);
    }

    public boolean covers(IntervalSet is2) {
        return this.getBits().intersects(is2.getBits());
    }

    public boolean covers(Integer time) {
        return bitwiseRepresentation.get(time);
    }

    private BitSet getBits() {
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
