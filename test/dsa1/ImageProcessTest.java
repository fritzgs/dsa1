package dsa1;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author fritz
 *
 */
class ImageProcessTest {

	String currentDir = System.getProperty("user.dir");
	
//	String path = currentDir+"/index.jpeg";
	String path = currentDir+"/cricketsheep_shop.jpg";

	
	ImageProcessor imgpro = new ImageProcessor();
	
	@Test
	void testResize() throws IOException {
		
		BufferedImage img = ImageIO.read(new File(path));
		int oldHeight = img.getHeight();
	
		BufferedImage imgresize = imgpro.resize(img);
		
		
		int newHeight = imgresize.getHeight();

		
		assertTrue(oldHeight > newHeight);	
	}
	
	@Test
	void testBlackAndWhite() throws IOException
	{
		BufferedImage img = ImageIO.read(new File(path));
		BufferedImage imgresize = imgpro.resize(img);
		imgpro.blackAndWhite(imgresize);
		Color origin = new Color(imgresize.getRGB(0, 0));
		if(origin.getBlue()==0 && origin.getGreen()==0 && origin.getRed() ==0)
			assertEquals(false, imgpro.findNode(0, 0).getIsWhite());
		
	}
	
	@Test
	void testAdjacentJoin() throws IOException
	{
		BufferedImage img = ImageIO.read(new File(path));
		BufferedImage imgresize = imgpro.resize(img);
		BufferedImage bnw = imgpro.blackAndWhite(imgresize);
		imgpro.adjacentJoin(bnw);
//		for(Node<Point> n: imgpro.getWhiteSets())
//			System.out.println(n.getSize());
		
		assertEquals(22, imgpro.getWhiteSets().size());

		
	}
	
}
