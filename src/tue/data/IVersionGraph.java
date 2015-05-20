package tue.data;

import java.util.Set;

public interface IVersionGraph {
    Set<Vertex> getVertices();
    Set<Edge> getEdges();
    IntervalSet l(Edge e);
    IntervalSet l(Vertex v);
}
