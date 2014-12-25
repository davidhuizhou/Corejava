package com.dzhou.corejava.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by davidzhou on 12/25/14.
 */
public class JavaTreeProblems {
    /**
     * Binary Tree Inorder Traversal -- LeetCode
     * https://oj.leetcode.com/problems/binary-tree-inorder-traversal/
     * Given a binary tree, return the inorder traversal of its nodes' values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    /**
     * Binary Tree Preorder Traversal -- LeetCode
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preorder(root, res);
        return res;
    }

    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                res.add(root.val);
                if (root.right != null)
                    stack.push(root.right);

                root = root.left;
            } else {
                root = stack.pop();
            }
        }
        return res;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) {
                    res.add(cur.val);
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    /**
     * Binary Tree Postorder Traversal -- LeetCode
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        postorder(root, res);
        return res;
    }

    private void postorder(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }

    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode peekNode = stack.peek();
                if (peekNode.right != null && pre != peekNode.right) {
                    root = peekNode.right;
                } else {
                    stack.pop();
                    res.add(peekNode.val);
                    pre = peekNode;
                }
            }
        }
        return res;
    }

    public ArrayList<Integer> postorderTraversal2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode cur = dummy;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    reverse(cur.left, pre);
                    TreeNode temp = pre;
                    while (temp != cur.left) {
                        res.add(temp.val);
                        temp = temp.right;
                    }
                    res.add(temp.val);
                    reverse(pre, cur.left);
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    void reverse(TreeNode start, TreeNode end) {
        if (start == end)
            return;
        TreeNode pre = start;
        TreeNode cur = start.right;
        TreeNode next;
        while (pre != end) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
    }

}
