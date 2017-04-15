package com.antt.dsa;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by antt on 3/22/2017.
 */
public class Graph <K extends Comparable<K>> {
    public int nedges;
    public int nvertices;
    public boolean directed;
    public List<EdgeNode> edges = new ArrayList<>();
    public List<Integer> degrees = new ArrayList<>();

    public static class EdgeNode<K> {
        public K vertex;
        public int weight;
        public EdgeNode<K> next;

        public EdgeNode(K y, int w, EdgeNode<K> e) {
            vertex = y;
            weight = w;
            next = e;
        }
    }

    public static Graph<Integer> readGraph(Reader is, boolean isDirected) {
        Graph<Integer> g = new Graph<>();
        Scanner scanner = new Scanner(is);
        int nvertex = scanner.nextInt();
        int nedges = scanner.nextInt();
        g.nvertices = nvertex;
        g.nedges = nedges;
        g.directed = isDirected;
        for (int i = 0; i < nvertex + 1; i++) {
            g.edges.add(null);
            g.degrees.add(0);
        }
        scanner.nextLine();
        for(int i = 0; i < nedges; i++) {
            String[] ele = scanner.nextLine().split("\\s");
            insertEdge(g, Integer.parseInt(ele[0]), Integer.parseInt(ele[1]),
                        ele.length >= 3 ? Integer.parseInt(ele[2]) : 0, isDirected);
        }
        return g;
    }

    private static void insertEdge(Graph<Integer> g, int x, int y, int w, boolean isDirected) {
        EdgeNode<Integer> e = g.edges.get(x);
        EdgeNode<Integer> newy = new EdgeNode<Integer>(y, w, e);
        g.edges.set(x, newy);
        g.degrees.set(x, g.degrees.get(x) + 1);
        if (!isDirected) {
            insertEdge(g, y, x, w, true);
        }
    }

    public static void main(String[] args) {
        String in = new StringBuilder()
                            .append("5 5\n")
                            .append("1 2 1\n")
                            .append("2 3 2\n")
                            .append("3 4 3\n")
                            .append("5 4 2\n")
                            .append("1 5 4\n").toString();

        Graph<Integer> g = Graph.readGraph(new StringReader(in), false);
        System.out.printf("Nvertices %d, Nedges %d\n", g.nvertices, g.nedges);
        for(int i = 1; i < g.edges.size(); i++) {
            System.out.printf("Vertext %d -> ", i);
            EdgeNode<Integer> ed = g.edges.get(i);
            while(ed != null) {
                System.out.printf("(%d,%d)  ", ed.vertex, ed.weight);
                ed = ed.next;
            }
            System.out.println();
        }
    }
}
