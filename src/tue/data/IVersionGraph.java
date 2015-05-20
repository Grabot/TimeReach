package tue.data;

import java.util.Set;

public interface IVersionGraph {
    Set<Vertex> getVertices();
    Set<Edge> getEdges();

    Set<Vertex> neighbours(Vertex u);

    IntervalSet l(Edge e);
    IntervalSet l(Vertex v);
}
