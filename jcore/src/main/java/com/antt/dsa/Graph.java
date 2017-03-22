package com.antt.dsa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antt on 3/22/2017.
 */
public class Graph <K extends Comparable<K>> {
    int nedges;
    int nvertices;
    boolean directed;
    List<EdgeNode> edges = new ArrayList<>();
    List<Integer> degrees = new ArrayList<>();

    public static class EdgeNode<K> {
        K node;
        int weight;
        EdgeNode<K> next;
    }
}
