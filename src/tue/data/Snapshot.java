package tue.data;

import java.util.List;
import java.util.Set;

public class Snapshot {
    private Integer time;
    private Set<Vertex> vertices;
    private Set<Edge> edges;

    public Snapshot(Integer time, Set<Vertex> vertices, Set<Edge> edges) {
        this.time = time;
        this.vertices = vertices;
        this.edges = edges;
    }

    public Integer getTime() {
        return time;
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public Set<Edge> getEdges() {
        return edges;
    }
}
