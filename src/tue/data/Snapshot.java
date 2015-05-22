package tue.data;

import tue.data.stanford.Digraph;

import java.util.List;
import java.util.Set;

public class Snapshot extends Digraph {
    private Integer time;
    private Set<Integer> vertices;
    private Set<Edge> edges;

    public Snapshot(Integer time, Set<Integer> vertices, Set<Edge> edges) {
        super(vertices.size());

        for (Edge edge : edges) {
            this.addEdge(edge.getVertex1(), edge.getVertex2());
        }
        this.time = time;
        this.vertices = vertices;
        this.edges = edges;
    }

    public Integer getTime() {
        return time;
    }

    public Set<Integer> getVertices() {
        return vertices;
    }

    public Set<Edge> getEdges() {
        return edges;
    }
}
