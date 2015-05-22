package tue.data;

public class Edge {
    private final int u;
    private final int v;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public int getVertex1(){
        return u;
    }
    public int getVertex2(){
        return v;
    }

    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + u;
        hash = hash * 31 + v;
        return hash;
    }

    public boolean equals(Object aThat) {
        if ( this == aThat ) return true;
        if ( !(aThat instanceof Edge) ) return false;
        Edge that = (Edge)aThat;
        return u == that.u && v == that.v;
    }
}
