package adt.bst;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.bt.BTNode;

public class StudentBSTTest {

	private BSTImpl<Integer> tree;
	private BTNode<Integer> NIL = new BTNode<Integer>();

	private void fillTree() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
	}

	@Before
	public void setUp() {
		tree = new BSTImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(-1, tree.height());

		assertEquals(NIL, tree.getRoot());

		assertArrayEquals(new Integer[] {}, tree.order());
		assertArrayEquals(new Integer[] {}, tree.preOrder());
		assertArrayEquals(new Integer[] {}, tree.postOrder());

		assertEquals(NIL, tree.search(12));
		assertEquals(NIL, tree.search(-23));
		assertEquals(NIL, tree.search(0));

		assertEquals(null, tree.minimum());
		assertEquals(null, tree.maximum());

		assertEquals(null, tree.sucessor(12));
		assertEquals(null, tree.sucessor(-23));
		assertEquals(null, tree.sucessor(0));

		assertEquals(null, tree.predecessor(12));
		assertEquals(null, tree.predecessor(-23));
		assertEquals(null, tree.predecessor(0));
	}

	@Test
	public void testMinMax() {
		tree.insert(6);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(null, tree.predecessor(-40));
		assertEquals(new Integer(-34), tree.sucessor(-40).getData());

		assertEquals(new Integer(-40), tree.predecessor(-34).getData());
		assertEquals(new Integer(0), tree.sucessor(-34).getData());

		assertEquals(new Integer(-34), tree.predecessor(0).getData());
		assertEquals(new Integer(2), tree.sucessor(0).getData());

		assertEquals(new Integer(0), tree.predecessor(2).getData());
		assertEquals(new Integer(5), tree.sucessor(2).getData());
	}

	@Test
	public void testSize() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		int size = 12;
		assertEquals(size, tree.size());

		while (!tree.isEmpty()) {
			tree.remove(tree.getRoot().getData());
			assertEquals(--size, tree.size());
		}
	}

	@Test
	public void testHeight() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] preOrder = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12, 76, 67, 232 };
		assertArrayEquals(preOrder, tree.preOrder());
		assertEquals(4, tree.height());

		tree.remove(0);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
	}

	@Test
	public void testRemove() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(6);
		order = new Integer[] { -40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(9);
		order = new Integer[] { -40, -34, 0, 2, 5, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		assertEquals(NIL, tree.search(6));
		assertEquals(NIL, tree.search(9));

	}

	@Test
	public void testSearch() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(new Integer(-40), tree.search(-40).getData());
		assertEquals(new Integer(-34), tree.search(-34).getData());
		assertEquals(NIL, tree.search(2534));
	}

	/*@Test
	public void leafsTeste() {
		fillTree();
		assertEquals(5, this.tree.contaNodes());
	}*/

	@Test
	public void grau2Teste() {
		fillTree();
		assertEquals(4, this.tree.contaNodes());
	}
	@Test
	public void testNodesPerLevel() {
		BSTImpl<Integer> newTree = new BSTImpl<>();
		
		Assert.assertArrayEquals(new int[] {}, newTree.altura());
		
		
		newTree.insert(new Integer(1));
		Assert.assertArrayEquals(new int[] {1}, newTree.altura());
		
		
		
		fillTree();
		Assert.assertEquals(4, this.tree.height());
		Assert.assertArrayEquals(new int[] {1, 2, 4, 4, 1}, this.tree.altura());
		
		this.tree.insert(new Integer(-50));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 5, 1}, this.tree.altura());
		
		this.tree.insert(new Integer(8));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 6, 1}, this.tree.altura());
		
		this.tree.insert(new Integer(-38));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 1}, this.tree.altura());
		
		this.tree.insert(new Integer(-100));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 2}, this.tree.altura());
		
		this.tree.insert(new Integer(-45));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 3}, this.tree.altura());
		
		this.tree.insert(new Integer(-39));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 4}, this.tree.altura());
		
		this.tree.insert(new Integer(-36));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 5}, this.tree.altura());
		
		this.tree.insert(new Integer(3));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 6}, this.tree.altura());
		
		this.tree.insert(new Integer(7));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 7}, this.tree.altura());
		
		this.tree.insert(new Integer(11));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 8}, this.tree.altura());
		
		
		this.tree.insert(new Integer(13));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 9}, this.tree.altura());
		
		
		this.tree.insert(new Integer(65));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 10}, this.tree.altura());
		

		this.tree.insert(new Integer(68));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 11}, this.tree.altura());
		
		this.tree.insert(new Integer(200));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 12}, this.tree.altura());
		
		this.tree.insert(new Integer(240));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 13}, this.tree.altura());
		
	}
}