package com.antt.dsa.javalib;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by antt on 4/11/2017.
 */
public class Heap {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b) -> {
            if (a > b) return -1;
            if (b == a) return 0;
            return 1;
        });
        pq.addAll(Arrays.asList(1,2,3,4,5));
        while(pq.size() > 0) {
            System.out.println(pq.poll());
        }
        System.out.println("###");
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        minheap.addAll(Arrays.asList(5,2,3,1,4));
        while(minheap.size() > 0) {
            System.out.println(minheap.poll());
        }
    }
}

