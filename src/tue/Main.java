package tue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import tue.algorithms.ConjuctiveBFS;
import tue.algorithms.TransitiveClosure;
import tue.data.Edge;
import tue.data.FakeVersionGraph;
import tue.data.Interval;
import tue.data.IntervalSet;
import tue.data.RealVersionGraph;
import tue.data.Snapshot;

public class Main {

	private void run()
	{
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

        RealVersionGraph graph = new RealVersionGraph();
        graph = getDemo();
        
        //Map<Edge, IntervalSet> mapTest = TransitiveClosure.execute( graph );

        IntervalSet set = new IntervalSet();
        set.addInterval(new Interval(0, 3));
        ConjuctiveBFS bfs = new ConjuctiveBFS();
        boolean test = bfs.execute(graph, 0, 4, set);
        System.out.println( "In your face Luuk: " + test );
	}

    private RealVersionGraph getDemo() {
        HashSet<Integer> vertices = new HashSet<Integer>();
        HashSet<Edge> edges = new HashSet<Edge>();

        Integer[] vx = new Integer[100];
        for( int i = 0; i < 7; i++ )
        {
            vx[i] = i;
            vertices.add(vx[i]);
        }
        
        List<Snapshot> evolutionGraph = new ArrayList<Snapshot>();
        
        //evolving graph, snapshot G_t0
        edges.add(new Edge( vx[0], vx[3] ));
        edges.add(new Edge( vx[3], vx[0] ));
        edges.add(new Edge( vx[0], vx[2] ));
        edges.add(new Edge( vx[1], vx[2] ));
        edges.add(new Edge( vx[2], vx[5] ));
        edges.add(new Edge( vx[6], vx[3] ));
        edges.add(new Edge( vx[6], vx[5] ));
        edges.add(new Edge( vx[4], vx[5] ));
        edges.add(new Edge( vx[5], vx[4] ));
        
        evolutionGraph.add(new Snapshot(1, vertices, edges));

        //clear edges for new snapshot
        edges.clear();
        //evolving graph, snapshot G_t1
        edges.add(new Edge( vx[0], vx[1] ));
        edges.add(new Edge( vx[0], vx[2] ));
        edges.add(new Edge( vx[0], vx[3] ));
        edges.add(new Edge( vx[3], vx[0] ));
        edges.add(new Edge( vx[1], vx[2] ));
        edges.add(new Edge( vx[2], vx[5] ));
        edges.add(new Edge( vx[2], vx[6] ));
        edges.add(new Edge( vx[6], vx[5] ));
        edges.add(new Edge( vx[5], vx[1] ));
        edges.add(new Edge( vx[4], vx[5] ));
        edges.add(new Edge( vx[5], vx[4] ));

        evolutionGraph.add(new Snapshot(2, vertices, edges));
        
        //clear edges for new snapshot
        edges.clear();
        //evolving graph, snapshot G_t2
        edges.add(new Edge( vx[0], vx[1] ));
        edges.add(new Edge( vx[1], vx[0] ));
        edges.add(new Edge( vx[0], vx[3] ));
        edges.add(new Edge( vx[3], vx[0] ));
        edges.add(new Edge( vx[3], vx[2] ));
        edges.add(new Edge( vx[2], vx[6] ));
        edges.add(new Edge( vx[6], vx[3] ));
        edges.add(new Edge( vx[2], vx[5] ));
        edges.add(new Edge( vx[4], vx[5] ));
        edges.add(new Edge( vx[5], vx[4] ));

        evolutionGraph.add(new Snapshot(3, vertices, edges));
        
        //clear edges for new snapshot
        edges.clear();
        //evolving graph, snapshot G_t3
        edges.add(new Edge( vx[0], vx[1] ));
        edges.add(new Edge( vx[1], vx[0] ));
        edges.add(new Edge( vx[0], vx[3] ));
        edges.add(new Edge( vx[3], vx[2] ));
        edges.add(new Edge( vx[2], vx[6] ));
        edges.add(new Edge( vx[6], vx[3] ));
        edges.add(new Edge( vx[2], vx[5] ));
        edges.add(new Edge( vx[6], vx[5] )); 
        edges.add(new Edge( vx[4], vx[1] ));
        edges.add(new Edge( vx[4], vx[5] ));
        edges.add(new Edge( vx[5], vx[4] ));

        evolutionGraph.add(new Snapshot(4, vertices, edges));

        return new RealVersionGraph(evolutionGraph);
    }
    

    public static void main(String[] args) {
    	Main main = new Main();
    	main.run();
    }
}
