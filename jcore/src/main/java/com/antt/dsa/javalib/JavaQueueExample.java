package com.antt.dsa.javalib;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.zip.Inflater;

/**
 * Created by BL on 3/15/2017.
 */
public class JavaQueueExample {
    public static void main(String[] args) {
        Queue<Integer> q =new LinkedList<Integer>();
        //add element to head
        q.add(1); // throw exception if out of space
        q.offer(2); // not throw exception
        q.add(3);

        for(int i : q) {
            System.out.printf("%d ", i);
        }
        System.out.println();

        //retrieve head but not remove
        System.out.println("head element " + q.peek()); //null if queue empty
        System.out.println("head element " + q.element()); //throw exception if queue empty

        //retrieve and remove head
        System.out.println("head element " + q.poll()); //return null if empty
        System.out.println("head element " + q.remove()); //exception if empty

        Stack<Integer> st = new Stack<Integer>();

    }
}
