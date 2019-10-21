import java.io.IOException;
import java.awt.image.BufferedImage;

public class Main {

	/**
	*	Ascii art generator. Given an image url displays the same image on screen 
	*	translated into the most astounding art one has ever seen.
	*	
	*	For fun and absolutely no profit.
	*
	*	@author Mads Garff 
	*/

	public static void main(String[] args) {

		try {
			Image image = new Image(args[0]);
			Palette palette = new Palette();
			Ascii ascii = new Ascii(palette.getPalette(), image);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failure: Something went wrong with the upload of the file.\nRestart the program.");			
		}
		
	}	

}