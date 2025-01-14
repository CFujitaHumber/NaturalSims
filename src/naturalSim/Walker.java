package naturalSim;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.Random;

public class Walker {

	
	private PVector location;
	PVector velocity;
	private PVector acceleration;
	
	Random generator;
	private boolean noise = true;
	public boolean allowAcceleration = false;
	public float volume = 0.05f;
	
	public Walker(int width, int height) {
		generator = new Random();
		setLocation(new PVector(width/2, height/2));
		setAcceleration(new PVector(0.3f, 0.1f));
		velocity = new PVector();
	}
	
	public void display(Graphics2D g) {
		g.setStroke(new BasicStroke(0));
		g.fillOval((int)getLocation().x, (int)getLocation().y, 40, 40);
	}
	
	/**
	 * @deprecated
	 */
	void step() {
		int stepx = (int)(Math.random()*3)-1;
		int stepy = (int)(Math.random()*3)-1;
		getLocation().x += stepx;
		getLocation().y += stepy;
		
		
		/*
		 * if (noise) {
			double r = Math.random();
			if(r < 0.4) {
				velocity.x+=volume;
			} else if(r < 0.6) {
				velocity.x-=volume;
			} else if (r < 0.8) {
				velocity.y+=volume;
			} else {
				velocity.y-=volume;
			}
		}
		
		velocity.x += generator.nextGaussian()*volume;
		velocity.y += generator.nextGaussian()*volume;
		 */
		
		
	}
	
	public void smart_step() 
	{
		if(allowAcceleration) {
			velocity.add(getAcceleration());
		}
		
		if(velocity.x > 10) 
		{
			velocity.x = 0;
		}
		if(velocity.y > 10) 
		{
			velocity.y = 0;
		}
		
		if (isNoise()) {
			double r = Math.random();
			if(r < 0.4) {
				velocity.x+=volume;
			} else if(r < 0.6) {
				velocity.x-=volume;
			} else if (r < 0.8) {
				velocity.y+=volume;
			} else {
				velocity.y-=volume;
			}
		}
		getLocation().add(velocity);
	}
	
	void bounce(int width, int height) {
		if((getLocation().x>width) || ( getLocation().x < 0)) {
			velocity.x *= -1;
		}
		if((getLocation().y > height) || (getLocation().y < 0)) {
			velocity.y *= -1;
		}
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

	/**
	 * @return the noise
	 */
	public boolean isNoise() {
		return noise;
	}

	/**
	 * @param noise the noise to set
	 */
	public void setNoise(boolean noise) {
		this.noise = noise;
	}

	/**
	 * @return the location
	 */
	public PVector getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(PVector location) {
		this.location = location;
	}

	/**
	 * @return the acceleration
	 */
	public PVector getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration the acceleration to set
	 */
	public void setAcceleration(PVector acceleration) {
		this.acceleration = acceleration;
	}

}
