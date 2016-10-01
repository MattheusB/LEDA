package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. Primeiro
 * implemente todos os mÃ©todos requeridos. Depois implemente dois
 * comparadores (com idÃ©ias opostas) e teste sua classe com eles. Dependendo
 * do comparador que vocÃª utilizar a lista funcionar como ascendente ou
 * descendente, mas a implemntaÃ§Ã£o dos mÃ©todos Ã© a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedDoubleLinkedListImpl<T> extends OrderedSingleLinkedListImpl<T>
		implements OrderedLinkedList<T>, DoubleLinkedList<T> {

	private DoubleLinkedListNode<T> last;

	public OrderedDoubleLinkedListImpl() {

	}

	public OrderedDoubleLinkedListImpl(Comparator<T> comparator) {
		super(comparator);
	}

	/**
	 * Este mÃ©todo faz sentido apenas se o elemento a ser inserido pode
	 * realmente ficar na primeira posiÃ§Ã£o (devido a ordem)
	 */
	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(),
					new DoubleLinkedListNode<>());
			if (isEmpty()) {

				this.head = node;
				this.last = node;
			} else if (this.getComparator().compare(this.head.getData(), element) >= 0) {
				node.setNext(this.head);
				((DoubleLinkedListNode<T>) this.head).setPrevious(node);
				this.head = node;
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {

			if (this.size() == 1) {
				this.head = new DoubleLinkedListNode<>();
				this.last = new DoubleLinkedListNode<>();

			} else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head.getNext();
				aux.setPrevious(new DoubleLinkedListNode<>());
				this.head = aux;

				if (this.size() == 1) {
					this.last = (DoubleLinkedListNode<T>) this.head;
				}
			}
		}

	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			DoubleLinkedListNode<T> aux = this.last.getPrevious();

			this.last = aux;
			this.last.setNext(new DoubleLinkedListNode<>());

		}

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(),
					new DoubleLinkedListNode<>());
			if (isEmpty()) {
				this.head = node;
				this.last = node;
			} else {
				if (this.getComparator().compare(element, this.head.getData()) < 0) {
					insertFirst(element);
				} else {
					DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;

					while (!aux.getNext().isNIL()
							&& this.getComparator().compare(element, aux.getNext().getData()) >= 0) {
						aux = (DoubleLinkedListNode<T>) aux.getNext();
					}
					node.setNext(aux.getNext());
					node.setPrevious(aux);

					aux.setNext(node);
					node.setPrevious(node);

					if (node.getNext().isNIL()) {
						this.last = node;
					}

				}
			}

		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (this.size() == 1) {
				removeFirst();
			} else if (this.head.getData().equals(element)) {
				removeFirst();
			} else {

				DoubleLinkedListNode<T> node = searchNode(element);

				if (!node.isNIL()) {
					node.getPrevious().setNext(node.getNext());
					((DoubleLinkedListNode<T>) node.getNext()).setPrevious(node.getPrevious());
				}
			}
		}

	}

	private DoubleLinkedListNode<T> searchNode(T element) {

		DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) getHead();

		while (!node.isNIL() && !node.getData().equals(element)) {
			node = (DoubleLinkedListNode<T>) node.getNext();
		}
		return node;
	}

}
