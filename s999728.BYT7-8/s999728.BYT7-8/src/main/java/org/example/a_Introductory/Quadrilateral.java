package org.example.a_Introductory;

public class Quadrilateral {
    private Point p1, p2, p3, p4;
    private Line l1, l2, l3, l4;

    Quadrilateral(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.l1 = new Line(p1, p2);
        this.l2 = new Line(p2, p3);
        this.l3 = new Line(p3, p4);
        this.l4 = new Line(p4, p1);
    }

    public Boolean isRectangle() {
        return (l1.isParallelTo(l3) && l1.isSameLengthAs(l3) && l2.isParallelTo(l4) && l2.isSameLengthAs(l4));
    }

    public Boolean isSquare() {
        return (isRectangle() && l1.isSameLengthAs(l2) && l2.isSameLengthAs(l3));
    }

}
