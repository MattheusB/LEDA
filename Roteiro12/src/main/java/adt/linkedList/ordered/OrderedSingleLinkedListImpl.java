package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.SingleLinkedListImpl;
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
public class OrderedSingleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements OrderedLinkedList<T> {

	private Comparator<T> comparator;

	public OrderedSingleLinkedListImpl() {
		this.comparator = new ComparatorDefault();
	}

	public OrderedSingleLinkedListImpl(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public T minimum() {
		if (!this.isEmpty()) {
			T menor = head.getData();
			T[] array = toArray();
			if (this.comparator.compare(menor, array[this.size() - 1]) > 0) {
				menor = array[this.size() - 1];
			}
			return menor;
		}
		return null;

	}

	@Override
	public T maximum() {
		if (!this.isEmpty()) {
			T maior = head.getData();
			T[] array = toArray();

			if (this.comparator.compare(maior, array[this.size() - 1]) < 0) {
				maior = array[this.size() - 1];
				return maior;
			}
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> node = new SingleLinkedListNode<>(element, new SingleLinkedListNode<T>());
			if (this.isEmpty()) {
				this.head = node;
			} else {
				if (this.comparator.compare(element, head.getData()) < 0) {
					node.setNext(this.head);
					this.head = node;
				} else {
					SingleLinkedListNode<T> aux = this.head.getNext();
					SingleLinkedListNode<T> anterior = head;
					while (!aux.isNIL() && this.comparator.compare(element, aux.getData()) >= 0) {
						aux = aux.getNext();
						anterior = anterior.getNext();
					}
					node.setNext(anterior.getNext());
					anterior.setNext(node);
				}
			}
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	class ComparatorDefault<T extends Comparable> implements Comparator<T> {

		@Override
		public int compare(T o1, T o2) {
			if (o1.compareTo(o2) > 0) {
				return 1;
			} else if (o1.compareTo(o2) < 0) {
				return -1;
			} else {
				return 0;
			}

		}

	}
}
