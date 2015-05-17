package tue.algorithms;

import tue.data.*;

import java.util.*;

public class TransitiveClosure {
    private TransitiveClosure () {}
    static void execute(VersionGraph in) {
        List<Vertex> vex = in.getVertices();
        Set<Edge> edges = in.getEdges();
        List<IntervalSet> cl = new LinkedList<IntervalSet>();

        for (Vertex u : vex) {
            for (Vertex v : vex) {
                Edge edge = new Edge(u, v);
                if(edges.contains(edge)) {
                    cl.add(in.l(edge));
                }
            }
        }
        for (int w = 0; w < vex.size(); w++) {
            for (Vertex u : vex) {
                for (Vertex v : vex) {


                }
            }
        }
    }
}
