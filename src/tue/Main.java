package tue;

import tue.algorithms.TransitiveClosure;
import tue.data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // small demo
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Edge> edges = new ArrayList<Edge>();

        Vertex[] vx = new Vertex[100];
        for (int i = 0; i < vx.length; i++) { vx[i] = new Vertex(i+1); }

        List<Snapshot> eg = new ArrayList<Snapshot>();

        vertices.add(vx[0]);
        vertices.add(vx[1]);
        vertices.add(vx[2]);
        vertices.add(vx[3]);
        edges.add(new Edge(vx[0], vx[1]));
        edges.add(new Edge(vx[1], vx[3]));

        eg.add(new Snapshot(new IntTime(1), vertices, edges));

        edges.remove(0);
        edges.add(new Edge(vx[2], vx[3]));

        eg.add(new Snapshot(new IntTime(2), vertices, edges));

        VersionGraph in = new VersionGraph(eg);
        Map<Edge, IntervalSet> execute = TransitiveClosure.execute(in);
    }

    private static class IntTime implements Time {
        private Integer i;

        private IntTime(int i) {
            this.i = i;
        }

        @Override
        public int compareTo(Time o) {
            IntTime o2 = (IntTime) o;

            return i.compareTo(o2.i);
        }
    }
}
