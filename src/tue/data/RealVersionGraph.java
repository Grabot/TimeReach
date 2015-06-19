package tue.data;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * NOTE: fG = fancy G that UTF8 does not have
 * Definition of version graph:
 *
 * Given an evolving graph fG_I = {G_t_i, G_t_i+1, . . . , G_t_j }, its
 * version graph is an edge and node labeled, directed graph
 * VG_I = (V_I , E_I , L_u, L_e)
 */

public class RealVersionGraph implements IVersionGraph {
    private Interval interval = new Interval(-1, -1);
    private HashMap<Edge, IntervalSet> edgeIntervals = new HashMap<>();
    private HashMap<Integer, IntervalSet> vertexIntervals = new HashMap<>();
    private HashMap<Integer, Set<Integer>> edges = new HashMap<>();

    public RealVersionGraph() {

    }

    public RealVersionGraph(List<Snapshot> eg) {
        assert eg.size() >= 1;
        eg.forEach(this::addSnapshot);
        eg.stream().map(Snapshot::getEdges).forEach(x -> x.forEach(this::getIntervalSet));
    }

    public void addSnapshot(Snapshot snap) {

        for(Edge e : snap.getEdges()) {
            Set<Integer> set = edges.getOrDefault(e.getVertex1(), new HashSet<>());
            set.add(e.getVertex2());
            edges.put(e.getVertex1(), set);
        }

        Integer start = this.interval.getStartTime() == -1 ? snap.getTime() : this.interval.getStartTime();
        Integer end = this.interval.getEndTime() == -1 ? snap.getTime() : this.interval.getEndTime();

        Integer start = this.interval != null ? this.interval.getStartTime() : snap.getTime();
        Integer end = this.insnap.getTime();

        this.interval = new Interval(start, end);
    }

    // V_I, vertices of graph
    public Set<Integer> getVertices() {
        Set<Integer> vertices = vertexIntervals.keySet();
        return vertices;
    }

    public Set<Edge> getEdges() {
        Set<Edge> edges = edgeIntervals.keySet();
        return edges;
    }

    @Override
    public Set<Integer> neighbours(Integer u) {
        return edges.getOrDefault(u, new HashSet<>());
    }

    public IntervalSet getIntervalSet(Edge e) {
        if(edgeIntervals.containsKey(e)) {
            return edgeIntervals.get(e);
        }
        IntervalSet set = calculateIntervals(Snapshot::getEdges, e);
        edgeIntervals.put(e, set);
        return set;
    }

    public IntervalSet getIntervalSet(Integer v) {
        if(vertexIntervals.containsKey(v)) {
            return vertexIntervals.get(v);
        }
        IntervalSet set = calculateIntervals(Snapshot::getVertices, v);
        vertexIntervals.put(v, set);
        return set;
    }

    private IntervalSet calculateIntervals(Function<Snapshot, Set> contain, Object thing) {
        IntervalSet set = new IntervalSet();
        boolean inInterval = false;
        Integer start = null;
        Integer end = null;
        for (Snapshot graph : evolvingGraph){
            Set holder = contain.apply(graph);
            if (holder.contains(thing))
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
        if(inInterval && start != null && end != null) {
            set.addInterval(new Interval(start, end));
        }
        return set;
    }
}
