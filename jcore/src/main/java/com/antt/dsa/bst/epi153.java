package com.antt.dsa.bst;

import com.antt.dsa.BST;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by antt on 4/13/2017.
 */
public class epi153 {
    public static void main(String[] args) {
        BST b = new BST();
        b.insert(3);b.insert(8);
        b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
        b.insert(20);b.insert(25);b.insert(15);b.insert(16);
        b.display(b.root);
        System.out.println();
        System.out.println("4 = " + kLargest(b.root, 4));
        System.out.println("6 = " + kLargest(b.root, 6));
        System.out.println("7 = " + kLargest(b.root, 7));
        System.out.println("1 = " + kLargest(b.root, 1));
        System.out.println("3 = " + kLargest(b.root, 3));
        System.out.println("9 = " + kLargest(b.root, 9));
        System.out.println("10 = " + kLargest(b.root, 10));
        System.out.println("11 = " + kLargest(b.root, 11));
        System.out.println("12 = " + kLargest(b.root, 12));
        System.out.println("13 = " + kLargest(b.root, 13));
        System.out.println("14 = " + kLargest(b.root, 14));

    }

    public static List<Integer> kLargest(BST.BSTNode node, int k) {
        if (node == null) return new LinkedList<>();

        List<Integer> ret = kLargest(node.right, k);
        if (ret.size() >= k) return ret.subList(0, k);
        ret.add(node.value);
        if (ret.size() >= k) return ret.subList(0,k);
        List<Integer> retL = kLargest(node.left, k - ret.size());

        for(int i = 0; i < retL.size() ; i++) {
            ret.add(retL.get(i));
        }
        return ret;
    }
}
