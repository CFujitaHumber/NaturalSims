/**
 * 
 */
package walkers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import naturalSim.Liquid;
import naturalSim.Mover;
import naturalSim.PVector;

/**
 * @author thefu
 *
 */
public class setup extends JFrame  implements Runnable {


	private static final long serialVersionUID = -2026557945172855306L;
	Thread runner;
	Graphics2D bufferGraphics;
	BufferedImage offscreen;
    Dimension dim = new Dimension(800,400);
    Mover list[] = new Mover[50];
	Random gen;
	float gravity = 0.1f;
	PVector wind = new PVector(0.001f, 0f);
	private Liquid liquid, liquid2;
	RenderingHints rh;
	
	public setup (){
		super ("Walker"); // Set the frame's name
		setSize (dim.width, dim.height);     // Set the frame's size
		rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		liquid = new Liquid(0, dim.height/3, dim.width, dim.height/3, 0.1f);
		liquid2 = new Liquid(0, dim.height/3, dim.width, dim.height/3, 0.1f);
		//liquid = new Liquid(0, dim.height/6, dim.width, dim.height/2, 0.1f);

		offscreen = new BufferedImage(dim.width, dim.height, BufferedImage.TRANSLUCENT);
		bufferGraphics = (Graphics2D) offscreen.createGraphics();
		//bufferGraphics.setRenderingHints(rh);
		bufferGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		gen = new Random();
		
		for (int i = 0; i < 50; i++) 
		{ 
			list[i] = new Mover((float) (2-gen.nextFloat()), Math.abs(gen.nextInt(dim.width)), dim.height/4);
			list[i].setColor(new Color(Math.abs(gen.nextFloat()),Math.abs(gen.nextFloat()),Math.abs(gen.nextFloat()),Math.abs(gen.nextFloat())));
		}

		
	} // Constructor
	
	
	public void start() 
	{
		if(runner == null) 
		{
			runner = new Thread(this);
			runner.start();
			setVisible (true);                // Show the frame
		}
		

	}
	
	public void stop() 
	{
		if(runner != null) {
			runner.interrupt();
			runner = null;
		}
		
	}
	 
	
	public void run() 
	{
		while(true) 
		{
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void paint (Graphics g)
	{
		
		Graphics2D g2 = (Graphics2D) g;
		g2.addRenderingHints(rh);
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		bufferGraphics.setColor(Color.white);
		bufferGraphics.fillRect(0, 0, dim.width, dim.height);
		
		
		PVector mouse = PVector.toVector(MouseInfo.getPointerInfo().getLocation()) ;
		PVector dir;
		/*PVector dir = PVector.sub(mouse, w.location);
		dir.normalize();
		
		dir.mult(0.15f);
		w.acceleration =  dir;*/
		liquid.display(bufferGraphics);
		liquid2.display(bufferGraphics);
		for (int i = 0; i < 50; i++) 
		{ 
			//System.out.println( (0.5*gen.nextGaussian()));
			//Color color = new Color((float) Math.abs(0.3*gen.nextGaussian()),(float) Math.abs(0.3*gen.nextGaussian()) , (float)Math.abs(0.3*gen.nextGaussian()), alpha); //Red 
			
			bufferGraphics.setPaint(new Color(0.5f,0.35f,0.6f,0.5f));
			
			//dir = PVector.sub(mouse, list[i].location);
			//dir.normalize();
			//dir.mult((float) Math.abs(gen.nextGaussian()*0.5 ));
			//list[i].applyForce(dir);
			if(list[i].isInside(liquid)) 
			{
				list[i].drag(liquid);
			}
			//list[i].applyForce(wind);
			list[i].applyGravity(gravity);
			list[i].update();
			list[i].display(bufferGraphics);
			list[i].checkEdges(dim.width,  dim.height);
		}
		
		//System.out.println(w.acceleration.x + " "+ w.acceleration.y);
		

		/*
		w.smart_step();
		w.display(bufferGraphics);
		
		bufferGraphics.setColor(Color.blue);
		
		w1.bounce(dim.width-10, dim.height-10);
		w1.smart_step();
		w1.display(bufferGraphics);
		*/
		bufferGraphics.setColor(Color.blue);
		g2.drawImage(offscreen, 0, 0, this);
		
	} // paint method

	
	public void update(Graphics g) 
	{
		paint(g);
	}
	 
	public static void main (String[] args){
		new setup ().start();    // Create a Main frame
	} // main method
} //