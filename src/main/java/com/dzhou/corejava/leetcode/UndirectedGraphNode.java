package com.dzhou.corejava.leetcode;

/**
 * Created by huizhou on 10/18/14.
 */

import java.util.*;

public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
