package tue.data;

import tue.algorithms.stanford.TarjanSCC;

import java.util.*;
import java.util.stream.Collectors;

public class SCC {
    Map<TarjanSCC, Integer> map = new LinkedHashMap<>();
    Map<Snapshot, TarjanSCC> tarjans = new HashMap<>();
    int size;

    // SCC Graph Snaphots
    List<Snapshot> graphSnapshots = new ArrayList<>();

    public SCC(List<Snapshot> snapshots) {
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

            for (int i = 0; i < tarjanSCC.count(); i++) {
                vertices.add(i);
            }

            edges.addAll(snapshot.getEdges().stream().map(edge -> new Edge(tarjanSCC.id(edge.getVertex1()), tarjanSCC.id(edge.getVertex2()))).collect(Collectors.toSet()));

            graphSnapshots.add(new Snapshot(snapshot.getTime(), vertices, edges));
        }
    }



    /**
     * P function defined
     * @param v vertex
     * @return component and time
     */
    public Map<Integer, Integer> Posting(Integer v) {
        Map<Integer, Integer> result = new LinkedHashMap<>();
        int i = 0;
        for (TarjanSCC scc : map.keySet()) {
            result.put(i + scc.id(v), map.get(scc));
            i += scc.count();
        }
        return result;
    }

}
