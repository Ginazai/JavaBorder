package ImageEditor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Keys implements KeyListener{
	public Keys(JFrame frame, String format, String defName){
		this.frame = frame;
		this.format = format;
		this.defName = defName;
	}
	
	JFrame frame;
	String format;
	String defName;
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE) {
			System.out.println("ESC");
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.dispose();
		} else if (key == KeyEvent.VK_S) {
			System.out.println("Save");
			try {
        		BufferedImage save = new BufferedImage(frame.getWidth(),
        				frame.getHeight(), BufferedImage.TYPE_INT_RGB);	
    	        Graphics2D graphics = save.createGraphics();	        	
    	        frame.printAll(graphics);	        	
    	        graphics.dispose();	
    	        ImageIO.write(save, format, new File(defName));       
    	        } catch (IOException x) {
    	        	x.printStackTrace();
    	        	}
		}
		if (key == KeyEvent.VK_1) {
			frame.getContentPane().setBackground(Color.BLACK);
			System.out.println("BLACK");
			} 
		if (key == KeyEvent.VK_2) {
			frame.getContentPane().setBackground(Color.WHITE);
			System.out.println("WHITE");
			}
		}
	}
