package tue.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Luuk on 18-6-2015.
 */
public class VersionGraphFactory {

    public static void create(String filename)
    {
        // Read information from file in memory
        List<TimeEdge> timeEdges = readDataFromVersionFile(filename);

        // Find first and lasttime index
        int startTime = -1;
        int endTime = -1;
        for (TimeEdge timeEdge : timeEdges) {
            if (startTime == -1 || timeEdge.startTime < startTime) {
                startTime = timeEdge.startTime;
            }
            if (endTime == -1 || timeEdge.endTime > endTime) {
                endTime = timeEdge.endTime;
            }
        }

        // Create all snapshots
        SCC scc = new SCC();
        HashSet<Integer> vertices;
        HashSet<Edge> edges;
        for (int i = startTime; i <= endTime; i++ ) {
            vertices = new HashSet<Integer>();
            edges = new HashSet<Edge>();

            for (TimeEdge timeEdge : timeEdges) {

                // When edge time is within snapshot
                if (timeEdge.startTime <= i && timeEdge.endTime <= i) {
                    vertices.add(timeEdge.v1);
                    vertices.add(timeEdge.v2);
                    edges.add(new Edge(timeEdge.v1, timeEdge.v2));
                }
            }

            scc.addSnapShot(new Snapshot(i, vertices, edges));
            //snapshots.add(new Snapshot(i, vertices, edges));
            System.out.printf("At time: %d; max time: %d", i, endTime);
            System.out.println();
        }

        //System.out.println(snapshots.get(0).toString());
    }

    private static List<TimeEdge> readDataFromVersionFile(String filename) {
        String line;
        List<TimeEdge> timeEdges = new ArrayList<TimeEdge>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            // Skip header by discarding first line
            reader.readLine();

            while ((line = reader.readLine()) != null ) {

                // Separate all tab fields into string array
                String[] lineVariables = line.split("\\t");

                // Stop if line does not contain three elements
                if (lineVariables.length < 3) {
                    continue;
                }

                // Split timespan
                String[] timespan = lineVariables[2].split(",");

                // Read data into objects
                int v1 = Integer.parseInt(lineVariables[0]);
                int v2 = Integer.parseInt(lineVariables[1]);
                int startTime = Integer.parseInt(timespan[0]);
                int endTime = Integer.parseInt(timespan[0]);

                timeEdges.add(new TimeEdge(v1, v2, startTime, endTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return timeEdges;
    }
}

class TimeEdge {
    public int v1;
    public int v2;
    public int startTime;
    public int endTime;

    public TimeEdge(int v1, int v2, int startTime, int endTime) {
        this.v1 = v1;
        this.v2 = v2;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
