package ImageEditor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BorderImage extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6830024346447106156L;
	public static String getStringAt(String ts, int lng, char c) {
		int index = 0;
		for (int j = 0; j < lng; j++) {
			if (ts.charAt(j) == c) {
				index = j;
			}
		}
		ts = ts.substring(0, index);
		return ts;
	}	
	public static String getStringFrom(String ts, int lng, char c) {
		int index = 0;
		for (int j = 0; j < lng; j++) {
			if (ts.charAt(j) == c) {
				index = j;
			}
		}
		ts = ts.substring(index + 1, lng);
		return ts;
	}
	public static void main(String[] args){	
		try {
			JFrame frame = new JFrame();
			frame.setUndecorated(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocation(100, 100);
			
			JFileChooser fc = new JFileChooser();
			if(fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
				BufferedImage img = ImageIO.read(fc.getSelectedFile());	
				//Name assign
				String path = fc.getSelectedFile().getAbsolutePath();
				String tmp = fc.getSelectedFile().getName();
				path = getStringAt(path, path.length(), '\\');
				path = path + "\\";		
				String fileName = getStringAt(tmp, tmp.length(), '.');
				String format = fc.getSelectedFile().getName();
				format = getStringFrom(format, format.length(), '.');
				String defaultName = path + fileName + "-copy" + "." + format;
				//			
				int w = img.getWidth();
				int h = img.getHeight();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				if (w > h) {
					JLabel label = new JLabel(new ImageIcon(img), JLabel.CENTER);
					frame.getContentPane().setBackground(Color.WHITE);
					frame.setSize(w, w);
					frame.getContentPane().add(label);        	
		        	frame.setVisible(true);
		        	frame.addKeyListener(new Keys(frame, format, defaultName));
		        	
		        	int screenHeight = screenSize.height;
		            int screenWidth = screenSize.width;
		            frame.setLocation(screenWidth/2 - 100, screenHeight/2 - 100);
				} else if (h > w) {
					JLabel label = new JLabel(new ImageIcon(img), JLabel.CENTER);
					frame.getContentPane().setBackground(Color.WHITE);
					frame.setSize(h, h);
					frame.getContentPane().add(label);        	
		        	frame.setVisible(true);
		        	frame.addKeyListener(new Keys(frame, format, defaultName));
		        	
		        	int screenHeight = screenSize.height;
		            int screenWidth = screenSize.width;
		            frame.setLocation(screenWidth/2 - 100, screenHeight/2 - 100);
				} else if (h == w) {
					int newSize = (int) (w*0.90);
					Image imgCopy = img.getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH);
					JLabel label = new JLabel(new ImageIcon(imgCopy), JLabel.CENTER);	
					frame.getContentPane().setBackground(Color.WHITE);
					frame.setSize(w, h);
					frame.getContentPane().add(label);        	
		        	frame.setVisible(true);
		        	frame.addKeyListener(new Keys(frame, format, defaultName));
		        	
		        	int screenHeight = screenSize.height;
		            int screenWidth = screenSize.width;
		            frame.setLocation(screenWidth/2 - 100, screenHeight/2 - 100);
				}		
				}  
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}