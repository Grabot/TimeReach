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
                Integer v1 = sc.nextBigInteger().intValue();
                Integer v2 = sc.nextBigInteger().intValue();
                String sp = sc.next("[0-9]*,[0-9]*");
                String[] split = sp.split(",");

                Integer time1 = new Integer(split[0]);
                Integer time2 = new Integer(split[1]);
                Interval interval = new Interval(time1, time2);

                in.addEdge(new Edge(v1, v2), interval);
                i++;
                if(i % 10000 == 0) {
                    System.out.println(i);
                }
            }
            System.out.println("Data loading done");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        getDemo(); 
        System.out.println("In your face Luuk");
    }



    private static RealVersionGraph getDemo() {
        HashSet<Integer> vertices = new HashSet<Integer>();
        HashSet<Edge> edges = new HashSet<Edge>();

        Integer[] vx = new Integer[100];
        for( int i = 0; i < 8; i++ )
        {
            vx[i] = i;
            vertices.add(vx[i]);
        }
        
        System.out.println("size of vertices: " + vertices.size());
        for (int i = 0; i < vx.length; i++) { vx[i] = new Integer(i+1); }

        List<Snapshot> evolutionGraph = new ArrayList<Snapshot>();
        
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
