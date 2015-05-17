package tue.algorithms;

import tue.data.Interval;
import tue.data.VersionGraph;
import tue.data.Vertex;

/**
 * NOTE: fG = fancy G that UTF8 does not have
 *
 * a conjunctive historical reachability query u -> v returns true, ( -iq> )
 * if there exists a path from u to v in all graph snapshots G_t_m , t_k ≤ t_m ≤ t_l of fG[t_i,t_j].
 */
public class ConjuctiveBFS {
    private ConjuctiveBFS () {}
    static boolean execute(VersionGraph in, Vertex u, Vertex v, Interval iq) {
        return false; //TODO: implement
    }
}
