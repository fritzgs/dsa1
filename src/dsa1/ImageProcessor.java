package dsa1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author fritz
 *
 * This class handles all the processing done to images.
 */
public class ImageProcessor {
	
	//a data set of all the nodes/pixels
	private UnionFind uf = new UnionFind();
	private ArrayList<Node<Point>> container = new ArrayList<>();
	private ArrayList<Node<Point>> whiteSets = new ArrayList<>();
	private int sheepTotal;
	
	/**
	 * Creates a new node of pixel and adds it to the arraylist.
	 * @param x
	 * @param y
	 */
	public void makeNode(int x, int y)
	{
		Point p = new Point(x, y);
		Node<Point> n = new Node<>(p);
		container.add(n);
	}
	
	/**
	 * Finds the node by using pixel co-ordination
	 * @param int x co-ordinate
	 * @param int y co-ordinate
	 * @return Node with Point(x,y).
	 */
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
	//1) get size of image and / ? to make smaller dimentions
	//2 resize it 
	/**
	 * Takes the file path and created a BufferedImage object.
	 * New smaller height and width is specified using the original height and width.
	 * A new buffered image object is created with new dimensions.
	 * 
	 * @param  String
	 * @return BufferedImage - resized version of the image input
	 * @throws IOException
	 */
	public BufferedImage resize(BufferedImage pic) throws IOException
	{
		int height = pic.getHeight()/1;
		int width = pic.getWidth()/1;
		
		Image temp = pic.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = resizedImg.createGraphics();
		g2d.drawImage(temp, 0, 0, null);
		g2d.dispose();
		
		
		return resizedImg;
	}
	
	/**
	 * Takes in a buffered image object and goes through each pixel.
	 * Each pixel point is made into a Node and the colour is converted into black or white - depending on the shade of the original color.
	 * If the shade is towards the white colour, the isWhite variable of the node is changed to true.
	 * 
	 * @param Buffered Image
	 * @return Black and white version of the image input
	 */
	public BufferedImage blackAndWhite(BufferedImage img)
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
	
	/**
	 * Goes through each pixel of the image provided 
	 * Checks the current pixel (if white) and it's white adjacent pixels.
	 * If adjacent is white - union current and adjacent
	 * @param img
	 */
	public void adjacentJoin(BufferedImage img)
	{
		for(int i = 0; i < img.getWidth()-1; i++)
			for(int j=0; j < img.getHeight()-1; j++)
			{
				Node<Point> current = findNode(i,j);
				Node<Point> right = findNode(i+1, j);
				Node<Point> left = findNode(i+1, j);
				Node<Point> up = findNode(i, j+1);
				Node<Point> down = findNode(i, j-1);
				
				if(current.getIsWhite()==true)
				{
					if(left != null && left.getIsWhite()==true)
					{
						uf.unionSize(current, left);
						left.setWasAdded();
					}
					if(right != null && right.getIsWhite()==true)
					{
						uf.unionSize(current, right);
						right.setWasAdded();
					}
					if(down != null && down.getIsWhite()==true)
					{
						uf.unionSize(current, down);
						down.setWasAdded();
					}
					if(up != null && up.getIsWhite()==true)
					{
						uf.unionSize(current, up);
						up.setWasAdded();
					}
				}
			}
		
		//Remove outliers 
		int sum = 0;
		for(Node<Point> n : container)
		{
			if(n.getParent().equals(n) && n.getIsWhite()==true)
			{
				whiteSets.add(n);
			}
		}
		
		for(Node<Point> nwhite : whiteSets)
		{
			sum += nwhite.getSize();	
		}
		
		
		int mean = sum/whiteSets.size();
		
		whiteSets.removeIf(s -> s.getSize() < mean);

		
		//trying out -- setting the count of cluster to 1 initially. the size of the white set / estiamte of 1 sheep will be the new amount.
		int sizeTest = mean * 3;
		sheepTotal = 0;
		for(Node<Point> w : whiteSets)
		{
			int sheeps = Math.round(w.getSize() / sizeTest);
			w.setSheep(sheeps);
			sheepTotal += w.getSheepCount();
		}
		//------
		
		System.out.println(whiteSets.size());
		
	}
	
	public int getSheepTotal()
	{
		return sheepTotal;
	}
	
	public ArrayList<Node<Point>> getPixelArray()
	{
		return container;
	}
	
	public ArrayList<Node<Point>> getWhiteSets()
	{
		return whiteSets;
	}

}
