package naturalSim;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Mover {

	Random generator; //number generation
	PVector location; // 
	PVector velocity;
	PVector acceleration;	
	PVector friction;
	Ellipse2D.Float shape;
	float mass = 10.0f;
	private Color color;
	
	public Mover(float mass, float x, float y) {
		this.mass = mass;
		generator = new Random();
		location = new PVector(x, y);
		acceleration = new PVector(0f, 0f);
		velocity = new PVector(0f,0f);
		shape = new Ellipse2D.Float(x, y, mass*16, mass*16);
	}
	
	public Mover(float x, float y) {
		mass = 10f;
		generator = new Random();
		location = new PVector(x, y);
		acceleration = new PVector(0.3f, 0.1f);
		velocity = new PVector();
		shape = new Ellipse2D.Float(x, y, mass*16, mass*16);
	}
	
	/**
	 * Will display as a circle.
	 * @param g
	 */
	public void display(Graphics2D g) {
		g.setStroke(new BasicStroke(0));
		g.setColor(getColor());
		g.fill(shape);
		g.draw(shape);
		
	}
	
	public void update() {
		
		shape.x = location.x;
		shape.y = location.y;
		velocity.add(acceleration);
		location.add(velocity);
		
		acceleration.mult(0);
	}
	
	public void checkEdges(int width, int height) {
		if(location.x > width) 
		{
			location.x = width;
			velocity.x *= -1;
		} else if(location.x < 0) {
			velocity.x *= -1;
			location.x = 0;
		}
		
		if(location.y > height) {
			velocity.y *= -1;
			location.y = height;
		}
	}
	

	public void applyGravity(float force) {
		PVector gravity = new PVector(0,force*mass);
		applyForce(gravity);
	}
	
	void applyForce(PVector force) {
		PVector f = PVector.div(force, mass);
		acceleration.add(f);
		  }
	
	
	void applyFriction() 
	{
		float c = 0.01f;//TODO Coeffiecent of friction
		float normal = 1; //TODO
		float frictionMag = c*normal;
		friction = velocity;
		friction.normalize();
		friction.mult(-1);
		friction.mult(frictionMag);
		applyForce(friction);
	}
	
	int probability()
	{
		int[] stuff = new int[5];
		stuff[0] = 1;
		stuff[1] = 1;
		stuff[2] = 2;
		stuff[3] = 3;
		stuff[4] = 3;
		int index = (int)Math.random()*stuff.length;
		return index;
	}

	
	public boolean isInside(Liquid l) {
		if(location.x>l.x && location.x<l.x+l.w && location.y>l.y && location.y<l.y+l.h) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * @param l The liquid to drag in
	 */
	public void drag(Liquid l) 
	{
		double speed = velocity.mag();
		float dragMagnitude = l.c * (float)(speed * speed);
		
		PVector drag = velocity;
		drag.mult(-1);
		drag.normalize();
		
		drag.mult(dragMagnitude);
		
		applyForce(drag);
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	



}
