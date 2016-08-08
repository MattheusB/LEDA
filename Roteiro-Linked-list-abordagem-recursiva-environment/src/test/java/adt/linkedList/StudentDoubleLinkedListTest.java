package adt.linkedList;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest {

	private RecursiveDoubleLinkedListImpl<Integer> lista1;
	private RecursiveDoubleLinkedListImpl<Integer> lista2;
	private RecursiveDoubleLinkedListImpl<Integer> lista3;

	private RecursiveDoubleLinkedListImpl<Integer> minhaListaVazia;
	private RecursiveDoubleLinkedListImpl<Integer> minhaListaUnitaria;
	private RecursiveDoubleLinkedListImpl<Integer> minhaListaComDoisElementos;
	private RecursiveDoubleLinkedListImpl<Integer> minhaListaComTresElementos;
	private RecursiveDoubleLinkedListImpl<Integer> minhaListaComElementosRepetidos;
	private RecursiveDoubleLinkedListImpl<Integer> minhaListaElementosIguais;

	public final Integer ZERO = 0;
	public final Integer UM = 1;
	public final Integer DOIS = 2;
	public final Integer TRES = 3;
	public final Integer QUATRO = 4;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(1);
		lista1.insert(2);
		lista1.insert(3);
		lista1.insert(4);

		// Lista com 1 elemento.
		lista3.insert(1);

		minhaListaUnitaria.insert(1);

		minhaListaComDoisElementos.insert(1);
		minhaListaComDoisElementos.insert(2);

		minhaListaComTresElementos.insert(1);
		minhaListaComTresElementos.insert(2);
		minhaListaComTresElementos.insert(3);

		minhaListaComElementosRepetidos.insert(1);
		minhaListaComElementosRepetidos.insert(1);
		minhaListaComElementosRepetidos.insert(2);

		minhaListaElementosIguais.insert(1);
		minhaListaElementosIguais.insert(1);
		minhaListaElementosIguais.insert(1);
		minhaListaElementosIguais.insert(1);

	}

	private void getImplementations() {
		lista1 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista2 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista3 = new RecursiveDoubleLinkedListImpl<Integer>();

		minhaListaVazia = new RecursiveDoubleLinkedListImpl<Integer>();
		minhaListaUnitaria = new RecursiveDoubleLinkedListImpl<Integer>();
		minhaListaComDoisElementos = new RecursiveDoubleLinkedListImpl<Integer>();
		minhaListaComTresElementos = new RecursiveDoubleLinkedListImpl<Integer>();
		minhaListaComElementosRepetidos = new RecursiveDoubleLinkedListImpl<Integer>();
		minhaListaElementosIguais = new RecursiveDoubleLinkedListImpl<Integer>();
	}

	@Test
	public void testIsEmpty() {
		assertFalse(minhaListaUnitaria.isEmpty());

		assertTrue(minhaListaVazia.isEmpty());

		minhaListaVazia.insert(1);
		assertFalse(minhaListaVazia.isEmpty());

		assertTrue(lista2.isEmpty());
		lista2.insertFirst(1);

		Assert.assertFalse(lista2.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertTrue(lista2.size() == 0);
		Assert.assertTrue(minhaListaVazia.size() == 0);

		lista2.insert(1);
		Assert.assertTrue(lista2.size() == 1);

		minhaListaVazia.insertFirst(1);
		Assert.assertTrue(minhaListaVazia.size() == 1);

		Assert.assertTrue(minhaListaUnitaria.size() == 1);
		Assert.assertTrue(minhaListaComDoisElementos.size() == 2);
		Assert.assertTrue(minhaListaComTresElementos.size() == 3);

		Assert.assertTrue(lista3.size() == 1);

		lista3.removeFirst();
		Assert.assertTrue(lista3.size() == 0);

		Assert.assertTrue(lista2.size() == 1);

		lista2.removeLast();
		Assert.assertTrue(lista2.size() == 0);

	}

	@Test
	public void testSearch() {

		assertTrue(minhaListaVazia.search(1) == null);

		assertTrue(minhaListaUnitaria.search(2) == null);

		assertEquals(minhaListaUnitaria.search(1), UM);

		assertEquals(minhaListaComDoisElementos.search(2), DOIS);

		minhaListaComDoisElementos.remove(2);
		assertTrue(minhaListaComDoisElementos.search(2) == null);

		assertEquals(minhaListaComTresElementos.search(3), TRES);

		minhaListaComTresElementos.removeLast();
		assertTrue(minhaListaComTresElementos.search(1) == UM);
		assertTrue(minhaListaComTresElementos.search(2) == DOIS);
		assertTrue(minhaListaComTresElementos.search(3) == null);
	}

	@Test
	public void testInsert() {
		assertTrue(minhaListaVazia.size() == ZERO);

		minhaListaVazia.insert(1);
		assertArrayEquals(new Integer[] { 1 }, minhaListaVazia.toArray());

		minhaListaVazia.insert(2);
		assertArrayEquals(new Integer[] { 1, 2 }, minhaListaVazia.toArray());

		minhaListaUnitaria.insert(null);
		assertArrayEquals(new Integer[] { 1 }, minhaListaUnitaria.toArray());

		// #--------------------------------------------

		Assert.assertTrue(lista2.size() == 0);

		lista2.insert(1);
		assertTrue(lista2.getData() == 1);
		assertTrue(lista2.getNext().getData() == null);

		lista2.removeFirst();
		assertTrue(lista2.size() == 0);

		lista2.insert(0);
		assertEquals(lista2.getData(), ZERO);
		assertEquals(lista2.getNext().getData(), null);

		lista2.insert(1);
		assertEquals(lista2.getNext().getData(), UM);

		lista2.insert(2);
		assertEquals(lista2.getNext().getNext().getData(), DOIS);

		assertNull(lista2.getNext().getNext().getNext().getData());
		assertEquals(((RecursiveDoubleLinkedListImpl<Integer>) lista2.getNext()).getPrevious().getData(), ZERO);

	}

	@Test
	public void testRemove() {
		assertTrue(lista1.size() == 4);

		lista1.remove(1);
		assertTrue(lista1.size() == 3);

		lista1.remove(10);
		assertTrue(lista1.size() == 3);

		lista1.remove(null);
		assertTrue(lista1.size() == 3);

		lista1.remove(2);
		assertTrue(lista1.size() == 2);

		lista1.remove(3);
		assertTrue(lista1.size() == 1);

		lista1.remove(3);
		assertTrue(lista1.size() == 1);

		lista1.remove(4);
		assertTrue(lista1.size() == 0);
	}

	@Test
	public void testRemove_2() {
		assertTrue(lista1.size() == 4);

		lista1.remove(2);
		assertEquals(lista1.next.next.getData(), QUATRO);

		lista1.remove(1);
		assertEquals(lista1.getData(), TRES);

		lista1.remove(3);
		assertEquals(lista1.getData(), QUATRO);
		assertNull(lista1.getNext().getData());

		lista1.remove(4);
		assertNull(lista1.getData());

		// #---------------------------------------------------

		lista2.insert(1);
		lista2.insert(2);
		lista2.insert(3);

		lista2.remove(1);
		assertEquals(lista2.getData(), DOIS);

		lista2.insertFirst(1);

		lista2.remove(3);
		assertEquals(lista2.getData(), UM);
		assertEquals(lista2.getNext().getData(), DOIS);

		lista2.insert(3);

		lista2.remove(2);
		assertEquals(lista2.getData(), UM);
		assertEquals(lista2.getNext().getData(), TRES);
	}

	@Test
	public void testToArray() {
		assertArrayEquals(new Integer[] { 1, 2, 3, 4 }, lista1.toArray());
		assertArrayEquals(new Integer[] {}, lista2.toArray());
		assertArrayEquals(new Integer[] { 1 }, lista3.toArray());

		lista2.insert(1);
		assertArrayEquals(new Integer[] { 1 }, lista2.toArray());

		lista2.remove(1);
		assertArrayEquals(new Integer[] {}, lista2.toArray());

		lista3.removeFirst();
		assertArrayEquals(new Integer[] {}, lista3.toArray());

		lista3.insertFirst(1);
		assertArrayEquals(new Integer[] { 1 }, lista3.toArray());
		lista3.removeLast();
		assertArrayEquals(new Integer[] {}, lista3.toArray());
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		assertTrue(lista2.size() == 0);

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
	}

	@Test
	public void testInsertFirst_2() {

		assertTrue(minhaListaVazia.size() == 0);

		minhaListaVazia.insertFirst(0);
		assertEquals(minhaListaVazia.getData(), ZERO);

		minhaListaVazia.insertFirst(1);
		assertEquals(minhaListaVazia.next.getData(), ZERO);
		assertEquals(minhaListaVazia.getData(), UM);

		minhaListaVazia.insertFirst(2);
		assertEquals(minhaListaVazia.next.next.getData(), ZERO);
		assertEquals(minhaListaVazia.getData(), DOIS);

	}

	@Test
	public void testRemoveFirst() {

		assertTrue(lista2.size() == 0);

		lista2.insertFirst(0);
		assertEquals(lista2.getData(), ZERO);

		lista2.insertFirst(1);
		assertEquals(lista2.next.getData(), ZERO);
		assertEquals(lista2.getData(), UM);

		lista2.insertFirst(2);
		assertEquals(lista2.next.next.getData(), ZERO);
		assertEquals(lista2.getData(), DOIS);

		lista2.removeFirst();
		assertEquals(lista2.getData(), UM);
		assertEquals(lista2.next.getData(), ZERO);
		assertNull(lista2.previous.getData());
		assertNull(lista2.getNext().getNext().getData());

		assertTrue(lista2.size() == DOIS);

		lista2.removeFirst();
		assertEquals(lista2.getData(), ZERO);
		assertNull(lista2.next.getData());
		assertNull(lista2.previous.getData());

		lista2.removeLast();
		assertNull(lista2.getData());
	}

	@Test
	public void testRemoveLast() {

		lista2.insertFirst(2);
		assertEquals(lista2.getData(), DOIS);
		assertNull(lista2.getPrevious().getData());

		lista2.insertFirst(1);
		assertEquals(lista2.getData(), UM);
		assertEquals(lista2.getNext().getData(), DOIS);

		lista2.insertFirst(0);
		assertEquals(lista2.getData(), ZERO);
		assertEquals(lista2.getNext().getData(), UM);

		lista2.removeLast();
		assertEquals(lista2.getData(), ZERO);
		assertEquals(lista2.getNext().getData(), UM);
	}

}