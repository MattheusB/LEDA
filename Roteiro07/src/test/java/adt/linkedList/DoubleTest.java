package adt.linkedList;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DoubleTest {

	private DoubleLinkedList<Integer> lista1;
	private DoubleLinkedList<Integer> lista2;
	private DoubleLinkedList<Integer> lista3;

	private DoubleLinkedList<Integer> minhaListaVazia;
	private DoubleLinkedList<Integer> minhaListaUnitaria;
	private DoubleLinkedList<Integer> minhaListaComDoisElementos;
	private DoubleLinkedList<Integer> minhaListaComTresElementos;
	private DoubleLinkedList<Integer> minhaListaComElementosRepetidos;
	private DoubleLinkedList<Integer> minhaListaElementosIguais;

	@Before
	public void setUp() throws Exception {
		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(1);
		lista1.insert(2);
		lista1.insert(3);

		// Lista com 1 elemento.
		lista3.insert(1);

		minhaListaElementosIguais.insert(10);
		minhaListaElementosIguais.insert(10);
		minhaListaElementosIguais.insert(10);

		minhaListaComDoisElementos.insert(50);
		minhaListaComDoisElementos.insert(30);

		minhaListaComTresElementos.insert(14);
		minhaListaComTresElementos.insert(8);

		minhaListaComElementosRepetidos.insert(2);
		minhaListaComElementosRepetidos.insert(2);
		minhaListaComElementosRepetidos.insert(2);
		minhaListaComElementosRepetidos.insert(2);
	}

	private void getImplementations() {
		lista1 = new DoubleLinkedListImpl();
		lista2 = new DoubleLinkedListImpl();
		lista3 = new DoubleLinkedListImpl();

		minhaListaVazia = new DoubleLinkedListImpl<Integer>();
		minhaListaUnitaria = new DoubleLinkedListImpl<Integer>();
		minhaListaComDoisElementos = new DoubleLinkedListImpl<Integer>();
		minhaListaComTresElementos = new DoubleLinkedListImpl<Integer>();
		minhaListaComElementosRepetidos = new DoubleLinkedListImpl<Integer>();
		minhaListaElementosIguais = new DoubleLinkedListImpl<Integer>();
	}

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(lista2.isEmpty());

		Assert.assertFalse(lista3.isEmpty());

		lista3.removeLast();
		Assert.assertTrue(lista3.isEmpty());

		lista3.insert(1);
		Assert.assertFalse(lista3.isEmpty());
		lista3.removeFirst();
		Assert.assertTrue(lista3.isEmpty());
		lista3.insert(1);
		Assert.assertFalse(lista3.isEmpty());
		lista3.remove(1);
		Assert.assertTrue(lista3.isEmpty());
	}

	@Test
	public void testIsEmpty_2() {
		// Assert.assertFalse(minhaListaVazia.isEmpty()); // deve falhar

		Assert.assertTrue(minhaListaVazia.isEmpty());

		lista3.removeLast();
		Assert.assertTrue(lista3.isEmpty());

		// Assert.fail("Not implemented!");
	}

	@Test
	public void testSize() {
		Assert.assertTrue(lista1.size() == 3);
		Assert.assertTrue(lista2.size() == 0);
		Assert.assertTrue(lista3.size() == 1);

		lista3.removeLast();
		Assert.assertTrue(lista3.size() == 0);

		lista3.insert(1);
		lista3.removeFirst();
		Assert.assertTrue(lista3.size() == 0);

		lista3.insert(1);
		lista3.remove(1);
		Assert.assertTrue(lista3.size() == 0);

		lista3.insertFirst(1);
		Assert.assertTrue(lista3.size() == 1);
	}

	@Test
	public void testSize_2() {
		// Assert.assertEquals(2, lista1.size()); // deve dar falha

		// apos dar falha, testar a linha abaixo (deve passar nos testes)
		Assert.assertEquals(2, minhaListaComTresElementos.size());

		// Assert.fail("Not implemented!");
	}

	@Test
	public void testSearch() {
		Assert.assertTrue(lista1.search(1) == 1);
		Assert.assertTrue(lista1.search(2) == 2);
		Assert.assertTrue(lista1.search(3) == 3);
		Assert.assertTrue(lista1.search(4) == null);

		lista1.removeLast();
		Assert.assertTrue(lista1.search(3) == null);

		lista1.removeFirst();
		Assert.assertTrue(lista1.search(1) == null);

		Assert.assertTrue(lista2.search(1) == null);

		Assert.assertTrue(lista3.search(1) == 1);
	}

	@Test
	public void testSearch_2() {
		Assert.assertEquals(10, (int) minhaListaElementosIguais.search(10));
		minhaListaElementosIguais.remove(10);
		Assert.assertEquals(10, (int) minhaListaElementosIguais.search(10));
		minhaListaElementosIguais.remove(10);
		Assert.assertEquals(10, (int) minhaListaElementosIguais.search(10));
		minhaListaElementosIguais.remove(10);
		Assert.assertNull(minhaListaElementosIguais.search(10));

		// Assert.fail("Not implemented!");
	}

	@Test
	public void testInsert() {
		Assert.assertTrue(lista2.size() == 0);

		lista2.insert(1);
		Assert.assertTrue(lista2.size() == 1);

		lista2.remove(1);
		Assert.assertTrue(lista2.size() == 0);

		lista2.insert(null);
		Assert.assertTrue(lista2.size() == 0);

		lista2.insert(1);
		Assert.assertTrue(lista2.size() == 1);

		// #--------------------------------------------

		DoubleLinkedListImpl<Integer> list = new DoubleLinkedListImpl<Integer>();

		Assert.assertTrue(list.size() == 0);

		list.insert(1);
		Assert.assertTrue(list.getHead().getData() == 1);
		Assert.assertTrue(list.last.getData() == 1);

		list.removeFirst();
		Assert.assertTrue(list.size() == 0);

		list.insert(0);
		Assert.assertTrue(list.getHead().getData() == 0);
		Assert.assertTrue(list.last.getData() == 0);

		list.insert(1);
		Assert.assertTrue(list.last.getData() == 1);

		list.insert(2);
		Assert.assertTrue(list.last.getData() == 2);

		Assert.assertTrue(list.last.next.getData() == null);
		Assert.assertTrue(list.last.previous.getData() == 1);

	}

	@Test
	public void testInsert_2() {
		Assert.assertNull(minhaListaVazia.search(7));
		minhaListaVazia.insert(7);
		Assert.assertEquals(7, (int) minhaListaVazia.search(7));

		// Assert.fail("Not implemented!");
	}

	@Test
	public void testRemove() {
		Assert.assertTrue(lista1.size() == 3);

		lista1.remove(1);
		Assert.assertTrue(lista1.size() == 2);

		lista1.remove(10);
		Assert.assertTrue(lista1.size() == 2);

		lista1.remove(null);
		Assert.assertTrue(lista1.size() == 2);

		lista1.remove(2);
		Assert.assertTrue(lista1.size() == 1);

		lista1.remove(3);
		Assert.assertTrue(lista1.size() == 0);

		// #---------------------------------------------------

		DoubleLinkedListImpl<Integer> list = new DoubleLinkedListImpl<Integer>();
		Assert.assertTrue(list.size() == 0);

		list.insert(0);
		list.insert(1);
		list.insert(2);
		list.insert(3);

		list.remove(2);
		System.out.println(list.head.next.next.getData());
		Assert.assertTrue(list.head.next.next.getData() == 3);
		Assert.assertTrue(list.last.previous.getData() == 1);

		list.remove(0);
		Assert.assertTrue(list.head.getData() == 1);

		list.remove(3);
		Assert.assertTrue(list.last.getData() == 1);
		Assert.assertTrue(list.head.getData() == 1);
		Assert.assertTrue(list.last.previous.getData() == null);
		Assert.assertTrue(list.head.next.getData() == null);

		list.remove(1);
		Assert.assertTrue(list.last.getData() == null);
		Assert.assertTrue(list.head.getData() == null);

		// #---------------------------------------------------

		list = new DoubleLinkedListImpl<Integer>();
		list.insert(1);
		list.insert(2);
		list.insert(3);

		list.remove(1);
		Assert.assertTrue(list.getHead().getData() == 2);

		list.insertFirst(1);

		list.remove(3);
		Assert.assertTrue(list.head.getData() == 1);
		Assert.assertTrue(list.last.getData() == 2);

		list.insert(3);

		list.remove(2);
		Assert.assertTrue(list.head.getData() == 1);
		Assert.assertTrue(list.last.getData() == 3);

	}

	@Test
	public void testRemove_2() {

		lista3.remove(1);
		Assert.assertTrue(lista3.search(10) == null);

		// Assert.fail("Not implemented!");
	}

	@Test
	public void testToArray() {
		Assert.assertArrayEquals(new Integer[] { 1, 2, 3 }, lista1.toArray());
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		Assert.assertArrayEquals(new Integer[] { 1 }, lista3.toArray());

		lista2.insert(1);
		Assert.assertArrayEquals(new Integer[] { 1 }, lista2.toArray());

		lista2.remove(1);
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());

		lista3.removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());

		lista3.insertFirst(1);
		Assert.assertArrayEquals(new Integer[] { 1 }, lista3.toArray());
		lista3.removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());
	}

	// M�todos de DoubleLinkedList

	@Test
	public void testInsertFirst() {

		Assert.assertTrue(lista2.size() == 0);

		lista2.insertFirst(1);
		Assert.assertTrue(lista2.size() == 1);

		lista2.remove(1);
		Assert.assertTrue(lista2.size() == 0);

		lista2.insertFirst(null);
		Assert.assertTrue(lista2.size() == 0);

		lista2.insertFirst(1);
		Assert.assertTrue(lista2.size() == 1);

		lista2.insertFirst(0);
		Assert.assertTrue(lista2.size() == 2);

		// #---------------------------------------------------

		DoubleLinkedListImpl<Integer> list = new DoubleLinkedListImpl<Integer>();

		Assert.assertTrue(list.size() == 0);

		list.insertFirst(0);
		Assert.assertTrue(list.getHead().getData() == 0);

		list.insertFirst(1);
		Assert.assertTrue(list.last.getData() == 0);
		Assert.assertTrue(list.getHead().getData() == 1);

		list.insertFirst(2);
		Assert.assertTrue(list.last.getData() == 0);
		Assert.assertTrue(list.getHead().getData() == 2);

	}

	@Test
	public void testInsertFirst_2() {
		minhaListaComTresElementos.insertFirst(9);

		// deve falhar:
		// Assert.assertNotEquals("[9, 14, 8]",
		// minhaListaComTresElementos.toArray().toString());

		Assert.assertEquals("[9, 14, 8]", Arrays.toString(minhaListaComTresElementos.toArray()));

		// Assert.fail("Not implemented!");
	}

	@Test
	public void testRemoveFirst() {
		DoubleLinkedListImpl<Integer> list = new DoubleLinkedListImpl<Integer>();

		Assert.assertTrue(list.size() == 0);

		list.insertFirst(0);
		Assert.assertTrue(list.getHead().getData() == 0);

		list.insertFirst(1);
		Assert.assertTrue(list.last.getData() == 0);
		Assert.assertTrue(list.getHead().getData() == 1);

		list.insertFirst(2);
		Assert.assertTrue(list.last.getData() == 0);
		Assert.assertTrue(list.getHead().getData() == 2);

		list.removeFirst();
		Assert.assertTrue(list.head.getData() == 1);
		Assert.assertTrue(list.head.next.getData() == 0);
		Assert.assertTrue(list.last.previous.previous.getData() == null);

		Assert.assertTrue(list.size() == 2);

		list.removeFirst();
		Assert.assertTrue(list.head.getData() == 0);
		Assert.assertTrue(list.head.next.getData() == null);
		Assert.assertTrue(list.last.previous.getData() == null);

		list.removeLast();
		Assert.assertTrue(list.last.getData() == null);
		Assert.assertTrue(list.head.getData() == null);

	}

	@Test
	public void testRemoveFirst_2() {

		minhaListaComTresElementos.removeFirst();

		// deve falhar
		// Assert.assertEquals(14, (int)minhaListaComTresElementos.search(14));

		Assert.assertEquals("[8]", Arrays.toString(minhaListaComTresElementos.toArray()));
		// Assert.fail("Not implemented!");
	}

	@Test
	public void testRemoveLast() {

		DoubleLinkedListImpl<Integer> list = new DoubleLinkedListImpl<Integer>();

		Assert.assertTrue(list.size() == 0);

		list.insertFirst(0);
		Assert.assertTrue(list.getHead().getData() == 0);

		list.insertFirst(1);
		Assert.assertTrue(list.last.getData() == 0);
		Assert.assertTrue(list.getHead().getData() == 1);

		list.insertFirst(2);
		Assert.assertTrue(list.last.getData() == 0);
		Assert.assertTrue(list.getHead().getData() == 2);

		list.removeLast();
		Assert.assertTrue(list.last.getData() == 1);
		Assert.assertTrue(list.last.next.getData() == null);
		Assert.assertTrue(list.last.previous.getData() == 2);

		Assert.assertTrue(list.size() == 2);

		list.removeLast();
		Assert.assertTrue(list.last.getData() == 2);
		Assert.assertTrue(list.last.next.getData() == null);
		Assert.assertTrue(list.last.previous.getData() == null);

		list.removeLast();
		Assert.assertTrue(list.last.getData() == null);
		Assert.assertTrue(list.head.getData() == null);

	}

	@Test
	public void testRemoveLast_2() {

		minhaListaComElementosRepetidos.removeLast();
		Assert.assertTrue(minhaListaComElementosRepetidos.search(2) != null);

		lista3.removeLast();
		Assert.assertTrue(lista3.isEmpty());

		// Assert.fail("Not implemented!");
	}

}