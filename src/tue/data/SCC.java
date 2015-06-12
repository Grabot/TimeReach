package tue.data;

import tue.algorithms.stanford.DirectedDFS;
import tue.algorithms.stanford.TarjanSCC;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SCC {
    Map<TarjanSCC, Integer> map = new LinkedHashMap<>();
    Map<Snapshot, TarjanSCC> tarjans = new HashMap<>();
    int size;

    // SCC Graph Snaphots
    List<Snapshot> graphSnapshots = new ArrayList<>();

    // Given an evolving graph GI = {Gti, Gti+1, . . . , Gtj },
    public SCC(List<Snapshot> snapshots) {

        // we invoke at each graph snapshot Gtk ∈ GI an algorithm, e.g.,
        // Tarjan’s algorithm [24], to identify the corresponding set of SCCs.
        for (Snapshot snapshot : snapshots) {
            TarjanSCC tarjan = new TarjanSCC(snapshot);
            tarjans.put(snapshot, tarjan);
            map.put(tarjan, snapshot.getTime());
        }
        size = snapshots.size();

        for (Snapshot snapshot : snapshots) {
            Set<Integer> vertices = new HashSet<>();
            Set<Edge> edges = new HashSet<>();

            TarjanSCC tarjanSCC = tarjans.get(snapshot);
            IntStream.range(0, tarjanSCC.count()).forEach(vertices::add);

            snapshot.getEdges()
                    .stream()
                    .map(edge ->
                            new Edge(
                                    tarjanSCC.id(edge.getVertex1()),
                                    tarjanSCC.id(edge.getVertex2())))
                    .collect(Collectors.toSet())
                    .forEach(edges::add);

            // combine it
            graphSnapshots.add(new Snapshot(snapshot.getTime(), vertices, edges));
        }
    }



    /**
     * P function defined
     * @param v vertex
     * @return component and time
     */
    public Map<Integer, Integer> Posting(Integer v) {
        // time -> componentId
        Map<Integer, Integer> result = new HashMap<>();

        int i = 0;
        for (TarjanSCC scc : map.keySet()) {
            result.put(map.get(scc), i + scc.id(v));
            i += scc.count();
        }
        return result;
    }

    public boolean TimeReach(Integer u, Integer v, IntervalSet iq)
    {
        if(iq.isEmpty()) return false;

        Map<Integer, Integer> mapU = this.Posting(u);
        Map<Integer, Integer> mapV = this.Posting(v);

        Integer startTime = iq.getStartTime();
        Integer endTime = iq.getEndTime();

        int[] stream = IntStream
                .rangeClosed(startTime, endTime)
                .filter(iq::covers).toArray();

        for (int i : stream) {
            int sc1 = mapU.get(i);
            int sc2 = mapV.get(i);
            if(sc1 == sc2) {
                return true;
            } else {
                Snapshot snapshot = graphSnapshots.get(i);

                if(!snapshot.getVertices().contains(sc1) ||
                !snapshot.getVertices().contains(sc2)) {
                    continue;
                }

                DirectedDFS dfs = new DirectedDFS(snapshot, sc1);
                if(dfs.marked(sc2)) {
                    return true;
                }
            }
        }

        return false;
    }

}
