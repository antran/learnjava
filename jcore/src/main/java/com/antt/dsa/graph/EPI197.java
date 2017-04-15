package com.antt.dsa.graph;

import java.util.*;

/**
 * Created by antt on 4/9/2017.
 */
public class EPI197 {
    public static void main(String[] args) {
        int seven = 7;
        int three = 7 >> 1;
        System.out.println(three);
//        return;
        Set<String> d = new HashSet<>(Arrays.asList("bat", "cot", "dog","dag","dot","cat"));
        List<String> ret = transfromStringBSF(d, "cat", "dog");
        System.out.printf("true = %b", ret.size() > 0);
        for (String s: ret) {
            System.out.printf("%s ", s);
        }
        System.out.println();
    }

    /**
     * P19.7
     */
    private static List<String> transfromStringBSF(Set<String> D, String s, String e) {
        List<String> ret = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, String > childParent = new HashMap<>();
        Map<String, Boolean> processed = new HashMap<>();
        queue.add(s);
        while(queue.size() > 0) {
            String current = queue.poll();
            processed.put(current, true);
            List<String> snext = next(D, current, processed);
            for (String sn : snext) {
                childParent.put(sn, current);
                if (sn.equals(e)) {
                    //make return list
                    String child = e;
                    ret.add(e);
                    while (childParent.containsKey(child)) {
                        String parent = childParent.get(child);
                        ret.add(parent);
                        child = parent;
                    }
                    //return
                    return ret;
                } else {
                    queue.add(sn);
                }
            }
        }
        return ret;
    }

    private static List<String> next(Set<String> d, String s, Map<String, Boolean> processed) {
        List<String> ret = new ArrayList<>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
//        System.out.println("add  child string... for " + s);
        for (int i =0; i < s.length(); i++) {
            String begin = i == 0 ? "" : s.substring(0, i);
            String end = i == s.length() - 1 ? "" : s.substring(i+1, s.length());
            for (char c : alphabet) {
                String ns = begin + c + end;
                if (d.contains(ns) && !processed.containsKey(ns)) {
//                    System.out.printf("'%s' ", ns);
                    ret.add(ns);
                }
            }
        }
//        System.out.println();
        return ret;
    }

}
