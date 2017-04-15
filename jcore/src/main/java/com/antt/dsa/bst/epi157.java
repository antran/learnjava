package com.antt.dsa.bst;

import com.antt.dsa.BST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by antt on 4/13/2017.
 * TODO: not correct
 */
public class epi157 {
    public static void main(String[] args) {
        BST b = new BST();
        b.insert(3);b.insert(8);
        b.insert(1);b.insert(4);b.insert(6);b.insert(2);b.insert(10);b.insert(9);
        b.insert(20);b.insert(25);b.insert(15);b.insert(16);
        b.display(b.root);
        System.out.println();
        List<Node> ret = kSmallest(0,2,3);
        ret.stream().forEach(System.out::print);
        System.out.println();
        ret = kSmallest(0,2,4);
        ret.stream().forEach(System.out::print);
        System.out.println();
        ret = kSmallest(0,2,5);
        ret.stream().forEach(System.out::print);
        System.out.println();
        ret = kSmallest(3,1,6);
        ret.stream().forEach(System.out::print);
        System.out.println();

    }

    public static List<Node> kSmallest(int a, int b, int k) {
        List<Node> ret = new LinkedList<>();
        Node ab = new Node(a,b);
        List<List<Node>> couples = new ArrayList<>();
        couples.add(new ArrayList<>(Arrays.asList(new Node(0,0))));
        ret.add(couples.get(0).get(0));
        Node last = ret.get(0);
        for (int i = 1; i <= k+1 && i <= (a+b+1); i++) {
            List<Node> prev = couples.get(i-1);
            List<Node> curr = new ArrayList<>();
            Node st = prev.get(0);
            curr.add(new Node(st.a+1, st.b));
            if (ab.getValue() >= st.getValue()) ret.add(st);
            if (st.a == a && st.b == b) break;
            for (int j = 0; j < prev.size(); j++) {
                Node pn = prev.get(j);
                st = new Node(pn.a, pn.b+1);
                curr.add(st);
                if (curr.get(j).a >= a && curr.get(j).b >= b) break;
                if (ab.getValue() >= st.getValue()) ret.add(st);
            }
            couples.add(curr);
            System.out.println("i= " + i+ ", size = " + curr.size());

        }
        ret.sort((x,y) -> {
            if (x.getValue() < y.getValue()) return - 1;
            if (x.getValue() == y.getValue()) return 0;
            return 1;
        });
        return ret;
    }

    private static class Node {
        int a;
        int b;
        public double getValue() {
            return (a + b*Math.sqrt(2));
        }
        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "" + a +
                    ", "+ b +
                    ", " + getValue() +
                    "} ";
        }
    }
}
