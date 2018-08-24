package dsa1;

public class Node<T> {
	private Node<?> parent;
	private T pixel;
	private int size;
	private boolean wasAdded;
	private boolean isWhite;
	
	public Node(T pixel)
	{
		this.pixel = pixel;
		this.parent = this;
		this.size = 1;
		this.wasAdded = false;
		this.isWhite = false;
	}
	
	public boolean getIsWhite()
	{
		return isWhite;
	}
	
	public T getPixel()
	{
		return pixel;
	}
	
	public Node<?> getParent()
	{
		return parent;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public boolean getWasAdded()
	{
		return wasAdded;
	}
	
	public void setSize(int s)
	{
		this.size = s;
	}
	
	public void setWasAdded(boolean b)
	{
		this.wasAdded = b;
	}
	
	public void setParent(Node<?> n)
	{
		this.parent = n;
	}
	
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
