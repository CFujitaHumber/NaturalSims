package naturalSim;

import java.awt.Point;

public class PVector {

	float x;
	float y;
	
	public static PVector toVector(Point p) {
		return new PVector(p.x, p.y);
	}
	
	public static PVector add(PVector v1, PVector v2) {
		PVector v3 = new PVector(v1.x + v2.x, v1.y + v2.y);
	    return v3;
    }
	
	public static PVector sub(PVector v1, PVector v2) {
		PVector v3 = new PVector(v1.x - v2.x, v1.y - v2.y);
		return v3;
	}
	
	public static PVector div(PVector v1, float v2) {
		PVector v3 = new PVector(v1.x / v2, v1.y / v2);
		return v3;
	}
	
	public PVector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public PVector() {
		x = 0;
		y = 0;
	}
	
	public PVector(double x, double y) {
		this.x = (float) x;
		this.y = (float) y;
	}
	
	

	public void add(PVector v) {
		y = y+ v.y;
		x = x+ v.x;
	}
	
	public void sub(PVector v) {
		x = x - v.x;
		y = y - v.y;
	}
	
	public void mult(float n) {
		x = x*n;
		y = y*n;
	}
	
	public void div(float n) {
		x = x/n;
		y = y/n;
	}
	
	
	public double mag() {
		return Math.sqrt(x*x + y*y);
	}
	
	
	public void normalize() {
		float m = (float) mag();
		if (m != 0) {
			   div(m);
	   }
	}



}
