package tue.data;

/**
 * Created by Luuk on 18-6-2015.
 */
public class Query {
    public int v1;
    public int v2;
    public int startTime;
    public int endTime;

    public boolean result;
    public long duration;

    public Query(int v1, int v2, int startTime, int endTime) {
        this.v1 = v1;
        this.v2 = v2;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
