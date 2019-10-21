import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon; 
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.util.Collections;

public class Palette {

	private ArrayList<Character> ascii = new ArrayList<Character>();
	private ArrayList<String> palette = new ArrayList<String>();
	private Color color = null;
	private BufferedImage background = null;
	private Graphics2D graphics = null;

	public Palette() {
		createBackground();
		createAsciiArray();
		createPalette();
	}

	private void createBackground() {
		this.background = new BufferedImage(100,100, BufferedImage.TYPE_INT_RGB);
		this.graphics = this.background.createGraphics();
		this.graphics.setPaint(new Color(255,255,255));
		this.graphics.fillRect(0,0,this.background.getWidth(), this.background.getHeight());
	}

	// consider deleting 
	public void displayBackground() {
		JFrame frame = new JFrame();
		ImageIcon icon = new ImageIcon(this.background);
		JLabel label = new JLabel(icon);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private void drawStringOnBackground(String str) {
		int fontSize = 90;
		this.graphics.setPaint(new Color(255,255,255));
		this.graphics.fillRect(0,0,this.background.getWidth(), this.background.getHeight());
		this.graphics.setFont(new Font("Courier New", Font.PLAIN, fontSize));
		this.graphics.setColor(Color.black);
		this.graphics.drawString(str,5,70);
	}

	private int asciiPixelCount() {
		int charPixelCount = 0;
		for (int i = 0; i < this.background.getWidth(); i++) {
			for (int j = 0; j < this.background.getHeight(); j++) {
				this.color = new Color(this.background.getRGB(i,j));
				if (this.color.getRed() == 0) {
					charPixelCount += 1;
				}
			}
		}
		return charPixelCount;
	}

	private void createAsciiArray() {
		for (int i = 32; i < 127; i++) {
			this.ascii.add((char) i);
		}
	}

	private void createPalette() {
		ArrayList<Integer> key = new ArrayList<Integer>();
		Map<Integer,String> asciiPixelCounts = new HashMap<Integer,String>();

		for (int i = 0; i < this.ascii.size(); i++) {
			// Draw string on background must be called before asciipixelcounts, because otherwise its the same value everytime - a new thing has to be put on the background.
			drawStringOnBackground(Character.toString(this.ascii.get(i)));
			asciiPixelCounts.put(asciiPixelCount(), Character.toString(this.ascii.get(i)));				
		}
		for (int pCount : asciiPixelCounts.keySet()) {
			key.add(pCount);
		}
		
		Collections.sort(key);

		for (int j = 0; j < key.size(); j++) {
			this.palette.add(asciiPixelCounts.get(key.get(j)));
		}

		for (int k = 0; k < 13; k++) {
			if (k < 7) {
				this.palette.add(0, " ");
			} else {
				this.palette.add("M");
			}
		}
	}

	public BufferedImage getBackground() {
		return this.background;
	}

	public ArrayList<String> getPalette() {
		return this.palette;
	}

	public ArrayList<Character> getAscii() {
		return this.ascii;
	}

}