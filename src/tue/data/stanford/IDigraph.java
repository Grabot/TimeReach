package tue.data.stanford;

/**
 * Created by Luuk on 18-6-2015.
 */
public interface IDigraph {
    int V();

    int E();

    void addEdge(int v, int w);

    Iterable<Integer> adj(int v);

    int outdegree(int v);

    IDigraph reverse();

    String toString();
}
