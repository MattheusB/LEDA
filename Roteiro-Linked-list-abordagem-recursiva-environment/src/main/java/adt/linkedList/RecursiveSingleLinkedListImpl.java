package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;

	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else if (next == null) {
			return 1;
		} else {
			return 1 + next.size();
		}

	}

	@Override
	public T search(T element) {
		if (element != null) {
			if (isEmpty()) {
				return null;
			} else if (this.data.equals(element)) {
				return this.data;
			} else if (next == null) {
				return null;
			} else {
				return next.search(element);
			}

		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (element != null) {

			if (isEmpty()) {
				this.data = element;
			} else if (next == null) {
				RecursiveSingleLinkedListImpl<T> node = new RecursiveSingleLinkedListImpl<>(element, null);
				next = node;
			} else {
				next.insert(element);
			}

		}

	}

	// 1,5,4,7 9

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (this.data.equals(element)) {
				if (next == null) {
					this.data = null;
				} else {
					this.data = this.next.data;
					this.next = next.next;

				}
			} else if (next != null) {
				this.next.remove(element);
			}

		}
	}

	@Override
	public T[] toArray() {
		if (isEmpty()) {
			return (T[]) new Object[this.size()];
		} else {
			int i = 0;
			T[] lista = (T[]) new Object[this.size()];
			return toArray(this, lista, i);
		}

	}

	private T[] toArray(RecursiveSingleLinkedListImpl<T> node, T[] array, int indice) {

		if (indice < array.length) {
			array[indice] = node.data;
			return toArray(node.next, array, indice + 1);
		}
		return array;

	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
