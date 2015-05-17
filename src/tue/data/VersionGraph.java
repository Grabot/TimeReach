package tue.data;

import java.util.*;

/**
 * NOTE: fG = fancy G that UTF8 does not have
 * Definition of version graph:
 *
 * Given an evolving graph fG_I = {G_t_i, G_t_i+1, . . . , G_t_j }, its
 * version graph is an edge and node labeled, directed graph
 * VG_I = (V_I , E_I , L_u, L_e)
 */

public class VersionGraph {
    private final Interval interval;
    private final List<Snapshot> evolvingGraph;

    public VersionGraph(List<Snapshot> eg) {
        assert eg.size() >= 1;

        Time start = eg.get(0).getTime();
        Time end = eg.get(eg.size() - 1).getTime();

        assert start.compareTo(end) <= 0;

        this.interval = new Interval(start, end);
        this.evolvingGraph = eg;
    }

    public VersionGraph(Interval i, List<Snapshot> eg) {
        this.interval = i;
        this.evolvingGraph = eg;
    }

    // V_I, vertices of graph
    public Set<Vertex> getVertices() {
        Set<Vertex> vertices = new HashSet<Vertex>();

        for (Snapshot graph : evolvingGraph){
            vertices.addAll(graph.getVertices());
        }

        return vertices;
    }

    public Set<Edge> getEdges() {
        Set<Edge> edges = new HashSet<Edge>();

        for (Snapshot graph : evolvingGraph){
            edges.addAll(graph.getEdges());
        }

        return edges;
    }

    public IntervalSet l(Edge e) {
        IntervalSet set = new IntervalSet();
        //TODO: calculate timespan
        return set;
    }

    public IntervalSet l(Vertex v) {
        IntervalSet set = new IntervalSet();
        //TODO: calculate timespan
        return set;
    }
}
