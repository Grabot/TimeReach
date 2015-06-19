package tue.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Luuk on 18-6-2015.
 */
public class RandomQueryGenerator {

    public static List<Query> create (List<TimeEdge> timeEdges, int count) {

        int startTime = -1;
        int startNode = -1;
        int endTime = -1;
        int endNode = -1;
        for (TimeEdge timeEdge : timeEdges) {
            if (startNode == -1 || timeEdge.v1 < startNode) {
                startNode = timeEdge.v1;
            }
            if (startNode == -1 || timeEdge.v2 < startNode) {
                startNode = timeEdge.v2;
            }
            if (startTime == -1 || timeEdge.startTime < startTime) {
                startTime = timeEdge.startTime;
            }
            if (endNode == -1 || timeEdge.v1 > endNode) {
                endNode = timeEdge.v1;
            }
            if (endNode == -1 || timeEdge.v2 > endNode) {
                endNode = timeEdge.v2;
            }
            if (endTime == -1 || timeEdge.endTime > endTime) {
                endTime = timeEdge.endTime;
            }
        }

        // Pick random nodes and timespans
        Random rand = new Random();

        startNode = startNode - (int)((startNode - endNode) * 0.25) < 0 ? 0 : startNode - (int)((startNode - endNode) * 0.25);
        endNode = endNode + (int)((startNode - endNode) * 0.25);

        List<Query> queries = new ArrayList<Query>();
        for (int i = 0; i < count; i++) {
            int v1 = rand.nextInt((endNode - startNode) + 1) + startNode;
            int v2 = rand.nextInt((endNode - startNode) + 1) + startNode;
            int t1 = rand.nextInt((endTime - startTime) + 1) + startTime;
            int t2 = rand.nextInt((endTime - startTime) + 1) + startTime;

            if (t1 < t2) {
                queries.add(new Query(v1, v2, t1, t2));
            } else {
                queries.add(new Query(v1, v2, t2, t1));
            }
        }

        return queries;
    }
}
