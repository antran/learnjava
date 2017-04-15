package com.antt.dsa.bst;

import com.antt.dsa.BST;

/**
 * Created by antt on 4/13/2017.
 */
public class epi152 {
    public static void main(String[] args) {
        BST b = new BST();
        b.insert(3);b.insert(8);
        b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
        b.insert(20);b.insert(25);b.insert(15);b.insert(16);
        System.out.println("8 = " + firstGreater(b.root, 7));
        System.out.println("3 = " + firstGreater(b.root, 2));
        System.out.println("4 = " + firstGreater(b.root, 3));
        System.out.println("max = " + firstGreater(b.root, 30));
        System.out.println("15 = " + firstGreater(b.root, 14));
        System.out.println("16 = " + firstGreater(b.root, 15));
        System.out.println("1 = " + firstGreater(b.root, 0));

    }

    public static int firstGreater(BST.BSTNode node, int value) {
        return fG(node, value,Integer.MAX_VALUE);
    }

    private static int fG(BST.BSTNode node, int value, int maxValue) {
        if (node == null) return maxValue;
        if (node.value > value) {
            return fG(node.left, value, node.value);
        } else {
            return fG(node.right, value, maxValue);
        }
    }
}
