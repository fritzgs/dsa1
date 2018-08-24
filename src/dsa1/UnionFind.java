package dsa1;


public class UnionFind {
	public Node<?> find(Node<?> node)
	{
		//if the parent is the node itself, it is the root.
		if(node.getParent().equals(node))
		{
			return node.getParent();
		}
		else
		{
			node.setParent(find(node.getParent()));
			return find(node.getParent());
		}
	}
	
	public void quickUnion(Node<?> a, Node<?> b)
	{
		find(b).setParent(find(a).getParent());
		a.setSize(a.getSize() + 1);
	}
	
	public void unionSize(Node<?> a, Node<?> b)
	{
		Node<?> rootA = find(a);
		Node<?> rootB = find(b);
		
		if(rootA.getSize() > rootB.getSize() || rootA.getSize() == rootB.getSize())
		{
			rootB.setParent(rootA);
			rootA.setSize(rootA.getSize()+1);
		}
		else
		{
			rootA.setParent(rootB);
			rootB.setSize(rootB.getSize()+1);
		}
	}

}
