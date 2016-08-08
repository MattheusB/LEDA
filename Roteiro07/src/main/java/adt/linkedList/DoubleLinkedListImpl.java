package adt.linkedList;

public class DoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	private DoubleLinkedListNode<T> nilNode = new DoubleLinkedListNode<>();

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, nilNode, nilNode);
			if (isEmpty()) {
				last = node;
				head = node;
			} else {
				node.next = head;
				head = node;

			}

		}

	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			head = head.next;
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			last.previous.next = (DoubleLinkedListNode<T>) last.next;
			((DoubleLinkedListNode<T>) last.next).previous = last.previous;
			last = last.previous;

		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> node = new DoubleLinkedListNode<T>(element, nilNode, nilNode);
			if (this.isEmpty()) {
				head = node;

			} else {
				node.previous = last;
				last.next = node;

			}
			last = node;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!isEmpty()) {
				if (head.getData().equals(element)) {
					head = head.next;
				}

				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head;
				while (!aux.getNext().isNIL()) {
					aux = (DoubleLinkedListNode<T>) aux.next;
					aux.previous = aux;
				}

				if (!aux.isNIL()) {
					aux.previous.setNext(aux.getNext());
				}

			}
		}
	}

	@Override
	public T search(T element) {
		T resultado = null;
		if (!isEmpty()) {

			DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head;

			while (!aux.isNIL()) {
				if (aux.getData().equals(element)) {
					return aux.getData();
				}
				aux = (DoubleLinkedListNode<T>) aux.next;
			}
		}

		return resultado;
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
