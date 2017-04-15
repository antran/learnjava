package com.antt.dsa.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by antt on 4/9/2017.
 */
public class EPI193 {
    public static void main(String[] args) {
        // given a start
        // find white region
        // if it is enclosed turn it to black
        // if not mark entire region as visited
        System.out.println("##########");
        paintEnclosedArea(
                new int[][]{
                        {0,0,0,0},
                        {0,0,1,0},
                        {1,0,1,0},
                        {1,0,0,0}});
        System.out.println("##########");
        paintEnclosedArea(
                new int[][]{
                        {0,0,0,0},
                        {0,1,0,0},
                        {1,0,1,0},
                        {1,0,0,0}});
        System.out.println("##########");
        paintEnclosedArea(
                new int[][]{
                        {0,0,1,0},
                        {0,1,0,1},
                        {1,0,1,0},
                        {1,0,0,0}});
        System.out.println("##########");
        paintEnclosedArea(
                new int[][]{
                        {0,0,1,0},
                        {0,1,1,1},
                        {1,1,1,0},
                        {1,0,0,0}});

        System.out.println("##########");
        paintEnclosedArea(
                new int[][]{
                        {0,0,0,0},
                        {0,1,1,0},
                        {0,1,1,0},
                        {0,0,0,0}});
        System.out.println("##########");
        paintEnclosedArea(
                new int[][]{
                        {0,0,0,0},
                        {0,1,1,0},
                        {0,1,0,1},
                        {0,0,1,1}});

    }

    private static void paintEnclosedArea(int[][] maze) {
        for ( int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                findRegion(maze, new Coordinator(i, j));
            }
        }
        for(int[] r: maze) {
            for(int c: r) {
                System.out.printf("%d ", c);
            }
            System.out.println();
        }
    }
    // Back is 0
    // White is 1
    private static List<Coordinator> findRegion(int[][] maze, Coordinator start) {
        List<Coordinator> ret = new LinkedList<>();
        boolean isEnclosed = true;
        int[][] DIRECTIONS = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        for (int i = 0; i < visited.length; i ++) {
            for(int j =0; j < visited[i].length; j++) {
                visited[i][j] = false;
            }
        }
        if (maze[start.x][start.y] == 0) {
            return Collections.emptyList();
        }

        Queue<Coordinator> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            //System.out.println("aaa");
            Coordinator cell = queue.poll();
            visited[cell.x][cell.y] = true;
            ret.add(cell);
            if (isEnclosed)
                isEnclosed = !atBorder(cell, maze); //TODO
            for(int[] dir : DIRECTIONS) {
                Coordinator coord = new Coordinator(cell.x + dir[0], cell.y + dir[1]);
                if (coord.x >= 0 && coord.x < maze.length
                        && coord.y >=0 && coord.y < maze[0].length
                        && maze[coord.x][coord.y] == 1
                        && !visited[coord.x][coord.y]) {
                    queue.add(coord);
                }
            }
        }
        if (isEnclosed) {
            for(Coordinator c : ret) {
                maze[c.x][c.y] = 0;
            }
        }
        return ret;
    }

    private static boolean atBorder(Coordinator cell, int[][] maze) {
        return cell.x == 0 || cell.y == 0 || cell.x == maze.length - 1 || cell.y == maze[cell.x].length - 1;
    }
}
