package tue.algorithms;

import tue.data.*;

import java.util.*;

/**
 * The time complexity for Algorithm 1 is O(|V_I|^3*T) in the
 * worst case and requires storage in the order of |V_I|^2.
 */
public class TransitiveClosure {
    private TransitiveClosure () {}
    public static Map<Edge, IntervalSet> execute(VersionGraph in) {
        Set<Vertex> vex = in.getVertices();
        Set<Edge> edges = in.getEdges();
        Map<Edge, IntervalSet> cl = new HashMap<Edge, IntervalSet>();

        for (Vertex u : vex) {
            for (Vertex v : vex) {
                Edge edge = new Edge(u, v);
                if(edges.contains(edge)) {
                    cl.put(edge, in.l(edge));
                } else {
                    cl.put(edge, IntervalSet.empty());
                }
            }
        }
        for (Vertex w : vex) {
            for (Vertex u : vex) {
                for (Vertex v : vex) {
                    Edge uv = new Edge(u, v);
                    Edge uw = new Edge(u, w);
                    Edge wv = new Edge(w, v);

                    IntervalSet cl_uv = cl.get(uv);
                    IntervalSet cl_uw = cl.get(uw);
                    IntervalSet cl_wv = cl.get(wv);
                    // cl(u,v) = cl(u,v) + (cl(u,w) x cl(w,v))
                    cl.put(uv, cl_uv.plus(cl_uw.cross(cl_wv)));
                }
            }
        }

        return cl;
    }
}
