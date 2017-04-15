package com.antt.dsa.graph;

import java.util.Objects;

/**
 * Created by antt on 4/9/2017.
 */
public class Coordinator {
    int x;
    int y;

    public Coordinator(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinator that = (Coordinator) o;
//
//        if (x != that.x) return false;
//        return y == that.y;
        return x == that.x && y == that.y;

    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
