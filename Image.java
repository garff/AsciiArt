import javax.imageio.ImageIO;
import java.io.File;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon; 
import java.io.IOException;

public class Image {

	private String imageUrl;
	private BufferedImage image;
	private Graphics2D graphics;

	public Image(int x, int y) {
		this.image = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		this.graphics = image.createGraphics();
		graphics.setPaint(new Color(255, 255, 255));
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
	}
 
	public Image(String imageUrl) throws IOException {
		this.imageUrl = imageUrl;
		try {
			this.image = ImageIO.read(new File(this.imageUrl));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failure: Something went wrong with the upload of the file.\nRestart the program.");
		}
	}

	public void convertImageToGrayscale() {
		ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		ColorConvertOp colorConverter = new ColorConvertOp(colorSpace, null);
		this.image = colorConverter.filter(this.image, null);
	}

	public void displayImage(BufferedImage image) {
		JFrame frame = new JFrame();
		ImageIcon icon = new ImageIcon(image);
		JLabel label = new JLabel(icon);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void saveImage(BufferedImage image) {
		try {
			File outputfile = new File("image.jpg");
			ImageIO.write(image, "jpg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failure: Something went wrong with saving the file.");
		}
	}

	public int getWidth() {
		return this.image.getWidth();
	}

	public int getHeight() {
		return this.image.getHeight();
	}

	public int getPixel(int x, int y) {
		return this.image.getRGB(x, y);
	}

	public BufferedImage getImage() {
		return this.image;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public Graphics2D getGraphics() {
		return this.graphics;
	}

}