package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListNode;
import adt.linkedList.SingleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. Primeiro
 * implemente todos os métodos requeridos. Depois implemente dois comparadores
 * (com idéias opostas) e teste sua classe com eles. Dependendo do comparador
 * que você utilizar a lista funcionar como ascendente ou descendente, mas a
 * implemntação dos métodos é a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedDoubleLinkedListImpl<T> extends OrderedSingleLinkedListImpl<T> implements
		OrderedLinkedList<T>,DoubleLinkedList<T> {

	private DoubleLinkedListNode<T> previous;

	public OrderedDoubleLinkedListImpl() {
		// TODO Auto-generated constructor stub
		throw new UnsupportedOperationException(
				"Default constructor is not working yet!");
	}

	public OrderedDoubleLinkedListImpl(Comparator<T> comparator) {
		super(comparator);
	}
	
	/**
	 * Este método faz sentido apenas se o elemento a ser inserido pode 
	 * realmente ficar na primeira posição (devido a ordem)
	 */
	@Override
	public void insertFirst(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void removeFirst() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void removeLast() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
