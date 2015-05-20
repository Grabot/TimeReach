package tue.data;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

public class IntervalSet {

    private List<Interval> intervals;
    private BitSet bitwiseRepresentation = new BitSet();

    public IntervalSet() {
        intervals = new LinkedList<Interval>();
    }

    // Instantiate an intervalSet with a bitset
    public IntervalSet(BitSet bitset) {
        int fbit = 0;
        int lbit = 0;

        while(lbit != -1) {
            fbit = bitset.nextSetBit(lbit);
            lbit = bitset.nextClearBit(fbit);

            if (lbit == -1) {
                intervals.add(new Interval(fbit, bitset.length()));
            } else {
                intervals.add(new Interval(fbit, lbit));
            }
        }
    }

    public BitSet cross(IntervalSet is2) {
        BitSet result = (BitSet) this.getBitwiseRepresentation().clone();
        result.and(is2.getBitwiseRepresentation());
        return result;
    }

    public BitSet plus(IntervalSet is2) {
        BitSet result = (BitSet) this.getBitwiseRepresentation().clone();
        result.or(is2.getBitwiseRepresentation());
        return result;
    }

    private void minimize() {
        //TODO: change intervals to get the minimum set

    }

    private BitSet getBitwiseRepresentation() {
        return bitwiseRepresentation;
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    public void addInterval(Interval interval) {

        intervals.add(interval);

        // Add the interval to the bitwise representation
        bitwiseRepresentation.set(interval.getStartTime(), interval.getEndTime(), true);
    }

    public static IntervalSet empty() {
        return new IntervalSet();
    }
}
