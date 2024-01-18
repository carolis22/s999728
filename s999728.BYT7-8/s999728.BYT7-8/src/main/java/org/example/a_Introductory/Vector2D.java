package org.example.a_Introductory;

public class Vector2D {
	public long x, y;
	
	Vector2D(long x, long y) {
		this.x = x;
		this.y = y;
	}
	
	/* Construct Vector2D from two points */
	Vector2D(Point p1, Point p2) {
		this.x = p2.x - p1.x;
		this.y = p2.y - p1.y;
	}

	public int dotProduct(Vector2D v) {
		return (int) ((x * v.x) + (y * v.y));
	}
	
	public boolean isOrthogonalTo(Vector2D v) {
		return (dotProduct(v) == 0);
	}
}
