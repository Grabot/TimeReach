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
    private Interval interval;
    private final List<Snapshot> evolvingGraph = new LinkedList<>();
    private HashMap<Edge, IntervalSet> edgeIntervals = new HashMap<>();
    private HashMap<Vertex, IntervalSet> vertexIntervals = new HashMap<>();

    public VersionGraph() {

    }

    public VersionGraph(List<Snapshot> eg) {
        assert eg.size() >= 1;

        eg.forEach(this::addSnapshot);
    }

    public void addSnapshot(Snapshot snap) {
        this.evolvingGraph.add(snap);

        snap.getEdges().forEach(this::l);
        snap.getVertices().forEach(this::l);

        Time start = this.interval != null ? this.interval.getStartTime() : snap.getTime();
        Time end = snap.getTime();

        this.interval = new Interval(start, end);
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
        if(edgeIntervals.containsKey(e)) {
            return edgeIntervals.get(e);
        }

        IntervalSet set = new IntervalSet();
        boolean inInterval = false;
        Time start = null;
        Time end = null;
        for (Snapshot graph : evolvingGraph){
            if(graph.getEdges().contains(e))
            {
                if(!inInterval) {
                    start = graph.getTime();
                    inInterval = true;
                }

                end = graph.getTime();
            } else {
                inInterval = false;
                if(start != null && end != null) {
                    set.addInterval(new Interval(start, end));
                    start = null;
                    end = null;
                }
            }
        }
        edgeIntervals.put(e, set);
        return set;
    }

    public IntervalSet l(Vertex v) {
        if(vertexIntervals.containsKey(v)) {
            return vertexIntervals.get(v);
        }

        IntervalSet set = new IntervalSet();
        boolean inInterval = false;
        Time start = null;
        Time end = null;
        for (Snapshot graph : evolvingGraph){
            if(graph.getVertices().contains(v))
            {
                if(!inInterval) {
                    start = graph.getTime();
                    inInterval = true;
                }

                end = graph.getTime();
            } else {
                inInterval = false;
                if(start != null && end != null) {
                    set.addInterval(new Interval(start, end));
                    start = null;
                    end = null;
                }
            }
        }
        vertexIntervals.put(v, set);
        return set;
    }
}
