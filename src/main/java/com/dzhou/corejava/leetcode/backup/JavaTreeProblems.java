package com.dzhou.corejava.leetcode.backup;

import java.util.*;

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

    /**
     * Maximum Depth of Binary Tree -- LeetCode
     * https://oj.leetcode.com/problems/maximum-depth-of-binary-tree/
     * Given a binary tree, find its maximum depth.
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null)
            return 0;
        int level = 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int curNum = 1; //num of nodes left in current level
        int nextNum = 0; //num of nodes in next level
        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            curNum--;
            if (n.left != null) {
                queue.add(n.left);
                nextNum++;
            }
            if (n.right != null) {
                queue.add(n.right);
                nextNum++;
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                level++;
            }
        }
        return level;
    }

    /**
     * Minimum Depth of Binary Tree -- LeetCode
     * https://oj.leetcode.com/problems/minimum-depth-of-binary-tree/
     * Given a binary tree, find its minimum depth.
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     */
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null)
            return minDepth(root.right) + 1;
        if (root.right == null)
            return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public int minDepth2(TreeNode root) {
        if (root == null)
            return 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int curNum = 0;
        int lastNum = 1;
        int level = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            //Return once reach a leaf
            if (cur.left == null && cur.right == null)
                return level;
            lastNum--;
            if (cur.left != null) {
                queue.offer(cur.left);
                curNum++;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                curNum++;
            }
            if (lastNum == 0) {
                lastNum = curNum;
                curNum = 0;
                level++;
            }
        }
        return 0;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int parentsNum = 1;
        int childrenNum = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();

        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            parentsNum--;
            list.add(n.val);

            if (n.left != null) {
                queue.add(n.left);
                childrenNum++;
            }

            if (n.right != null) {
                queue.add(n.right);
                childrenNum++;

            }

            if (parentsNum == 0) {
                res.add(list);
                parentsNum = childrenNum;
                childrenNum = 0;
                list = new ArrayList<Integer>();

            }

        }
        Collections.reverse(res);
        return res;

    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.add(root);
        List<Integer> list = new ArrayList<Integer>();
        list.add(root.val);
        res.add(list);

        int level = 1;

        while (!stack.isEmpty()) {
            LinkedList<TreeNode> newStack = new LinkedList<TreeNode>();
            list = new ArrayList<Integer>();

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (level % 2 == 0) {
                    if (node.left != null) {
                        newStack.push(node.left);
                        list.add(node.left.val);
                    }
                    if (node.right != null) {
                        newStack.push(node.right);
                        list.add(node.right.val);
                    }
                } else {
                    if (node.right != null) {
                        newStack.push(node.right);
                        list.add(node.right.val);
                    }
                    if (node.left != null) {
                        newStack.push(node.left);
                        list.add(node.left.val);

                    }

                }

            }
            level++;
            if(!list.isEmpty())
                res.add(list);

            stack = newStack;

        }

        return res;
    }

}





