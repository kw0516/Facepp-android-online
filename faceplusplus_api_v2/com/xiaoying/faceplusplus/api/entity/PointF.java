package com.xiaoying.faceplusplus.api.entity;

import java.io.Serializable;

import android.graphics.Point;

public class PointF implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2682901319355529372L;
	public float x;
	public float y;

	public PointF() {
	}

	public PointF(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public PointF(Point p) {
		this.x = p.x;
		this.y = p.y;
	}

	/**
	 * Set the point's x and y coordinates
	 */
	public final void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Set the point's x and y coordinates to the coordinates of p
	 */
	public final void set(PointF p) {
		this.x = p.x;
		this.y = p.y;
	}

	public final void negate() {
		x = -x;
		y = -y;
	}

	public final void offset(float dx, float dy) {
		x += dx;
		y += dy;
	}

	/**
	 * Returns true if the point's coordinates equal (x,y)
	 */
	public final boolean equals(float x, float y) {
		return this.x == x && this.y == y;
	}

	/**
	 * Return the euclidian distance from (0,0) to the point
	 */
	public final float length() {
		return length(x, y);
	}

	/**
	 * Returns the euclidian distance from (0,0) to (x,y)
	 */
	public static float length(float x, float y) {
		return (float) Math.sqrt(x * x + y * y);
	}
}
