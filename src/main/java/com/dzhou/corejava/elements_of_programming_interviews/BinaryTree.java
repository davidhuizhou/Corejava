package com.dzhou.corejava.elements_of_programming_interviews;


public class BinaryTree {


  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private static class HeightAndDiameter {
    private int diameter;
    private int height;

    public HeightAndDiameter(int height, int diameter) {
      this.height = height;
      this.diameter = diameter;

    }

  }

  private static HeightAndDiameter computeHeightAndDiameter(TreeNode root) {
    int diameter = Integer.MIN_VALUE;
    int[] height = {0, 0};

    if (root.left != null) {
      HeightAndDiameter left = computeHeightAndDiameter(root.left);
      if (left.height + 1 > height[0]) {
        height[1] = height[0];
        height[0] = left.height + 1;
      } else if (left.height + 1 > height[1]) {
        height[1] = left.height + 1;
      }
      diameter = Math.max(diameter, left.diameter);
    }

    if (root.right != null) {
      HeightAndDiameter right = computeHeightAndDiameter(root.right);
      if (right.height + 1 > height[0]) {
        height[1] = height[0];
        height[0] = right.height + 1;
      } else if (right.height + 1 > height[1]) {
        height[1] = right.height + 1;
      }
      diameter = Math.max(diameter, right.diameter);
    }

    return new HeightAndDiameter(height[0], Math.max(diameter, height[0] + height[1]));


  }


  private static class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;
  }

  private static class BalanceStatusWithHeight {
    public boolean balanced;
    public int height;

    public BalanceStatusWithHeight(boolean balanced, int height) {
      this.balanced = balanced;
      this.height = height;
    }

  }

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return checkBalanced(tree).balanced;
  }

  private static BalanceStatusWithHeight checkBalanced(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return new BalanceStatusWithHeight(true, -1);
    }

    BalanceStatusWithHeight leftResult = checkBalanced(tree.left);
    if (!leftResult.balanced) {
      return leftResult; // Left subtree is not balanced
    }

    BalanceStatusWithHeight rightResult = checkBalanced(tree.right);
    if (!rightResult.balanced) {
      return rightResult; // Right subtree is not balanced
    }

    boolean isBalanced = Math.abs(leftResult.height = rightResult.height) <= 1;
    int height = Math.max(leftResult.height, rightResult.height) + 1;
    return new BalanceStatusWithHeight(isBalanced, height);


  }


}
