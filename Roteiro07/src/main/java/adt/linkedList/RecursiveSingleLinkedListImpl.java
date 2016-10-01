package adt.linkedList;

import java.util.Arrays;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {


	protected SingleLinkedListNode<T> head;

	public RecursiveSingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();

	}

	@Override
	public boolean isEmpty() {
		if (head.isNIL()) {
			return true;
		}
		return false;

	}

	@Override
	public int size() {
		SingleLinkedListNode<T> aux = this.head;

		int size = 0;
		if (isEmpty()) {
			return size;
		}

		while (!aux.isNIL()) {
			size++;
			aux = aux.next;
		}
		return size;

	}

	@Override
	public T search(T element) {
		T resultado = null;
		if (element != null && !isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.isNIL()) {
				if (aux.getData().equals(element)) {
					resultado = aux.getData();
				}
				aux = aux.getNext();

			}

		}

		return resultado;

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> node = new SingleLinkedListNode<>(element, new SingleLinkedListNode<T>());

			if (isEmpty()) {
				this.head = node;
			} else {
				SingleLinkedListNode<T> aux = this.head;
				while (!aux.getNext().isNIL()) {
					aux = aux.getNext();
				}

				aux.setNext(node);
			}
		}

	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!isEmpty()) {
				if (this.head.getData().equals(element)) {
					this.head = head.next;

				} else {
					SingleLinkedListNode<T> aux = head.getNext();
					SingleLinkedListNode<T> anterior = head;

					while (!aux.isNIL() && !aux.getData().equals(element)) {
						aux = aux.getNext();
						anterior = anterior.getNext();

					}
					if (!aux.isNIL()) {
						anterior.setNext(aux.getNext());

					}
				}
			}

		}

	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		int i = 0;
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL()) {
			array[i] = aux.getData();
			aux = aux.getNext();
			i++;
		}
		return array;

	}

	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
