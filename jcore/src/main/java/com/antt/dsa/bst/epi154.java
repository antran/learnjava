package com.antt.dsa.bst;

import com.antt.dsa.BST;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by antt on 4/13/2017.
 */
public class epi154 {
    public static void main(String[] args) {
        BST b = new BST();
        b.insert(3);b.insert(8);
        b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
        b.insert(20);b.insert(25);b.insert(15);b.insert(16);
        b.display(b.root);
        System.out.println();
        BST.BSTNode one = b.root.left;
        BST.BSTNode two = one.right;
        BST.BSTNode eight = b.root.right;
        BST.BSTNode four = eight.left;
        BST.BSTNode ten = eight.right;
        BST.BSTNode twenty = ten.right;
        System.out.println("3 = " + findLCA(b.root, one, b.root));
        System.out.println("3 = " + findLCA(b.root, one, eight));
        System.out.println("3 = " + findLCA(b.root, two, eight));
        System.out.println("8 = " + findLCA(b.root, four, ten));
        System.out.println("8 = " + findLCA(b.root, four, twenty));
        System.out.println("8 = " + findLCA(b.root, four, eight));
        System.out.println("8 = " + findLCA(b.root, eight, twenty));
        System.out.println("8 = " + findLCA(b.root, eight, four));

    }

    public static BST.BSTNode findLCA(BST.BSTNode base, BST.BSTNode x, BST.BSTNode y) {

        if (((base.value > x.value) && (base.value > y.value)) ) {
            return findLCA(base.left, x, y);
        }

        if ((base.value < x.value) && (base.value < y.value)) {
            return findLCA(base.right, x, y);
        }

        if (base.value == x.value) return x;
        if (base.value == y.value) return y;

        return base;
    }
}
