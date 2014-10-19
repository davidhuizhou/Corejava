package com.dzhou.corejava.leetcode;

/**
 * Created by huizhou on 10/18/14.
 */

import java.util.*;

public class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
