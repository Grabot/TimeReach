package tue.data;

public class Edge {
    private final Vertex u;
    private final Vertex v;

    public Edge(Vertex u, Vertex v) {
        this.u = u;
        this.v = v;
    }

    public Vertex getVertex1(){
        return u;
    }
    public Vertex getVertex2(){
        return v;
    }

    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + u.hashCode();
        hash = hash * 31 + v.hashCode();
        return hash;
    }

    public boolean equals(Object aThat) {
        if ( this == aThat ) return true;
        if ( !(aThat instanceof Edge) ) return false;
        Edge that = (Edge)aThat;
        return u == that.u && v == that.v;
    }
}
