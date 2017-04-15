package com.antt.dsa.graph;

import com.antt.dsa.Graph;
import static com.antt.dsa.Graph.*;

import java.io.StringReader;
import java.util.*;

/**
 * Created by antt on 4/9/2017.
 */
public class EPI199 {
    public static void main(String[] args) {
        String in = new StringBuilder()
                .append("5 5\n")
                .append("1 2 1\n")
                .append("2 3 2\n")
                .append("3 4 3\n")
                .append("5 4 2\n")
                .append("1 5 4\n").toString();

        Graph<Integer> g = Graph.readGraph(new StringReader(in), false);
        List<Integer> path = shortestPath(g, 1, 4);
        for(Integer e : path)  {
            System.out.printf("%d ", e);
        }
        System.out.println();
        in = new StringBuilder()
                .append("5 5\n")
                .append("1 2 1\n")
                .append("2 3 2\n")
                .append("3 4 3\n")
                .append("5 4 2\n")
                .append("1 5 5\n").toString();

        g = Graph.readGraph(new StringReader(in), false);
        path = shortestPath(g, 1, 4);
        for(Integer e : path)  {
            System.out.printf("%d ", e);
        }
    }

    // BFS to travel graph. Begin with start vertex
    // Using queue q to keep discovered vertex
    // Using dis[] to keep dis(s, v)
    // Using parent[] of type Parent to keep parent of v that make dis(s,v) minimum
    // Parent {
    //   int[] parent;
    //   int[] value;
    // }
    static class ParentInfo {
        List<Integer> nodes = new ArrayList<>();
        int bestParentIdx = -1;
        int bestValue = Integer.MAX_VALUE;
    }
    private static List<Integer> shortestPath(Graph<Integer> g, int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] processed = new boolean[g.nvertices+1];
        int[] dis = new int[g.nvertices+1];
        ParentInfo[] pinfos = new ParentInfo[g.nvertices+1];
        List<Integer> ret = new LinkedList<>();

        for (int i = 0; i < g.nvertices + 1; i++) {
            processed[i] = false;
            dis[i] = Integer.MAX_VALUE;
            pinfos[i] = new ParentInfo();
        }

        queue.add(start);
        dis[start] = 0;
        pinfos[start] = null;
        int nodeIdx = start;

        while(!processed[nodeIdx]) {
            processed[nodeIdx] = true;
//            if (nodeIdx == end) {
//                break;
//            }
            EdgeNode<Integer> p = g.edges.get(nodeIdx);
            while (p != null) {
                int distant = dis[nodeIdx] + p.weight;
                if (distant <= dis[p.vertex] && !processed[p.vertex]) {
                    dis[p.vertex] = distant;
                    updateParent(pinfos, nodeIdx, p.vertex);
                }
                p = p.next;
            }

            int min = Integer.MAX_VALUE;
            for(int i = 1; i < g.nvertices + 1; i++) {
                if (dis[i] < min && !processed[i]) {
                    min = dis[i];
                    nodeIdx = i;
                }
            }
        }

        ParentInfo info = pinfos[end];
        ret.add(end);
        while(info != null) {
            int bestParent = info.nodes.get(info.bestParentIdx);
            ret.add(bestParent);
            info = pinfos[bestParent];
        }
        Collections.sort(ret);
        return ret;
    }

    private static void updateParent(ParentInfo[] pinfos, int parent, int child) {
        ParentInfo pp = pinfos[child];
        pp.nodes.add(parent);
        if (pinfos[parent] == null) {
            pp.bestParentIdx = 0;
            pp.bestValue  = 1;
        } else if (pp.bestValue > pinfos[parent].bestValue + 1) {
            pp.bestValue = pinfos[parent].bestValue + 1;
            pp.bestParentIdx = pp.nodes.size() - 1;
        }
    }
}