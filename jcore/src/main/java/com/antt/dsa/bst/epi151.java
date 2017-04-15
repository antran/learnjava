package com.antt.dsa.bst;

import com.antt.dsa.BST;

/**
 * Created by antt on 4/13/2017.
 */
public class epi151 {
    public static void main(String[] args) {
        BST b = new BST();
        b.insert(3);b.insert(8);
        b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
        b.insert(20);b.insert(25);b.insert(15);b.insert(16);
        System.out.println("true = " + isBST(b.root));
        BST.BSTNode root = new BST.BSTNode(10);
        System.out.println("true = " + isBST(root));
        root.left = new BST.BSTNode(5);
        System.out.println("true = " + isBST(root));
        root.right = new BST.BSTNode(20);
        System.out.println("true = " + isBST(root));
        root.left.left = new BST.BSTNode(30);
        System.out.println("false = " + isBST(root));
        root.left.left.value = 2;
        System.out.println("true = " + isBST(root));
        root.left.right = new BST.BSTNode(3);
        System.out.println("false = " + isBST(root));
    }

    public static boolean isBST(BST.BSTNode node) {
        if (node == null) return true;
        if ((node.left != null && node.value < node.left.value)
                || (node.right != null && node.right.value < node.value)) {
            return false;
        }
        return isBST(node.left) && isBST(node.right);
    }
}
