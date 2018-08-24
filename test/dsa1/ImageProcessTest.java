package dsa1;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class ImageProcessTest {

	String currentDir = System.getProperty("user.dir");
	String path = currentDir+"/cricketsheep_shop.jpg";

	
	ImageProcessor imgpro = new ImageProcessor();
	
	@Test
	void testResize() throws IOException {
		
		BufferedImage img = ImageIO.read(new File(path));
		int oldHeight = img.getHeight();
	
		BufferedImage imgresize = imgpro.resize(path);
		
		
		int newHeight = imgresize.getHeight();

		
		assertTrue(oldHeight > newHeight);	
	}
	
	@Test
	void testBlackAndWhite() throws IOException
	{
		BufferedImage imgresize = imgpro.resize(path);
		imgpro.blackAndWhite(imgresize);
		Color origin = new Color(imgresize.getRGB(0, 0));
		if(origin.getBlue()==0 && origin.getGreen()==0 && origin.getRed() ==0)
			assertEquals(false, imgpro.findNode(0, 0).getIsWhite());
		
	}
	
}