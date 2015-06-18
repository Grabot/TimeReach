package tue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import tue.algorithms.ConjuctiveBFS;
import tue.algorithms.TransitiveClosure;
import tue.data.*;

public class Main {

    private void run() {
        /*
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
        */

        /*
    	int times = 0;
    	for( int x = 0; x <= 3; x++ )
    	{
    		for( int y = 0; y <= 3; y++ )
    		{
		    	//loop over all 7 nodes twice for all possible combinations
		    	for( int i = 1; i <= 7; i++ )
		    	{
		    		for ( int j = 1; j <= 7; j++ )
		    		{
		    			if( y >= x )
		    			{
			    			times ++;
					        IntervalSet set = new IntervalSet();
					        set.addInterval(new Interval(x, y));
				
					        int node1 = i;
					        int node2 = j;
					
					        node1--;
					        node2--;
					        
					        RealVersionGraph graph = new RealVersionGraph(getDemo());
					        Map<Edge, IntervalSet> map = TransitiveClosure.execute(graph);
					        IntervalSet resultSet = map.get(new Edge(node1, node2));
					        boolean truth = resultSet.covers(set);
					        //System.out.println("Truth: " + truth);
					
					        boolean test = ConjuctiveBFS.execute(graph, node1, node2, set);
					        //System.out.println("In your face Sander: " + test);
					        
					        //SCC graph2 = new SCC(getDemo());
					        //boolean test2 = graph2.TimeReach(node1, node2, set);
					        //System.out.println("In your face Nanne: " + test2);
					        
					        //if( !truth == test || !test == test2 || !truth == test2 )
					        //{
					        //	System.out.println("not good " + " at intervalset (" + x + ", " + y + ")" + " for node " + i + " to " + j );
					        }
		    			}
		    		}
		    	}
    		}
    	}
    	System.out.println("ran " + times + " times" );
    	*/

        pbTest();
    }

    private void pbTest()
    {
        String file_fbdata = "./data/fb_days_version";

        VersionGraphFactory.create(file_fbdata);
    }

    private List<Snapshot> getDemo() {
        HashSet<Integer> vertices = new HashSet<Integer>();
        HashSet<Edge> edges = new HashSet<Edge>();

        for (int i = 0; i < 7; i++) {
            vertices.add(i);
        }

        List<Snapshot> evolutionGraph = new ArrayList<Snapshot>();

        edges.clear();
        //evolving graph, snapshot G_t0
        edges.add(new Edge(0, 3)); //u1 to u4
        edges.add(new Edge(3, 0)); //u4 to u1
        edges.add(new Edge(0, 2)); //u1 to u3
        edges.add(new Edge(1, 2)); //u2 to u3
        edges.add(new Edge(2, 5)); //u3 to u6
        edges.add(new Edge(6, 3)); //u7 to u4
        edges.add(new Edge(6, 5)); //u7 to u6
        edges.add(new Edge(4, 5)); //u5 to u6
        edges.add(new Edge(5, 4)); //u6 to u5

        evolutionGraph.add(new Snapshot(0, vertices, edges));

        //clear edges for new snapshot
        edges = new HashSet<Edge>();
        //evolving graph, snapshot G_t1
        edges.add(new Edge(0, 1)); //u1 to u2
        edges.add(new Edge(0, 2)); //u1 to u3
        edges.add(new Edge(0, 3)); //u1 to u4
        edges.add(new Edge(3, 0)); //u4 to u1
        edges.add(new Edge(1, 2)); //u2 to u3
        edges.add(new Edge(2, 5)); //u3 to u6
        edges.add(new Edge(2, 6)); //u3 to u7
        edges.add(new Edge(6, 5)); //u7 to u6
        edges.add(new Edge(5, 1)); //u6 to u2
        edges.add(new Edge(4, 5)); //u5 to u6
        edges.add(new Edge(5, 4)); //u6 to u5

        evolutionGraph.add(new Snapshot(1, vertices, edges));

        //clear edges for new snapshot
        edges = new HashSet<>();
        //evolving graph, snapshot G_t2
        edges.add(new Edge(0, 1));
        edges.add(new Edge(1, 0));
        edges.add(new Edge(0, 3));
        edges.add(new Edge(3, 0));
        edges.add(new Edge(3, 2));
        edges.add(new Edge(2, 6));
        edges.add(new Edge(6, 3));
        edges.add(new Edge(2, 5));
        edges.add(new Edge(4, 5));
        edges.add(new Edge(5, 4));

        evolutionGraph.add(new Snapshot(2, vertices, edges));

        //clear edges for new snapshot
        edges = new HashSet<Edge>();
        //evolving graph, snapshot G_t3
        edges.add(new Edge(0, 1));
        edges.add(new Edge(1, 0));
        edges.add(new Edge(0, 3));
        edges.add(new Edge(3, 2));
        edges.add(new Edge(2, 6));
        edges.add(new Edge(6, 3));
        edges.add(new Edge(2, 5));
        edges.add(new Edge(6, 5));
        edges.add(new Edge(4, 1));
        edges.add(new Edge(4, 5));
        edges.add(new Edge(5, 4));

        evolutionGraph.add(new Snapshot(3, vertices, edges));
	
        return evolutionGraph;
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
