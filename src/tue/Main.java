package tue;

import tue.algorithms.TransitiveClosure;
import tue.data.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
//        RealVersionGraph in = getDemo();

        FakeVersionGraph in = new FakeVersionGraph();

        try {
            Scanner sc = new Scanner(new File("data/fb_days_version"));
            sc.nextLine();
            int i = 0;
            while(sc.hasNextLine() && sc.hasNextBigDecimal()) {
                Vertex v1 = new Vertex(sc.nextBigInteger().intValue());
                Vertex v2 = new Vertex(sc.nextBigInteger().intValue());
                String sp = sc.next("[0-9]*,[0-9]*");
                String[] split = sp.split(",");

                Integer time1 = new Integer(split[0]);
                Integer time2 = new Integer(split[1]);
                Interval interval = new Interval(time1, time2);

                in.addEdge(new Edge(v1, v2), interval);
                i++;
                if(i % 10000 == 0) {
                    System.out.print(i);
                    System.out.println("");
                }
            }
            System.out.println("Data loading done");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<Edge, IntervalSet> execute = TransitiveClosure.execute(in);
    }



    private static RealVersionGraph getDemo() {
        HashSet<Vertex> vertices = new HashSet<Vertex>();
        HashSet<Edge> edges = new HashSet<Edge>();

        Vertex[] vx = new Vertex[100];
        for (int i = 0; i < vx.length; i++) { vx[i] = new Vertex(i+1); }

        List<Snapshot> evolutionGraph = new ArrayList<Snapshot>();

        vertices.add(vx[0]);
        vertices.add(vx[1]);
        vertices.add(vx[2]);
        vertices.add(vx[3]);
        Edge e1 = new Edge(vx[0], vx[1]);
        edges.add(e1);
        edges.add(new Edge(vx[1], vx[3]));

        evolutionGraph.add(new Snapshot(1, vertices, edges));

        edges.remove(e1);
        edges.add(new Edge(vx[2], vx[3]));

        evolutionGraph.add(new Snapshot(2, vertices, edges));

        return new RealVersionGraph(evolutionGraph);
    }
}
