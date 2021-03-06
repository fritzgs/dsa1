package dsa1;
/**
 * 
 * @author fritz
 *
 * @param <T> Generic
 */
public class Node<T> {
	private Node<?> parent;
	private T pixel;
	private int size;
	private boolean wasAdded;
	private boolean isWhite;
	private int sheep;
	
	/**
	 * Constructor of the node
	 * @param pixel/Point object
	 */
	public Node(T pixel)
	{
		this.pixel = pixel;
		this.parent = this;
		this.size = 1;
		this.wasAdded = false;
		this.isWhite = false;
		this.sheep = 1;
	}
	
	public int getSheepCount()
	{
		return sheep;
	}
	
	public void setSheep(int i)
	{
		this.sheep =i;
	}
	
	/**
	 * Returns the boolean value of isWhite.
	 * If the pixel color of the node is white: returns true.
	 * Else, false.
	 * @return boolean
	 */
	public boolean getIsWhite()
	{
		return isWhite;
	}
	
	/**
	 * Returns the object that was passed onto the node.
	 * @return T
	 */
	public T getPixel()
	{
		return pixel;
	}
	
	/**
	 * Returns the parent of the node. At creation, each node's parent is itself.
	 * @return Node
	 */
	public Node<?> getParent()
	{
		return parent;
	}
	
	/**
	 * Returns the size of the Node.
	 * Size increases as other Nodes refer to this node.
	 * @return int
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getWasAdded()
	{
		return wasAdded;
	}
	
	/**
	 * Set the size of the node.
	 * @param int
	 */
	public void setSize(int s)
	{
		this.size = s;
	}
	
	/**
	 * 
	 * @param b
	 */
	public void setWasAdded()
	{
		this.wasAdded = true;
	}
	
	/**
	 * Set the parent of this node as another node.
	 * @param Node
	 */
	public void setParent(Node<?> n)
	{
		this.parent = n;
	}
	
	/**
	 * Change the boolean of isWhite to true if the color of the pixel is white.
	 */
	public void setIsWhite()
	{
		this.isWhite = true;
	}
	
	
	@Override
	public String toString()
	{
		return pixel.toString();
	}

}
