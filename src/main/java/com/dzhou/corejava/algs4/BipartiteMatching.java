package com.dzhou.corejava.algs4;

import com.dzhou.lib.algorithms.StdOut;
import com.dzhou.lib.algorithms.StdRandom;

/*************************************************************************
 *  Compilation:  javac com.dzhou.corejava.algs4.BipartiteMatching.java
 *  Execution:    java com.dzhou.corejava.algs4.BipartiteMatching N E
 *  Dependencies: com.dzhou.corejava.algs4.FordFulkerson.java com.dzhou.corejava.algs4.FlowNetwork.java com.dzhou.corejava.algs4.FlowEdge.java
 *
 *  Find a maximum matching in a bipartite graph. Solve by reducing
 *  to maximum flow.
 *
 *  The order of growth of the running time in the worst case is E V
 *  because each augmentation increases the cardinality of the matching
 *  by one.
 *
 *  The Hopcroft-Karp algorithm improves this to E V^1/2 by finding
 *  a maximal set of shortest augmenting paths in each phase.
 *
 *********************************************************************/

import com.dzhou.lib.algorithms.*;

public class BipartiteMatching {

    public static void main(String[] args) {

        // read in bipartite network with 2N vertices and E edges
        // we assume the vertices on one side of the bipartition
        // are named 0 to N-1 and on the other side are N to 2N-1.
        int N = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int s = 2*N, t = 2*N + 1;
        FlowNetwork G = new FlowNetwork(2*N + 2);
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(N);
            int w = StdRandom.uniform(N) + N;
            G.addEdge(new FlowEdge(v, w, Double.POSITIVE_INFINITY));
            StdOut.println(v + "-" + w);
        }
        for (int i = 0; i < N; i++) {
            G.addEdge(new FlowEdge(s,     i, 1.0));
            G.addEdge(new FlowEdge(i + N, t, 1.0));
        }


        // compute maximum flow and minimum cut
        FordFulkerson maxflow = new FordFulkerson(G, s, t);
        StdOut.println();
        StdOut.println("Size of maximum matching = " + (int) maxflow.value());
        for (int v = 0; v < N; v++) {
            for (FlowEdge e : G.adj(v)) {
                if (e.from() == v && e.flow() > 0)
                    StdOut.println(e.from() + "-" + e.to());
            }
        }
    }

}
