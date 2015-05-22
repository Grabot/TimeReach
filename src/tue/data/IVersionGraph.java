package tue.data;

import java.util.Set;

public interface IVersionGraph {
    Set<Integer> getVertices();
    Set<Edge> getEdges();

    Set<Integer> neighbours(Integer u);

    IntervalSet getIntervalSet(Edge e);
    IntervalSet getIntervalSet(Integer v);
}
