package com.antt.dsa.graph;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by antt on 4/9/2017.
 */
public class EPI192 {
    public static void main(String[] args) {
         paintMatrix(
                new int[][]{{1,1,1},
                        {1,0,1},
                        {1,1,1}},
                new Coordinator(2, 0));
        System.out.println("##########");
        paintMatrix(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                new Coordinator(2, 0));
        System.out.println("##########");
        paintMatrix(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                new Coordinator(2, 2));
        System.out.println("##########");
        paintMatrix(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                new Coordinator(1, 1));

        System.out.println("//######### DFS");
        paintMatrixDFS(
                new int[][]{{1,1,1},
                        {1,0,1},
                        {1,1,1}},
                new Coordinator(2, 0));
        System.out.println("##########");
        paintMatrixDFS(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                new Coordinator(2, 0));
        System.out.println("##########");
        paintMatrixDFS(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                new Coordinator(2, 2));
        System.out.println("##########");
        paintMatrixDFS(
                new int[][]{
                        {1,1,1},
                        {0,0,1},
                        {1,0,1}},
                new Coordinator(1, 1));
    }

    private  static void paintMatrixDFS(int[][] matrix, Coordinator start) {
        int color = matrix[start.x][start.y];
        paintMatrixDFSRecur(matrix,start, color);
        for(int[] r: matrix) {
            for(int i : r) {
                System.out.printf("%d ", i);
            }
            System.out.println();
        }
    }

    private static void paintMatrixDFSRecur(int[][] matrix, Coordinator start, int color) {
        // stop condition
        // loop all start neighbour and call dfs on each one
        if (matrix[start.x][start.y] != color) return;

        int[][] DIRECTIONS = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        matrix[start.x][start.y] = color == 1 ? 0 : 1;
        for(int[] e : DIRECTIONS) {
            Coordinator coord = new Coordinator(start.x + e[0], start.y + e[1]);
            if (coord.x < 0 || coord.x >= matrix.length
                    || coord.y < 0 || coord.y >= matrix[0].length
                    || color != matrix[coord.x][coord.y]) {
                continue;
            }
            paintMatrixDFSRecur(matrix, coord, color);
        }

    }

    private static  void paintMatrix(int[][] matrix, Coordinator start) {
        // get original color
        // using bfs to visit all adjacent cells with same color as original color
        // at each visit flip color
        int origColor = matrix[start.x][start.y];
        Queue<Coordinator> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Coordinator visiting = queue.poll();
            addNeightbours(queue, matrix, visiting, origColor);
            //flip visiting color
            matrix[visiting.x][visiting.y] = origColor == 1 ? 0 : 1;
        }

        for(int[] r: matrix) {
            for(int i : r) {
                System.out.printf("%d ", i);
            }
            System.out.println();
        }
    }

    private static void addNeightbours(Queue<Coordinator> queue, int[][] matrix, Coordinator visiting, int origColor) {
        int[][] DIRECTIONS = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        for(int[] e : DIRECTIONS) {
            Coordinator coord = new Coordinator(visiting.x + e[0], visiting.y + e[1]);
            if (coord.x < 0 || coord.x >= matrix.length
                    || coord.y < 0 || coord.y >= matrix[0].length
                    || origColor != matrix[coord.x][coord.y]) {
                continue;
            }
            queue.add(coord);
        }
    }
}
