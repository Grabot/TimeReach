package tue.algorithms;

import java.util.Objects;
import java.util.Set;
import java.util.Stack;

import tue.data.Edge;
import tue.data.IVersionGraph;
import tue.data.IntervalSet;

/**
 * NOTE: fG = fancy G that UTF8 does not have
 *
 * a conjunctive historical reachability query u -> v returns true, ( -iq> )
 * if there exists a path from u to v in all graph snapshots G_t_m , t_k ≤ t_m ≤ t_l of fG[t_i,t_j].
 */
public class ConjuctiveBFS {
    
	public ConjuctiveBFS () 
    {
    	
    }
    
    public static boolean execute(IVersionGraph in, Integer u, Integer v, IntervalSet Iq) {
    	/**
    	 * Algorithm 3 Conjunctive-BFS(VG_I , u, v, {I_Q})
    	 * Input: Version graph VG_I , nodes u, v, interval I_Q subset
    	 * Output: True if v is reachable from u in all time instants in I_Q and false otherwise
    	1: create a queue N, create a queue INT
    	2: enqueue u onto N, enqueue I_Q onto INT
    	3: while N =/= emptyset; do
    	4: 		n <- N.dequeue()
    	5: 		i <- INT.dequeue()
    	6: 		for all w s.t. (n, w) in VG_I and {I_Q} (x) L_e((n,w)) =/= emptyset do
    	7: 			I' = {I_Q} (x) L_e(n,w)
    	8: 			if w == v then
    	9: 				R = R (+) I'
    	10: 			if R ]=	I' then
    	11: 				Return(true)
    	12: 			end if
    	13: 		continue
    	14: 		end if
    	15: 		if IN(w) ]/= I' then
    	16: 			IN(w) = IN(w) (+) I'
    	17: 			enqueue w onto N
    	18: 			enqueue I' onto INT
    	19: 		end if
    	20: 	end for
    	21: end while
    	22: Return(false)
    	*/
    	Stack<Integer> N = new Stack<>();
    	Stack<IntervalSet> INT = new Stack<>();
    	N.push(u);
    	INT.push(Iq);
		IntervalSet R = IntervalSet.empty();
		IntervalSet IN = IntervalSet.empty();
    	while( !N.isEmpty() )
    	{
			Integer n = N.pop();
    		IntervalSet i = INT.pop();
    		Set<Integer> neighbours = in.neighbours(n);
    		for(Integer w : neighbours) {
    			Edge e = new Edge(n, w);
				IntervalSet Iprime = i.cross(in.getIntervalSet(e));
				if(!Iprime.isEmpty())
				{
					if(Objects.equals(w, v))
					{
						R = R.plus(Iprime);
						if( i.covers(R))
						{
							return true;
						}
						continue;
					}
					if( !Iprime.covers(IN))
					{
						IN = IN.plus(Iprime);
						N.push(w);
						INT.push(Iprime);
					}
				}
    		}
    	}
        return false; 
    }
}
