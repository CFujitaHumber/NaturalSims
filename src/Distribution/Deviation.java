package Distribution;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JFrame;


public class Deviation extends JFrame{

	private static final long serialVersionUID = -2026557945172855306L;
	Random generator;
	boolean start = false;
	
	public Deviation (){
		super ("Gassian"); // Set the frame's name
		System.setProperty("sun.awt.noerasebackground", "true");
		generator = new Random();
		setSize (800, 400);     // Set the frame's size
		setVisible (true);                // Show the frame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	} // Constructor
	 
	public void paint (Graphics g){
		
		// Place the drawing code here
		Graphics2D g2 =  (Graphics2D) g;
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);
	    if(!start) 
		{
			g2.fillRect(0, 0, 800, 400);
			start = true;
		}
		draw(g2);
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		repaint();
		
		
	} // paint method
	 
	private void draw(Graphics2D g2) {
		float num = (float) generator.nextGaussian();
		float sd = 60;
		float mean = 320;
		float x = sd * num + mean;
		
		float alpha = 0.1f;
		Color color = new Color(1, 0, 0, alpha); //Red 
		g2.setPaint(color);
		g2.setStroke(new BasicStroke(0));
		g2.fill(new Ellipse2D.Float(x,180,16,16));
	}

	public static void main (String[] args){
		new Deviation ();    // Create a Main frame
	} // main method
} // Main class