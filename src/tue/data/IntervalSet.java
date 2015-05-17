package tue.data;

import java.util.LinkedList;
import java.util.List;

public class IntervalSet {

    private List<Interval> intervals;

    public IntervalSet() {
        intervals = new LinkedList<Interval>();
    }

    /**
     * Join is1 ⊗ is2
     * @param is1 IntervalSet
     * @param is2 IntervalSet'
     * @return IntervalSet joined
     */
    public IntervalSet join(IntervalSet is1, IntervalSet is2)
    {
        IntervalSet result = new IntervalSet();
        for (Interval i1 : is1.getIntervals()) {
            for (Interval i2 : is2.getIntervals()) {
                result.addInterval(i1.intersect(i2));
            }
        }

        result.minimize();
        return result;
    }

    /**
     * Merge is1 ⊕ is2 is the minimum set equivalent to is1 ∪ is2.
     * @param is1 IntervalSet
     * @param is2 IntervalSet'
     * @return IntervalSet merged
     */
    public IntervalSet merge(IntervalSet is1, IntervalSet is2)
    {
        IntervalSet result = new IntervalSet();
        for (Interval i : is1.getIntervals()) {
            result.addInterval(i);
        }
        for (Interval i : is2.getIntervals()) {
            result.addInterval(i);
        }

        result.minimize();
        return result;
    }

    public IntervalSet cross(IntervalSet is1, IntervalSet is2){ return join(is1, is2); }
    public IntervalSet plus(IntervalSet is1, IntervalSet is2) {
        return merge(is1, is2); }

    private void minimize() {
        //TODO: change intervals to get the minimum set

    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    public void addInterval(Interval interval) {
        intervals.add(interval);
    }
}
