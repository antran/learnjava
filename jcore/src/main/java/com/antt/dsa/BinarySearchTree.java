package com.antt.dsa;

import java.util.function.Function;

/**
 * Created by antt on 3/18/2017.
 */
public class BinarySearchTree<Key extends Comparable, Val> {
    Node root;
    private class Node {
        Key key;
        Val value;
        Node left;
        Node right;

        public Node(Key key, Val value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public BinarySearchTree() {

    }

    public Val get(Key k) {
        return null;
    }

    public Val delete(Key k) {
        return null;
    }

    public boolean insert(Key k, Val v) {
        return false;
    }

    public int height() {
        return -1;
    }

}
