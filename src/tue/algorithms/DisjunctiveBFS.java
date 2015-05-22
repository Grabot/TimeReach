package tue.algorithms;

import tue.data.IVersionGraph;
import tue.data.Interval;

/**
 * NOTE: fG = fancy G that UTF8 does not have
 *
 * a disjunctive historical reachability query u -> v returns true, ( -iq> )
 * if there exists a path from u to v in at least one graph snapshot G_t_m , t_k ≤ t_m ≤ t_l of fG[t_i,t_j].
 */
public class DisjunctiveBFS {
    private DisjunctiveBFS () {}
    public static boolean execute(IVersionGraph in, Integer u, Integer v, Interval iq) {
        return false; //TODO: implement
    }
}
