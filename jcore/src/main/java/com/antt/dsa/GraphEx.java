package com.antt.dsa;

import java.util.*;

/**
 * Created by antt on 3/22/2017.
 */
public class GraphEx {

    public static void main(String[] args) {
        int seven = 7;
        int three = 7 >> 1;
        System.out.println(three);
//        return;
        testSearchMaze();
        Set<String> d = new HashSet<>(Arrays.asList("bat", "cot", "dog","dag","dot","cat"));
        List<String> ret = transfromStringBSF(d, "cat", "dog");
        System.out.printf("true = %b", ret.size() > 0);
        for (String s: ret) {
            System.out.printf("%s ", s);
        }
        System.out.println();
    }

    static class Coordinator {
        int row;
        int col;

        public Coordinator(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    /**
     * P19.1 search a maze
     * @param
     */
    private static boolean searchMaze(int[][] maze, int sx, int sy, int fx, int fy) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        boolean ret = false;

        //init visited
        for(boolean[] r : visited) {
            for(int i = 0; i < r.length; i++) {
                r[i] = false;
            }
        }

        return searchMazeDFS(maze, sx, sy, fx, fy, visited);
    }

    private static boolean searchMazeDFS(int[][] maze, int sx, int sy, int fx, int fy, boolean[][] visited) {
        if (sx == fx && sy == fy) return true;
        if (visited[sx][sy]) return false;
        visited[sx][sy] = true;
        for (int i = sx - 1; i <= sx + 1; i++) {
            for (int j = sy - 1; j <= sy + 1; j++) {
                if (i < 0 || j < 0 || i >= maze.length || j >= maze[i].length || maze[i][j] == 0) {
                    continue;
                }
                if (searchMazeDFS(maze,i, j, fx, fy, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean searchMaze(int[][] maze, Coordinator from, Coordinator to) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return searchMazeDFS(maze, from, to, visited);
    }

    private static boolean searchMazeDFS(int[][] maze, Coordinator from, Coordinator to, boolean[][] visited) {
        // if from == to return true
        // if from == null return false
        // mark visited[from.r][from.col] = true
        // get neighbours of from
        // for each n in neighbours
        // found = searchMazeDFS(maze, n, to);

        if (from == null || visited[from.row][from.col] || maze[from.row][from.col] == 0) return false;
        if (from.row == to.row && from.col == to.col) return true;
        visited[from.row][from.col] = true;
        Coordinator[] neightbours = new Coordinator[8];
        int count = 0;
        for(int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i ==0 && j ==0) continue;
                if (from.row + i < 0 || from.row + i >=  maze.length
                        || from.col + j < 0 || from.col + j >= maze[0].length) {
                    neightbours[count] = null;
                } else {
                    neightbours[count] = new Coordinator(from.row + i, from.col + j);
                }
                count++;
            }
        }
        for (Coordinator c : neightbours) {
            if (searchMazeDFS(maze, c, to, visited)) {
                return true;
            }
        }
        return false;
    }

    private static void testSearchMaze() {
        System.out.printf("True = %b\n", searchMaze(
                new int[][]{{1,1,1},
                            {1,0,1},
                            {1,1,1}},
                2, 0, 0, 2));
        System.out.printf("# True = %b\n", searchMaze(
                new int[][]{{1,1,1},
                        {1,0,1},
                        {1,1,1}},
                new Coordinator(2, 0), new Coordinator(0, 2)));
        System.out.printf("false = %b\n", searchMaze(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                2, 0, 0, 2));
        System.out.printf("# false = %b\n", searchMaze(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                new Coordinator(2, 0), new Coordinator(0, 2)));
        System.out.printf("false = %b\n", searchMaze(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                2, 0, 2, 2));
        System.out.printf("# false = %b\n", searchMaze(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                new Coordinator(2, 0), new Coordinator(2, 2)));
        System.out.printf("true = %b\n", searchMaze(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                0, 0, 2, 2));
        System.out.printf("# true = %b\n", searchMaze(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                new Coordinator(0, 0), new Coordinator(2, 2)));
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
