package naturalSim;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Liquid {

	float x,y,w,h;
	float c;
	Color color = Color.blue;
	
	public Liquid(float x, float y, float w, float h, float c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
	}
	
	public void display(Graphics2D g) {
		g.setStroke(new BasicStroke(0));
		g.setColor(color);
		g.fillRect((int) x,(int) y, (int) w, (int) h);
	}

}
