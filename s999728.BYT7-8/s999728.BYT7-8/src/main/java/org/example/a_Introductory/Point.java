package org.example.a_Introductory;

public class Point {
    public long x, y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Point add(Point p) {
        return new Point(x + p.x, y + p.y);
    }

    public Point sub(Point p) {
        return new Point(x - p.x,y - p.y);
    }
}
