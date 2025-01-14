//The "Main" class.
package walkers;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import naturalSim.*;

public class RandomWalks extends JFrame  implements Runnable {


	private static final long serialVersionUID = -2026557945172855306L;
	Thread runner;
	Graphics2D bufferGraphics;
	BufferedImage offscreen;
    Dimension dim = new Dimension(800,400);
	Walker list[] = new Walker[50];
	Random gen;
	private BufferedImage background;

	
	public RandomWalks (){
		super ("Walker"); // Set the frame's name
		setSize (dim.width, dim.height);     // Set the frame's size
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		offscreen = new BufferedImage(dim.width, dim.height, BufferedImage.TRANSLUCENT);
		bufferGraphics = (Graphics2D) offscreen.createGraphics();
		bufferGraphics.setRenderingHints(rh);
		
		gen = new Random();
		
		for (int i = 0; i < 50; i++) { 
			list[i] = new Walker(dim.width, dim.height);
			list[i].allowAcceleration = true;
			list[i].setNoise(true);
			list[i].volume = 0.5f;
		}

		
		try {
			background = ImageIO.read (new File (".\\src\\Underwater-terrain-011.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	} // Constructor
	
	
	public void start() {
		if(runner == null) 
		{
			runner = new Thread(this);
			runner.start();
			setVisible (true);                // Show the frame
		}
		

	}
	
	public void stop() {
		if(runner != null) {
			runner.interrupt();
			runner = null;
		}
		
	}
	 
	
	public void run() {
		while(true) {
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
		
		
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		bufferGraphics.drawImage(background, 0, 0, dim.width, dim.height, this);
		bufferGraphics.setColor(Color.white);
		
		
		PVector mouse = PVector.toVector(MouseInfo.getPointerInfo().getLocation()) ;
		PVector dir;
		/*PVector dir = PVector.sub(mouse, w.location);
		dir.normalize();
		
		dir.mult(0.15f);
		w.acceleration =  dir;*/
		for (int i = 0; i < 50; i++) { 
			//System.out.println( (0.5*gen.nextGaussian()));
			//Color color = new Color((float) Math.abs(0.3*gen.nextGaussian()),(float) Math.abs(0.3*gen.nextGaussian()) , (float)Math.abs(0.3*gen.nextGaussian()), alpha); //Red 
			
			bufferGraphics.setPaint(new Color(0.5f,0.35f,0.6f,0.5f));
			dir = PVector.sub(mouse, list[i].getLocation());
			dir.normalize();
			dir.mult((float) Math.abs(gen.nextGaussian()*0.5 ));
			list[i].setAcceleration(dir);
			list[i].smart_step();
			list[i].display(bufferGraphics);
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
		g.drawImage(offscreen, 0, 0, this);
		
	} // paint method

	
	public void update(Graphics g) {
		paint(g);
	}
	 
	public static void main (String[] args) {
		new RandomWalks ().start();    // Create a Main frame
	} // main method
} // Main class