package tue.data;

import java.util.List;

public class Snapshot {
    private Time time;
    private List<Vertex> vertices;
    private List<Edge> edges;

    public Snapshot(Time time, List<Vertex> vertices, List<Edge> edges) {
        this.time = time;
        this.vertices = vertices;
        this.edges = edges;
    }

    public Time getTime() {
        return time;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
