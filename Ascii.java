import java.util.*;
import java.awt.color.ColorSpace;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.Graphics2D;

public class Ascii {

	private ArrayList<String> palette = null;
	private Image image = null;
	private BufferedImage asciiImage = null;
	private Graphics2D graphics = null;

	/**
		This class handles the creation of the strings of ascii from the image supplied
		from the user. It also does the drawing of the created strings onto a new 
		buffered image called asciiImage. The drawn image can be return.
	*/ 
	public Ascii(ArrayList<String> palette, Image image) {
		this.palette = palette;
		this.image = image;
		this.image.convertImageToGrayscale();
		this.asciiImage = new BufferedImage(image.getWidth()*5, (image.getHeight()*6)-25, BufferedImage.TYPE_INT_RGB); // multiplier with 7 - depends on fontsize - makes the image larger according to font and font size 
		this.graphics = asciiImage.createGraphics();
		this.graphics.setPaint(new Color(255, 255, 255));
		this.graphics.fillRect(0, 0, image.getWidth()*5, (image.getHeight()*6)-25); // same type of multiplier with 6 -makes image fit according to fontsize, font and linespacing 

		String[] asciiRows = createAsciiString(this.palette, this.image);
		
		drawAsciiString(asciiRows, this.graphics);
		this.image.saveImage(this.asciiImage)
	}

	/**
		Returns an array of ascii strings from each of the rows of the image.
	*/
	private String[] createAsciiArray(ArrayList<String> palette, Image image) {
		String[] asciiRows = new String[image.getHeight()];
		String row = "";
		int count = 0;
		long gray = 0;

		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				Color color = new Color(image.getPixel(j,i)); 
				// takes greyscale values from 0-255 to between 0 - 100
				gray = Math.round((image.getPixel(j,i)& 0xFF) / 2.55);
				// inverts the values - otherwise white would be black and vice verca 
				row = row + palette.get(100 - (int) gray); 
				count++;
			}
			rows[i] = row;
			asciiRow = ""; 
		}
		return asciiRows;
	}

	/**
		Draws each of the ascii rows on to a fresh BufferedImage.
	*/
	private void drawAsciiString(String[] asciiRows, Graphics2D graphics) {
		int count = 0;
		int fontSize = 8;
		graphics.setFont(new Font("Courier New", Font.PLAIN, fontSize));
		graphics.setColor(Color.black);

		for (int i = 0; i < allRows.length; i++) {
			// multiply i with 6 for corect spacing between lines vertically on final image
			graphics.drawString(allRows[i], 0, i*6); 
		}
	}

	/**
		Returns the freshly drawn BufferedImage.
	*/
	public BufferedImage getAsciiImage() {
		return this.asciiImage;
	}
}