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

    private List<Time> times;
    private HashMap<Time, List<Vertex>> vertices;
    private HashMap<Time, List<Edge>> edges;


    public VersionGraph(Interval i) {
        this.interval = i;
        times = new LinkedList<Time>();
        vertices = new HashMap<Time, List<Vertex>>();
        edges = new HashMap<Time, List<Edge>>();
    }

    // V_I, vertices of graph
    public List<Vertex> getVertices() {
        List<Vertex> nodes = new ArrayList<Vertex>();
        for (Time time : times) {
            if(interval.contains(time) && vertices.containsKey(time)) {
                nodes.addAll(vertices.get(time));
            }
        }

        return nodes;
    }

    public Set<Edge> getEdges() {
        Set<Edge> connections = new HashSet<Edge>();
        for (Time time : times) {
            if(interval.contains(time) && edges.containsKey(time)) {
                connections.addAll(edges.get(time));
            }
        }
        return connections;
    }

    public IntervalSet l(Edge e) {
        return new IntervalSet(); // ?!??!!!?!??!?!
    }

    public IntervalSet l(Vertex v) {
        return new IntervalSet(); // ?!??!!!?!??!?!
    }
}
