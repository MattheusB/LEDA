package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	private int size;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
		this.size = 0;

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
		return this.size;

	}

	@Override
	public T search(T element) {
		T resultado = null;
		if (element != null) {
			if (element != null && !isEmpty()) {
				SingleLinkedListNode<T> aux = this.head;
				while (!aux.isNIL()) {
					if (aux.getData().equals(element)) {
						resultado = aux.getData();
					}
					aux = aux.getNext();

				}

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
			this.size++;
		}

	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!isEmpty()) {
				if (this.head.getData().equals(element)) {
					this.head = head.next;
				}
				SingleLinkedListNode<T> aux = head.getNext();
				SingleLinkedListNode<T> anterior = head;

				while (!aux.isNIL() && !aux.getData().equals(element)) {
					aux = aux.getNext();
					anterior = anterior.getNext();

				}
				if (!aux.isNIL()) {
					anterior.setNext(aux.getNext());
					this.size--;

				}
			}

		}

	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size];
		int i = 0;
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL()) {
			array[i] = aux.getData();
			aux = aux.getNext();
			i++;
		}
		return array;

	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
