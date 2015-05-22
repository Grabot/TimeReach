package tue.data;

import java.util.Set;

public interface IVersionGraph {
    Set<Integer> getVertices();
    Set<Edge> getEdges();

    Set<Integer> neighbours(Integer u);

    IntervalSet l(Edge e);
    IntervalSet l(Integer v);
}
