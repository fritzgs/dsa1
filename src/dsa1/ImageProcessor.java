package dsa1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageProcessor {
	
	private ArrayList<Node<Point>> container = new ArrayList<>();
	
	public void makeNode(int x, int y)
	{
		Point p = new Point(x, y);
		Node<Point> n = new Node<>(p);
		container.add(n);
	}
	
	public Node<Point> findNode(int x, int y)
	{
		Node<Point> res = null; 
		for(Node<Point> arr : container)
		{
			if(arr.getPixel().getX() == x && arr.getPixel().getY() == y)
			{
				res = arr;
			}
		}
		
		return res;
	}
	
	//make the image smaller
	//1) get size of image and * ? to make smaller dimentions
	//2 resize it 
	public BufferedImage resize(String file) throws IOException
	{
		BufferedImage img = ImageIO.read(new File (file));
		int height = img.getHeight() / 5;
		int width = img.getWidth() / 5;
		
		Image temp = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = resizedImg.createGraphics();
		g2d.drawImage(temp, 0, 0, null);
		g2d.dispose();
		
		
		return resizedImg;
	}
	
	//convert pixels to black and white
	public Image blackAndWhite(BufferedImage img)
	{
		for(int i = 0; i < img.getWidth()-1; i++)
			for(int j=0; j < img.getHeight()-1; j++)
			{
				makeNode(i, j);
				Color color = new Color(img.getRGB(i, j));
				int alpha = color.getAlpha();
				
				if(color.getRed() < 220 && color.getGreen() < 220 && color.getBlue() < 200)
				{
					Color black = new Color(0,0,0, alpha);
					img.setRGB(i, j, black.getRGB());
				}
				
				Color newColor = new Color(img.getRGB(i, j));
				if(newColor.getRed() != 0 && newColor.getGreen() != 0 && newColor.getBlue() != 0)
				{
					Color white = new Color(255,255,255, alpha);
					img.setRGB(i, j, white.getRGB());
					findNode(i, j).setIsWhite();
				}
			}
		
		return img;
	}

}
