package com.antt.dsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;

/**
 * Created by BL on 3/15/2017.
 */
public class TreeEx {

    private static TreeNode buildTreePreIn(int[] preOrder, int[] inorder) {
        int preStart = 0;
        int preEnd = preOrder.length - 1;
        int inStart = 0;
        int inEnd = inorder.length - 1;
        return construct(preOrder, preStart, preEnd, inorder, inStart, inEnd);
    }

    private static TreeNode buildTreePostIn(int[] postOrder, int[] inorder) {
        int postStart = 0;
        int postEnd = postOrder.length - 1;
        int inStart = 0;
        int inEnd = inorder.length - 1;
        return constructPostorder(postOrder, postStart, postEnd, inorder, inStart, inEnd);
    }

    private static TreeNode construct(int[] preOrder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {

//        System.out.printf("pre[%d -> %d], inorder[%d -> %d]", preStart, preEnd, inStart, inEnd);
//        System.out.println();
        //stop condition
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int val = preOrder[preStart];
        TreeNode p = new TreeNode(val);
        //find k
        int k = 0;
        int len = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (val == inorder[i]) {
                k = i;
                len = i - inStart;
                break;
            }
        }

        p.left = construct(preOrder, preStart + 1, preStart + len, inorder, inStart, k - 1);
        p.right = construct(preOrder, preStart + len + 1, preEnd, inorder, k + 1, inEnd);

        return p;
    }

    // example input
    // in order:    4 2 5  1 6 7 3 8
    // post order:  4 5 2 6 7 8 3 1
    private static TreeNode constructPostorder(int[] postOrder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd) {
        // find root from post order
        // find left, right tree from inorder
        // construct left, right tree
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        int v = postOrder[postEnd];
        int k = 0;
        int len = 0;
        TreeNode node = new TreeNode(v);
        for (k = inStart; k <= inEnd; k++) {
            if (inorder[k] == v) {
                len = k - inStart;
            }
        }

        node.left = constructPostorder(postOrder, postStart, postStart + len - 1, inorder, inStart, inStart + len - 1);
        node.right= constructPostorder(postOrder, postStart + len, postEnd - 1, inorder, inStart + len + 1, inEnd);

        return node;
    }

    private static ArrayList<Integer> inorderRecursive(TreeNode root) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (root == null) {
            return ret;
        }

        ret.add(root.value);
        ret.addAll(inorderRecursive(root.left));
        ret.addAll(inorderRecursive(root.right));
        return ret;
    }

    public static boolean dspSum(TreeNode root, int sum) {
        Queue<TreeNode> q = new LinkedList<>();
//        System.out.println();
        if (root != null && root.left == null && root.right == null) {
            return root.value == sum;
        }
        boolean ret = false;
        if (root.left != null) {
            ret = dspSum(root.left, sum - root.value);
        }
        if (!ret && root.right != null)
            ret = dspSum(root.right, sum - root.value);
//        while (!q.isEmpty())
        return ret;
    }

    public static boolean bfs(TreeNode root, int num) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) return false;
        q.add(root);
        while (q.size() > 0) {
            TreeNode node = q.remove();
            System.out.printf("%d ", node.value);
            if (node.value == num) return true;
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        System.out.println();
        return false;
    }


    public static boolean dfs(TreeNode root, int num) {
        Stack<TreeNode> q = new Stack<>();
        if (root == null) return false;
        q.add(root);
        while (q.size() > 0) {
            TreeNode node = q.pop();
            System.out.printf("%d ", node.value);
            if (node.value == num) return true;
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
        }
        System.out.println();
        return false;
    }

    public static void printTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (q.size() > 0) {
            TreeNode n = q.poll();
            if (n != null) {
                System.out.printf("%d ", n.value);
                q.add(n.right);
                q.add(n.left);
            }
        }
        System.out.println();
    }

    // a tree is height balanced if it height(left) - height(right) in [-1,1]
    public static boolean isTreeHeightBalanced(TreeNode root) {
        if (root == null) return true;

        int hl = root.left == null ? 0 : height(root.left);
        int hr = root.right == null ? 0 : height(root.right);
        return Math.abs(hl - hr) <= 1 && isTreeHeightBalanced(root.left) && isTreeHeightBalanced(root.right);
    }

    private static int height(TreeNode root) {
        int height = 0;
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> hh = new LinkedList<>();
        if (root != null) {
            q.add(root);
            hh.add(1);
        }
        while (q.size() > 0) {
            TreeNode node = q.poll();
            int nodeH = hh.poll();
            if (node.left != null) {
                q.add(node.left);
                hh.add(nodeH + 1);
            }
            if (node.right != null) {
                q.add(node.right);
                hh.add(nodeH + 1);
            }
            height = Math.max(height, nodeH);
        }
        System.out.printf("Root %d, height = %d\n", root != null ? root.value : -1, height);
        return height;
    }

    private static void testBalancedTree() {
        TreeNode newroot = buildTreePreIn(new int[]{1, 2, 4, 5, 3, 7, 6, 8}, new int[]{4, 2, 5, 1, 6, 7, 3, 8});
        System.out.printf("true = %s\n", isTreeHeightBalanced(newroot));

        newroot = buildTreePreIn(new int[]{1, 2, 4, 5, 3, 7, 6}, new int[]{4, 2, 5, 1, 6, 7, 3});
        System.out.printf("false = %s\n", isTreeHeightBalanced(newroot));

        newroot = buildTreePreIn(new int[]{3, 7, 6}, new int[]{6, 7, 3});
        System.out.printf("false = %s\n", isTreeHeightBalanced(newroot));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);
        root.right.left.left = new TreeNode(6);
        ArrayList<Integer> inorderRet = inorderRecursive(root);
        for (Integer i : inorderRet) {
            System.out.printf("%d ", i);
        }
        System.out.println();

        TreeNode newroot = buildTreePreIn(new int[]{1, 2, 4, 5, 3, 7, 6, 8}, new int[]{4, 2, 5, 1, 6, 7, 3, 8});
        printTree(newroot);

        System.out.println("### Construct tree from inorder and post order");
        newroot = buildTreePostIn(new int[]{4,5,2,6,7,8,3,1}, new int[]{4, 2, 5, 1, 6, 7, 3, 8});
        printTree(newroot);

        System.out.printf("sum(17): false %s\n", dspSum(root, 18));
        System.out.printf("sum(7): true %s\n", dspSum(root, 7));
        testBFS_DFS(root);
        testBalancedTree();
    }

    private static void testBFS_DFS(TreeNode root) {
        System.out.println("bfs 4 " + bfs(root, 4));
        System.out.println("bfs 8 " + bfs(root, 8));
        System.out.println("bfs 5 " + bfs(root, 5));
        System.out.println("bfs 6 " + bfs(root, 6));
        System.out.println("bfs 7 " + bfs(root, 7));
        System.out.println("bfs 1 " + bfs(root, 1));
        System.out.println("bfs 10 " + bfs(root, 10));
        System.out.println("dfs 4 " + dfs(root, 4));
        System.out.println("dfs 8 " + dfs(root, 8));
        System.out.println("dfs 5 " + dfs(root, 5));
        System.out.println("dfs 6 " + dfs(root, 6));
        System.out.println("dfs 7 " + dfs(root, 7));
        System.out.println("dfs 1 " + dfs(root, 1));
        System.out.println("dfs 10 " + dfs(root, 10));
    }
}

