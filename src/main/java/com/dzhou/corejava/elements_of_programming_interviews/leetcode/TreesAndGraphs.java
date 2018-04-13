package com.dzhou.corejava.elements_of_programming_interviews.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * LeetCode - Trees and Graphs.
 * 
 * @author huizhou
 *
 */
public class TreesAndGraphs {

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private static class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
      this.label = x;
      neighbors = new ArrayList<UndirectedGraphNode>();
    }

  }

  /**
   * Same Tree - https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/306/
   */
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null) {
      return q == null;
    }

    if (q == null) {
      return p == null;
    }

    return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

  }

  /**
   * Validate Binary Tree -
   * https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/266/
   */
  public boolean isValidBST(TreeNode root) {
    return isValidBST(root, null, null);
  }

  public boolean isValidBST(TreeNode p, Integer min, Integer max) {
    if (p == null)
      return true;

    if (min != null && p.val <= min)
      return false;
    if (max != null && p.val >= max)
      return false;

    return isValidBST(p.left, min, p.val) && isValidBST(p.right, p.val, max);
  }



  /**
   * Binary Tree Paths -
   * https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/280/
   */
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> paths = new ArrayList<>();

    if (root == null) {
      return paths;
    }

    Queue<Path> queue = new LinkedList<>();
    queue.add(new Path("", root));

    while (!queue.isEmpty()) {
      Path p = queue.poll();
      TreeNode n = p.node;

      if (n.left == null && n.right == null) {
        paths.add(p.getPath());
        continue;
      }

      if (n.left != null) {
        queue.add(new Path(p.getPath(), n.left));
      }
      if (n.right != null) {
        queue.add(new Path(p.getPath(), n.right));
      }
    }

    return paths;

  }

  private static class Path {
    private String p;
    private TreeNode node;

    Path(String p, TreeNode node) {
      this.p = p;
      this.node = node;
    }

    String getPath() {
      return p.isEmpty() ? "" + node.val : p + "->" + node.val;
    }
  }



  /**
   * Clone Graph - https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/277/
   */
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) {
      return null;
    }

    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    Queue<UndirectedGraphNode> queue = new LinkedList<>();
    queue.add(node);
    map.put(node, new UndirectedGraphNode(node.label));

    while (!queue.isEmpty()) {
      UndirectedGraphNode n = queue.poll();

      for (UndirectedGraphNode e : n.neighbors) {
        // Copy vertex e if it has not copied before, and, put on the queue of the copy.
        if (!map.containsKey(e)) {
          map.put(e, new UndirectedGraphNode(e.label));
          queue.add(e);
        }
        // Copy edge.
        map.get(n).neighbors.add(map.get(e));
      }
    }

    return map.get(node);
  }

}
