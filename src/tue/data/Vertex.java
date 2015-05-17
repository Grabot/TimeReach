package tue.data;

public class Vertex {
    private int id;

    public Vertex(int id) {
        this.id = id;
    }

    public int hashCode() {
        return id;
    }

    public boolean equals(Object aThat) {
        if ( this == aThat ) return true;
        if ( !(aThat instanceof Vertex) ) return false;
        Vertex that = (Vertex)aThat;
        return id == that.id;
    }
}
