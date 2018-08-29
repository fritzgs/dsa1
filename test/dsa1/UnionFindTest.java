package dsa1;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author fritz
 *
 */
class UnionFindTest {
	
	UnionFind uf = new UnionFind();
	
	Point p1 = new Point(0,0);
	Point p2 = new Point(0,1);
	Point p3 = new Point(0,2);
	Point p4 = new Point(0,3);
	Node<Point> n1 = new Node<>(p1);
	Node<Point> n2 = new Node<>(p2);
	Node<Point> n3 = new Node<>(p3);
	Node<Point> n4 = new Node<>(p4);

	
	
	
	@Test
	void testFind() {
		assertEquals(n1, n1.getParent());
		assertFalse(n2.getParent().equals(null));
	}
	
	@Test
	void testQuick()
	{
		uf.quickUnion(n1, n2);
		assertEquals(n1, n2.getParent());
	}
	
	@Test
	void testUnionSize()
	{
		uf.unionSize(n1, n2);
		assertEquals(n1, n2.getParent());
		uf.unionSize(n3, n2);
		assertEquals(n1, n3.getParent());
		uf.unionSize(n4, n3);
		assertEquals(n1, n4.getParent());
		assertEquals(4, n1.getSize());
	}

}
