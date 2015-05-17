package tue.data;

public class Edge {
    private final Vertex u;
    private final Vertex v;

    public Edge(Vertex u, Vertex v) {
        this.u = u;
        this.v = v;
    }

    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + u.hashCode();
        hash = hash * 31 + v.hashCode();
        return hash;
    }
}
