package tue.data;

public class Interval {
    private Time time1;
    private Time time2;
    private boolean isEmpty = true;

    public static Interval empty() {
        Interval interval = new Interval();
        interval.isEmpty = true;
        return interval;
    }

    private Interval() {}

    public Interval(Time time1, Time time2) {
        this.time1 = time1;
        this.time2 = time2;
        this.isEmpty = false;
    }

    public boolean contains(Time given) {
        return time1.compareTo(given) >= 0 && given.compareTo(time2) <= 0;
    }

    public Interval intersect(Interval i2) {
        return i2;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public Time getStartTime() {
        return time1;
    }
}
