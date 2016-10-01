package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	private DoubleLinkedListNode<T> nilNode = new DoubleLinkedListNode<>();

	public DoubleLinkedListImpl() {
		setLast(nilNode);
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, nilNode, nilNode);
			if (isEmpty()) {
				last = node;
				head = node;
			} else {
				node.next = head;
				((DoubleLinkedListNode<T>) head).previous = node;
				head = node;

			}

		}

	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			head = head.next;
			((DoubleLinkedListNode<T>) head).previous = nilNode;

			if (head.next == null) {
				head.next = nilNode;
				last = (DoubleLinkedListNode<T>) head;
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			last = last.previous;
			last.next = nilNode;

			if (last.previous == null || last.previous.isNIL()) {
				last.previous = nilNode;
				head = last;
			}

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
					((DoubleLinkedListNode<T>) head).previous = nilNode;

					if (head.next == null) {
						head.next = nilNode;
						last = (DoubleLinkedListNode<T>) head;
					}

				} else {

					DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head.next;

					while (!aux.isNIL() && !aux.getData().equals(element)) {
						aux = (DoubleLinkedListNode<T>) aux.next;
					}

					if (!aux.isNIL()) {
						aux.previous.next = aux.next;
						((DoubleLinkedListNode<T>) aux.next).previous = aux.previous;
						if (aux.next.isNIL()) {
							last = last.previous;
						}
					}
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