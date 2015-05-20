package tue.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FakeVersionGraph implements IVersionGraph {
    private HashMap<Edge, IntervalSet> edgeIntervals = new HashMap<>();
    private HashMap<Vertex, IntervalSet> vertexIntervals = new HashMap<>();
    private HashSet<Edge> edges = new HashSet<>();
    private HashSet<Vertex> vertices = new HashSet<>();

    public void addEdge(Edge edge, Interval interval) {
        IntervalSet edset = edgeIntervals.getOrDefault(edge, new IntervalSet());

        Vertex vertex1 = edge.getVertex1();
        Vertex vertex2 = edge.getVertex2();
        edges.add(edge);
        vertices.add(vertex1);
        vertices.add(vertex2);

        IntervalSet vxset1 = vertexIntervals.getOrDefault(vertex1, new IntervalSet());
        IntervalSet vxset2 = vertexIntervals.getOrDefault(vertex2, new IntervalSet());

        vxset1.addInterval(interval);
        vxset2.addInterval(interval);
        edset.addInterval(interval);

        edgeIntervals.put(edge, edset);
    }

    @Override
    public Set<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public Set<Edge> getEdges() {
        return edges;
    }

    @Override
    public IntervalSet l(Edge e) {
        return edgeIntervals.getOrDefault(e, new IntervalSet());
    }

    @Override
    public IntervalSet l(Vertex v) {
        return vertexIntervals.getOrDefault(v, new IntervalSet());
    }
}
